package com.example.academyminskmovie

import android.util.Log
import com.example.academyminskmovie.Threads.BaseTask
import com.example.academyminskmovie.Threads.TaskEventsContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

private const val LOG_TAG = "CoroutineTask"
private const val MAX_COUNTER_VALUE = 10
private const val DELAY_IN_MILLS = 500L

class CoroutineTask(listener: TaskEventsContract.Lifecycle) : BaseTask<Job>(listener), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    override fun createTask(): Job {
        return launch(context = Dispatchers.IO, start = CoroutineStart.LAZY) {

            Log.d(LOG_TAG, "Start job on IO thread | thread: ${Thread.currentThread().name}")

            repeat(MAX_COUNTER_VALUE) { counter ->
                Log.d(
                    LOG_TAG,
                    "New counter value [counter: $counter] | thread: ${Thread.currentThread().name}"
                )
                launch(Dispatchers.Main) {
                    Log.d(
                        LOG_TAG,
                        "Switch thread to main [counter: $counter] | thread: ${Thread.currentThread().name}"
                    )
                    onProgressUpdate(counter)
                }
                delay(DELAY_IN_MILLS)
            }

            launch(Dispatchers.Main) {
                Log.d(
                    LOG_TAG,
                    "Switch thread to main again | thread: ${Thread.currentThread().name}"
                )
                onPostExecute()
            }
        }
    }

    override fun cancel() {
        Log.d(LOG_TAG, "Before 'cancel' of job | thread: ${Thread.currentThread().name}")

        task?.cancel()
        coroutineContext.cancel()

        Log.d(LOG_TAG, "Before 'cancel' of job | thread: ${Thread.currentThread().name}")
    }

    override fun start(): Boolean? {
        onPreExecute()
        Log.d(LOG_TAG, "Before 'start' of job | thread: ${Thread.currentThread().name}")
        val started = task?.start()
        Log.d(
            LOG_TAG,
            "After 'start' of job (started: $started) | thread: ${Thread.currentThread().name}"
        )
        return started
    }

    override fun getCreatedTextResId() = R.string.msg_oncreate

    override fun getStartedTextResId() = R.string.msg_onstart

    override fun getCanceledTextResId() = R.string.msg_oncancel
}