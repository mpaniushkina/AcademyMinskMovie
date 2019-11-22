package com.example.academyminskmovie.threads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.academyminskmovie.CoroutineTask
import com.example.academyminskmovie.R
import com.example.academyminskmovie.StringsProvider

class TaskViewModel(val stringsProvider: StringsProvider) : ViewModel(), TaskEventsContract.Operationable {

    private val textMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var task: BaseTask<out Any>? = null
    private val listener: TaskEventsContract.Lifecycle = object : TaskEventsContract.Lifecycle {
        override fun onProgressUpdate(progress: Int) {
            textMutableLiveData.value = progress.toString()
        }
        override fun onPreExecute() = Unit

        override fun onPostExecute() {
            textMutableLiveData.value = stringsProvider.getString(com.example.academyminskmovie.R.string.done)
        }

        override fun onCancel() = Unit
    }
    val text: LiveData<String> = textMutableLiveData

//    private lateinit var mState: SavedStateHandle
//    fun SavedStateViewModel(savedStateHandle: SavedStateHandle) {
//        mState = savedStateHandle
//        mState.getLiveData<String>(textMutableLiveData.toString())
//    }

    override fun startTask() {
        val started = task?.start()

        if (started == null || started == false) {
            textMutableLiveData.value = stringsProvider.getString(task?.getStartedTextResId() ?: R.string.msg_should_create_task)
        }
    }

    override fun cancelTask() {
        val canceled = task?.cancel()

        if (canceled == null) {
            textMutableLiveData.value = stringsProvider.getString(task?.getCanceledTextResId() ?: R.string.msg_should_create_task)
        }
    }

    override fun createTask(type: Int) {
        textMutableLiveData.value = stringsProvider.getString(task?.getCreatedTextResId() ?: R.string.msg_oncreate)

        task = when (type) {
            1 ->  CoroutineTask(listener)
                .apply { createTask() }
            else -> SimpleAsyncTask(listener)
                .apply { createTask() }
        }
    }

    override fun onCleared() {
        super.onCleared()
        task?.cancel()
    }

}