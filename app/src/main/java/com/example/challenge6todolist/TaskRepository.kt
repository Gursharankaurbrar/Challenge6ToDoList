package com.example.challenge6todolist

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A repository for managing tasks in the Firestore database.
 **/
object TaskRepository {
    // Firestore instance
    @SuppressLint("StaticFieldLeak")
    private val db = FirebaseFirestore.getInstance()
    private val tasksCollection = db.collection("tasks")

    /**
     * Retrieves all tasks from Firestore and passes them to the provided callback. It maps Firestore documents to Task objects.
     **/
    fun getTasks(callback: (List<Task>) -> Unit) {
        tasksCollection.addSnapshotListener { snapshot, _ ->
            val taskList = snapshot?.documents?.mapNotNull {
                it.toObject(Task::class.java)?.copy(id = it.id)
            } ?: emptyList()
            callback(taskList)
        }
    }

    /**
     * Adds a new task to the Firestore database.
     */
    fun addTask(task: Task) {
        tasksCollection.add(task)
    }

    /**
     * Updates an existing task in Firestore based on its ID
     */
    fun updateTask(task: Task) {
        Log.d("TaskRepository", "Updating task ${task.id}: isDone = ${task.isDone}")
        tasksCollection.document(task.id).set(task)
    }

    /**
     * Deletes a task from Firestore by its ID.
     */
    fun deleteTask(taskId: String) {
        tasksCollection.document(taskId).delete()
    }
}
