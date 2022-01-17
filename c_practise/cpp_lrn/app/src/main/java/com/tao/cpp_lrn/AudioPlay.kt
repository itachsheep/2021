package com.tao.cpp_lrn

import android.view.Surface

object AudioPlay {

    external fun nativePlayPcm(pcmPath: String)
    external fun nativeStopPcm()

    external fun testFriend()
    external fun nativePlayVideo(videoPath: String, surface: Surface)
    external fun printUser(user: User)
}