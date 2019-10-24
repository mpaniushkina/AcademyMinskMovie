package com.example.academyminskmovie.Threads

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.academyminskmovie.Fragments.BaseFragment
import com.example.academyminskmovie.R
import kotlinx.android.synthetic.main.fragment_counter.*

private const val FRAGMENT_TYPE = "fragment_type"

class TaskFragment : BaseFragment() {

    override fun getLayoutResId() = R.layout.fragment_counter

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        btnCreate.setOnClickListener {
//           counter()
//        }
//        btnCancel.setOnClickListener{
//        }

//    }

//    private fun counter() {
//        val timer = object: CountDownTimer(10000, 500) {
//            override fun onTick(millisUntilFinished: Long) {
//                tvCounter.setText("" + millisUntilFinished / 1000)
//            }
//
//            override fun onFinish() {
//                tvCounter.setText("Done!")
//            }
//        }
//        timer.start()
//    }


    companion object {
        fun newInstance(fragmentTitle: String): TaskFragment {
            val fragment = TaskFragment()

            val bundle = Bundle(1).apply {
                putString(FRAGMENT_TYPE, fragmentTitle)
            }
            fragment.arguments = bundle

            return fragment
        }
    }

    private var listener: TaskEventsContract.Operationable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (activity != null && activity is TaskEventsContract.Operationable) {
            listener = activity as TaskEventsContract.Operationable
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnCreate.setOnClickListener { listener?.createTask() }
        btnStart.setOnClickListener { listener?.startTask() }
        btnCancel.setOnClickListener { listener?.cancelTask() }

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
}