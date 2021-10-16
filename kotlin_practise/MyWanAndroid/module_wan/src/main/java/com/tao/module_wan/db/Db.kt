/**
 * @ClassName:      Db.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 4:00 PM
 */
package com.tao.module_wan.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tao.module_base.BaseApplication
import com.tao.module_wan.model.Author
import com.tao.module_wan.model.HotWord

@Database(entities = [Author::class, HotWord::class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun authorDao(): AuthorDao

    abstract fun historyDao(): HistoryDao

    companion object {
        private var instance: Db? = null

        @Synchronized
        fun instance(): Db {
            if (instance == null) {
                instance = Room.databaseBuilder(
                        BaseApplication.instance,
                        Db::class.java,
                        "WanAndroidDB"
                ).build()
            }
            return instance!!
        }
    }
}