package java.com.interpretationofdreams.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.com.interpretationofdreams.data.local.localentity.Words
import java.com.interpretationofdreams.repository.Repository

class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val _words = MutableLiveData<List<Words>>()
    val words: LiveData<List<Words>> = _words

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _words.postValue(repository.getAllWords())
        }
    }
}