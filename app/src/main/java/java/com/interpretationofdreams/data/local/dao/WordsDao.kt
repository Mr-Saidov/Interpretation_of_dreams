package java.com.interpretationofdreams.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.com.interpretationofdreams.data.local.localentity.Words

@Dao
interface WordsDao {

    @Query("select * from category order by cat_id asc")
    fun getAllWords(): List<Words>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWords(words: ArrayList<Words>)

    @Query("SELECT * FROM category order by cat_name")
    fun getWordWithPagination(): DataSource.Factory<Int, Words>

    @Query("SELECT * FROM category where cat_name LIKE  :name order by cat_name")
    fun getWordWithPaginationByName(name: String): DataSource.Factory<Int, Words>
}