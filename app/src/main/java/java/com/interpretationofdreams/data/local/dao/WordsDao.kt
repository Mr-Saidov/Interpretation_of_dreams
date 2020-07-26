package java.com.interpretationofdreams.data.local.dao

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
}