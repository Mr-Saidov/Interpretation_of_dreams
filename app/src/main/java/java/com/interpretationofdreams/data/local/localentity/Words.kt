package java.com.interpretationofdreams.data.local.localentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Words(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cat_id")
    val id: Int,

    @ColumnInfo(name = "cat_name")
    val categoryName: String
)