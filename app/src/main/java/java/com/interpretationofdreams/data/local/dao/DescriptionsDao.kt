package java.com.interpretationofdreams.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import java.com.interpretationofdreams.data.local.localentity.Descriptions

@Dao
interface DescriptionsDao {

    @Query("select*from items where cat_id = :categoryId")
    fun getDescriptionsByCatId(categoryId: Int): List<Descriptions>
}