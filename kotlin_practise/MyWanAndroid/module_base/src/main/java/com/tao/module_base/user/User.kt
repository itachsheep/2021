package com.tao.module_base.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey var id: Int,
        var userName: String,
        var token: String,
        var icon: String?,
        var email: String?,
        var extension: String?
)
