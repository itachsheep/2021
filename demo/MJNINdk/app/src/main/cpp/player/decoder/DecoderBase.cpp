//
// Created by wei tao on 2021/4/26.
//
#include "DecoderBase.h"
#include "LogUtil.h"

int DecoderBase::Init(const char *url, AVMediaType mediaType)
{
    LOGCATD("DecoderBase::Init url=%s, mediaType=%d", url, mediaType);
    strcpy(m_Url,url);
    m_MediaType = mediaType;
    return 0;
}

