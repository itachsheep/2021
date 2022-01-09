//
// Created by wei tao on 2022/1/9.
//

#include "simplest_aac_parser.h"

int get_ADTS_frame(unsigned char *buffer, int buf_size, unsigned char *data, int *data_size) {
    int size = 0;

    if (!buffer || !data || !data_size) {
        printf("param illegal \n");
        return -1;
    }

    while (1) {
        if (buf_size < 7) {
            printf("buff size < 7 \n");
            return -1;
        }

        //其中每个ADTS frame之间通过syncword（同步字）进行分隔。
        // 同步字为0xFFF（二进制“111111111111”）。
        // AAC码流解析的步骤就是首先从码流中搜索0x0FFF，
        // 分离出ADTS frame；然后再分析ADTS frame的首部各个字段
        if ((buffer[0] == 0xff) && (buffer[1] & 0xf0) == 0xf0) {
            size |= ((buffer[3] & 0x03) << 11);  //high 2 bit
            size |= buffer[4] << 3;               //middle 8 bit
            size |= ((buffer[5] & 0xe0) >> 5);    //low 3bit
            break;
        }
        --buf_size;
        ++buffer;
    }

    if (buf_size < size) {
        return 1;
    }
    memcpy(data, buffer, size);
    *data_size = size;
    return 0;
}

int simplest_aac_parse(char *source) {
    int data_size = 0;
    int size = 0;
    int cnt = 0;
    int offset = 0;

    FILE *my_out = stdout;
    unsigned char *aac_frame = (unsigned char *) malloc(1024 * 5);
    unsigned char *aac_buffer = (unsigned char *) malloc(1024 * 1024);

    FILE *fp_in = fopen(source, "rb+");
    if (!fp_in) {
        printf("open file error \n");
        return -1;
    }

    printf("-----+- ADTS Frame Table -+------+\n");
    printf(" NUM | Profile | Frequency| Size |\n");
    printf("-----+---------+----------+------+\n");
    while (!feof(fp_in)) {
        data_size = fread(aac_buffer + offset, 1, 1024 * 1024 - offset, fp_in);
        unsigned char *input_data = aac_buffer;
        while (1) {
            int ret = get_ADTS_frame(input_data, data_size, aac_frame, &size);
            if (ret == -1) {
                break;
            } else if (ret == 1) {
                memcpy(aac_buffer, input_data, data_size);
                offset = data_size;
                break;
            }

            char profile_str[10] = {0};
            char frequence_str[10] = {0};

            unsigned char profile = aac_frame[2] & 0xC0;
            profile = profile >> 6;

            switch (profile) {
                case 0:
                    sprintf(profile_str, "Main");
                    break;
                case 1:
                    sprintf(profile_str, "LC");
                    break;
                case 2:
                    sprintf(profile_str, "SSR");
                    break;
                default:
                    sprintf(profile_str, "unknown");
                    break;
            }

            unsigned char sampling_frequency_index = aac_frame[2] & 0x3C;
            sampling_frequency_index = sampling_frequency_index >> 2;
            switch (sampling_frequency_index) {
                case 0:
                    sprintf(frequence_str, "96000Hz");
                    break;
                case 1:
                    sprintf(frequence_str, "88200Hz");
                    break;
                case 2:
                    sprintf(frequence_str, "64000Hz");
                    break;
                case 3:
                    sprintf(frequence_str, "48000Hz");
                    break;
                case 4:
                    sprintf(frequence_str, "44100Hz");
                    break;
                case 5:
                    sprintf(frequence_str, "32000Hz");
                    break;
                case 6:
                    sprintf(frequence_str, "24000Hz");
                    break;
                case 7:
                    sprintf(frequence_str, "22050Hz");
                    break;
                case 8:
                    sprintf(frequence_str, "16000Hz");
                    break;
                case 9:
                    sprintf(frequence_str, "12000Hz");
                    break;
                case 10:
                    sprintf(frequence_str, "11025Hz");
                    break;
                case 11:
                    sprintf(frequence_str, "8000Hz");
                    break;
                default:
                    sprintf(frequence_str, "unknown");
                    break;
            }

            fprintf(my_out, "%5d| %8s|  %8s| %5d|\n", cnt, profile_str, frequence_str, size);
            data_size -= size;
            input_data += size;
            cnt++;
        }
    }

    fclose(fp_in);
    free(aac_buffer);
    free(aac_frame);
    return 0;
}