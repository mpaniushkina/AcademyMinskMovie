package com.example.academyminskmovie.Threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.academyminskmovie.Fragments.BaseFragment
import com.example.academyminskmovie.Interfaces.IFragmentListener
import com.example.academyminskmovie.R

class ThreadHandlerActivity : AppCompatActivity(), IFragmentListener,
    TaskEventsContract.Lifecycle, TaskEventsContract.Operationable {

    private var threadsFragment: TaskFragment? = null
    private var task: SimpleAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_handler)
        replaceFragment(TaskFragment())

        if (savedInstanceState == null) {
            val fragment = TaskFragment.newInstance(
                getString(R.string.fragment_handler_exe_title)
            ).also { threadsFragment = it }

            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit()
        }
    }

    override fun replaceFragment(fragment: BaseFragment) {
        supportFragmentManager
            .beginTransaction()
            //.addToBackStack(null)
            .add(R.id.threadHandlerTask, fragment)
            .commit()
    }

    override fun createTask() {
        Toast.makeText(this, getString(R.string.msg_thread_oncreate), Toast.LENGTH_SHORT).show()
        task = SimpleAsyncTask(this)
    }

    override fun startTask() {
        val taskCopy = task

        if (taskCopy == null || taskCopy.isCancelled) {
            Toast.makeText(this,
                R.string.msg_should_create_task, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.msg_thread_onstart), Toast.LENGTH_SHORT).show()
            task?.execute()
        }
    }

    override fun cancelTask() {
        if (task == null) {
            Toast.makeText(this,
                R.string.msg_should_create_task, Toast.LENGTH_SHORT).show()
        } else {
            task?.cancel()
        }
    }

    override fun onPreExecute() {
        Toast.makeText(this, getString(R.string.msg_preexecute), Toast.LENGTH_SHORT).show()

        threadsFragment?.updateFragmentText(getString(R.string.task_created))
    }

    override fun onPostExecute() {
        Toast.makeText(this, getString(R.string.msg_postexecute), Toast.LENGTH_SHORT).show()

        threadsFragment?.updateFragmentText(getString(R.string.done))
        task = null
    }

    override fun onProgressUpdate(progress: Int) {
        threadsFragment?.updateFragmentText(progress.toString())
    }

    override fun onCancel() {
        Toast.makeText(this, getString(R.string.msg_thread_oncancel), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        if (task != null) {
            task!!.cancel()
            task = null
        }
        super.onDestroy()
    }
}
