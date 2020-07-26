package java.com.interpretationofdreams.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import java.com.interpretationofdreams.data.local.dao.DescriptionsDao
import java.com.interpretationofdreams.data.local.dao.WordsDao
import java.com.interpretationofdreams.data.local.localentity.Descriptions
import java.com.interpretationofdreams.data.local.localentity.Words

@Database(entities = [Words::class, Descriptions::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordsDao
    abstract fun descriptionsDao(): DescriptionsDao

    companion object {
        const val dbName = "myquotes.db"
    }
}
