package com.example.academyminskmovie.Threads

interface TaskEventsContract {

    interface Operationable{
        fun createTask()
        fun startTask()
        fun cancelTask()
    }

    interface Lifecycle{
        fun onPreExecute()
        fun onPostExecute()
        fun onProgressUpdate(progress: Int)
        fun onCancel()
    }

}