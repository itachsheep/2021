package com.tao.module_wan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HotWord(
        @PrimaryKey(autoGenerate = true)
        var id : Int?,
        val name : String
)
