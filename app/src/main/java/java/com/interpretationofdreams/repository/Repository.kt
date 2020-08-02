package java.com.interpretationofdreams.repository

import java.com.interpretationofdreams.data.local.AppDatabase
import java.com.interpretationofdreams.data.local.localentity.Descriptions

class Repository(private val appDatabase: AppDatabase) {

    fun getAllWords() = appDatabase.wordDao().getAllWords()
    fun getWordDescription(wordId: Int): List<Descriptions> =
        appDatabase.descriptionsDao().getDescriptionsByCatId(wordId)

    fun getWordWithPagination() = appDatabase.wordDao().getWordWithPagination()

    fun getWordWithPaginationByName(name: String) =
        appDatabase.wordDao().getWordWithPaginationByName(name)
}