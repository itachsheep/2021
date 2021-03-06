/**
 * @ClassName: _7_string_to_integer
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.algorithm.practise.hr收集腾讯笔试题;

/**
 *  字符串转int类型数字
 */
public class _7_string_to_integer {
    public static void main(String[] args) {
        String test1 = "283947";
        String test2 = "   93240098";
        String test3 = "-2398401";
        System.out.println(myAtoI(test1));
        System.out.println(myAtoI(test2));
        System.out.println(myAtoI(test3));
    }

    public static int myAtoI(String number) {
        if(number == null || number.length() == 0) return 0;
        int multi = 1;
        if(number.charAt(0) == '-') {
            multi = -1;
        }

        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if(c >= '0' && c <= '9') {
                if(multi > 0) {
                    sum = sum * 10 + (c - '0');
                    if(sum < 0) {
                        throw new IllegalArgumentException("too large number");
                    }
                } else {
                    sum = sum * 10 - (c - '0');
                    if(sum > 0) {
                        throw new IllegalArgumentException("too small number");
                    }
                }
            }
        }
        return sum;
    }
}
