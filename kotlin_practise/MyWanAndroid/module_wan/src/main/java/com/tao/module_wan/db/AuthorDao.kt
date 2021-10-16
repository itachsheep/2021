/**
 * @ClassName:      AuthorDao.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 4:06 PM
 */
package com.tao.module_wan.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tao.module_wan.model.Author

@Dao
interface AuthorDao {
    @Insert
    fun insert(authors: ArrayList<Author>)

    @Update
    fun update(author: Author)

    @Update
    fun updateAll(authors: List<Author>)

    @Query("SELECT * FROM Author WHERE visible in (:visible) ORDER BY sort ASC")
    fun queryByVisible(visible: Int): List<Author>

    @Query("SELECT * FROM Author WHERE visible in (:visible) ORDER BY sort ASC")
    fun queryLiveDataByVisible(visible: Int): LiveData<List<Author>>

    @Query("SELECT * FROM Author")
    fun queryAll(): List<Author>
}