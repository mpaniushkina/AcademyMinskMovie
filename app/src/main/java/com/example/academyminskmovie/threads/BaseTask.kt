package com.example.academyminskmovie.threads

import androidx.annotation.StringRes

abstract class BaseTask<T>(private val listener: TaskEventsContract.Lifecycle) :
    TaskEventsContract.Lifecycle {

    protected var task: T = createTask()
    /**
     * Runs on the UI thread before [.doInBackground].
     */
    override fun onPreExecute() {
        listener.onPreExecute()
    }

    /**
     * Runs on the UI thread after [.doInBackground]
     */
    override fun onPostExecute() {
        listener.onPostExecute()
    }

    override fun onProgressUpdate(progress: Int) {
        listener.onProgressUpdate(progress)
    }

    override fun onCancel() {
        listener.onCancel()
    }

    abstract fun cancel()

    abstract fun start(): Boolean?

    abstract fun createTask() : T

    @StringRes
    abstract fun getCreatedTextResId(): Int
    abstract fun getStartedTextResId(): Int
    abstract fun getCanceledTextResId(): Int


}