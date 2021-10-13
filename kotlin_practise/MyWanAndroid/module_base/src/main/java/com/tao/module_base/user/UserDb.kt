package com.tao.module_base.user

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tao.module_base.BaseApplication


@Database(entities = [User::class], version = 2)
abstract class UserDb: RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
        private  var instance: UserDb ?= null
        private val lock: Any = Any()

        @Synchronized
        fun instance(): UserDb {
            if(instance == null) {
                synchronized(lock) {
                    if(instance == null) {
                        instance = Room.databaseBuilder(
                                BaseApplication.instance,
                                UserDb::class.java,
                                "T_WanAndroid"
                        ).build()
                    }
                }
            }
            return instance!!
        }
    }

}