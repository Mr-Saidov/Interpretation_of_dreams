package java.com.interpretationofdreams.data.local.localentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Descriptions(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    val id: Int,

    @ColumnInfo(defaultValue = "0")
    val saved: Int,
    val txt: String,

    @ColumnInfo(name = "cat_id")
    val categoryId: Int
)