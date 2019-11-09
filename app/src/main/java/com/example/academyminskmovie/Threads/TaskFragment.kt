package com.example.academyminskmovie.Threads

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.academyminskmovie.Fragments.BaseFragment
import com.example.academyminskmovie.R
import kotlinx.android.synthetic.main.fragment_counter.*

private const val FRAGMENT_TYPE = "fragment_type"

class TaskFragment : BaseFragment(), TaskEventsContract.Lifecycle,
    TaskEventsContract.Operationable {

    private lateinit var viewModel: TaskViewModel

    override fun getLayoutResId() = R.layout.fragment_counter

    companion object {
        fun newInstance(fragmentTitle: String): TaskFragment {
            val fragment = TaskFragment()

            val bundle = Bundle().apply {
                putString(FRAGMENT_TYPE, fragmentTitle)
            }
            fragment.arguments = bundle

            return fragment
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//
//        super.onSaveInstanceState(outState)
//        outState.putString(FRAGMENT_TYPE, tvCounter.text.toString())
//    }

    private var listener: TaskEventsContract.Operationable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (activity != null && activity is TaskEventsContract.Operationable) {
            listener = activity as TaskEventsContract.Operationable
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders
            .of(this.activity!!, TaskViewModelFactory(this.context!!))
            .get(TaskViewModel::class.java)
        viewModel.text.observe(this, Observer { text -> this.updateFragmentText(text) })

        btnCreate.setOnClickListener { viewModel.createTask(arguments?.getInt("extra_type", 0) ?: 0) }
        btnStart.setOnClickListener { viewModel.startTask() }
        btnCancel.setOnClickListener { viewModel.cancelTask() }

        //UNPACK OUR DATA FROM OUR BUNDLE
        val fragmentText = this.arguments?.getString(FRAGMENT_TYPE)
        tvCounter.text = fragmentText
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun updateFragmentText(text: String) {
        tvCounter.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelTask()
    }

    override fun createTask(type: Int) {
        viewModel.createTask(type)
    }

    override fun startTask() {
        viewModel.startTask()
    }

    override fun cancelTask() {
        viewModel.cancelTask()
    }

    override fun onProgressUpdate(progress: Int) {
        this.updateFragmentText(progress.toString())
    }

    override fun onPreExecute() {
        this.updateFragmentText(getString(R.string.task_created))
    }

    override fun onPostExecute() {
        this.updateFragmentText(getString(R.string.done))
    }

    override fun onCancel() {
        //Toast.makeText(this, getString(R.string.msg_oncancel), Toast.LENGTH_SHORT).show()
    }
}