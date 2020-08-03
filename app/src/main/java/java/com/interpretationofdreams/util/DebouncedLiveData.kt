package java.com.interpretationofdreams.util

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData


/**
 *  Created by Dilshodbek on 8/3/2020
 */

class DebouncedLiveData<T>(source: LiveData<T>, duration: Long) :
    MediatorLiveData<T?>() {
    private val mSource: LiveData<T> = source
    private val mDuration: Long = duration
    private val debounceRunnable = Runnable { postValue(mSource.value) }
    private val handler: Handler = Handler()

    init {
        addSource(mSource) {
            handler.removeCallbacks(debounceRunnable)
            handler.postDelayed(debounceRunnable, mDuration)
        }
    }
}