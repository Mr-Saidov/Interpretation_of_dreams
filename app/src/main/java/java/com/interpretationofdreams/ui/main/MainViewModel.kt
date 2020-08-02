package java.com.interpretationofdreams.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.com.interpretationofdreams.data.local.localentity.Words
import java.com.interpretationofdreams.repository.Repository

class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val _words = MutableLiveData<List<Words>>()
    val words: LiveData<List<Words>> = _words
    private var _pagedWords = MutableLiveData<PagedList<Words>>()
    var pagedWords: LiveData<PagedList<Words>> = _pagedWords

    init {
//        viewModelScope.launch(Dispatchers.IO) {

            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .build()
            pagedWords = LivePagedListBuilder(
                repository.getWordWithPagination(),
                pagedListConfig
            ).build()
//        }

    }
}