package java.com.interpretationofdreams.ui.description

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.com.interpretationofdreams.data.local.localentity.Descriptions
import java.com.interpretationofdreams.repository.Repository

class DescriptionViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    private val _description = MutableLiveData<List<Descriptions>>()
    val description: LiveData<List<Descriptions>> = _description

    fun getDescription(wordId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _description.postValue(repository.getWordDescription(wordId))
        }
    }
}