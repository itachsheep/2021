/**
 * @ClassName:      HistoryDao.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 4:07 PM
 */
package com.tao.module_wan.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tao.module_wan.model.Author
import com.tao.module_wan.model.HotWord

@Dao
interface HistoryDao {
    @Insert
    fun insert(hotWord: HotWord)

    @Query("SELECT * FROM HotWord WHERE name in (:name)")
    fun query(name: String): HotWord?

    @Query("SELECT * FROM HotWord")
    fun queryAll(): LiveData<List<HotWord>>

    @Delete
    fun delete(hotWord: HotWord)

    @Query("DELETE FROM HotWord WHERE name in (:name)")
    fun deleteByName(name: String)

    @Query("DELETE FROM HotWord")
    fun deleteAll()
}