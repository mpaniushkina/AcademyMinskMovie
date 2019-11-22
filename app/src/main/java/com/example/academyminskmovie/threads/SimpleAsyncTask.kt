package com.example.academyminskmovie.threads

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.example.academyminskmovie.R

class SimpleAsyncTask(listener: TaskEventsContract.Lifecycle) : BaseTask<Thread>(listener) {

    override fun createTask(): Thread {
        return object : Thread("Handler_executor_thread") {
            override fun run() {
                doInBackground()
                runOnUiThread(Runnable { onPostExecute() })
            }
        }
    }

    @Volatile
    var isCancelled = false
        private set

    override fun getCreatedTextResId() = R.string.msg_thread_oncreate

    override fun getStartedTextResId() = R.string.msg_thread_onstart

    override fun getCanceledTextResId() = R.string.msg_thread_oncancel

    override fun start(): Boolean? {
        runOnUiThread(Runnable {
            onPreExecute()
            task = createTask()
            task?.start()
        })
        return true
    }

    override fun cancel() {
        isCancelled = true
        task?.interrupt()
    }

    /**
     * Runs on new thread after [.onPreExecute] and before [.onPostExecute].
     */
    private fun doInBackground() {
        val end = 10
        for (i in 0..end) {
            if (isCancelled) {
                return
            }

            publishProgress(i)
            SystemClock.sleep(500)
        }
    }

    private fun runOnUiThread(runnable: Runnable) {
        Handler(Looper.getMainLooper()).post(runnable)
    }

    private fun publishProgress(progress: Int) {
        runOnUiThread(Runnable { onProgressUpdate(progress) })
    }
}