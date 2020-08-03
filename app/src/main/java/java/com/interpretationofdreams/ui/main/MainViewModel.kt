package java.com.interpretationofdreams.ui.main

import androidx.arch.core.util.Function
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import timber.log.Timber
import java.com.interpretationofdreams.data.local.localentity.Words
import java.com.interpretationofdreams.repository.Repository


class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val _words = MutableLiveData<List<Words>>()
    val words: LiveData<List<Words>> = _words
    private var _pagedWords = MutableLiveData<PagedList<Words>>()
    var pagedWords: LiveData<PagedList<Words>> = _pagedWords

    private var _query = MutableLiveData<String>()

    init {
        _query.value = ""
        Timber.e(System.currentTimeMillis().toString())
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .build()
        pagedWords = Transformations.switchMap(
            _query,
            Function<String, LiveData<PagedList<Words>>> { input: String ->
                if (input.isBlank()) {
                    return@Function LivePagedListBuilder(
                        repository.getWordWithPagination(),
                        pagedListConfig
                    ).build()
                } else {
                    return@Function LivePagedListBuilder(
                        repository.getWordWithPaginationByName("%" + _query.value!! + "%"),
                        pagedListConfig
                    ).build()
                }
            }
        )
        Timber.e(System.currentTimeMillis().toString())
    }

    fun setfilter(query: String) {
        _query.value = query
    }

}