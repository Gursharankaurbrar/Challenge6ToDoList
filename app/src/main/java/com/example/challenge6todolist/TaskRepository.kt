package com.example.challenge6todolist

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

object TaskRepository {
    @SuppressLint("StaticFieldLeak")
    private val db = FirebaseFirestore.getInstance()
    private val tasksCollection = db.collection("tasks")

    fun getTasks(callback: (List<Task>) -> Unit) {
        tasksCollection.addSnapshotListener { snapshot, _ ->
            val taskList = snapshot?.documents?.mapNotNull {
                it.toObject(Task::class.java)?.copy(id = it.id)
            } ?: emptyList()
            callback(taskList)
        }
    }

    fun addTask(task: Task) {
        tasksCollection.add(task)
    }

    fun updateTask(task: Task) {
        Log.d("TaskRepository", "Updating task ${task.id}: isDone = ${task.isDone}")
        tasksCollection.document(task.id).set(task)
    }


    fun deleteTask(taskId: String) {
        tasksCollection.document(taskId).delete()
    }
}
