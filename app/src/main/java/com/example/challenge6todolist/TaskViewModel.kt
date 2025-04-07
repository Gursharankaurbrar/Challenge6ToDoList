package com.example.challenge6todolist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * ViewModel for managing tasks in the To-Do list app.
 */
class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf<List<Task>>(emptyList())
        private set

    /**
     * Initializes the ViewModel by fetching tasks from the TaskRepository.
     */
    init {
        TaskRepository.getTasks { taskList ->
            tasks = taskList
        }
    }

    /**
     * Adds a new task with the given title to the repository.
     */
    fun addTask(title: String) {
        val task = Task(title = title)
        TaskRepository.addTask(task)
    }

    /**
     * Toggles the status of task.If the task is not completed, it will be marked as completed, and vice versa.
     */
    fun toggleTask(task: Task) {
        val updatedTask = task.copy(isDone = !task.isDone)
        Log.d("TaskViewModel", "Toggling task ${task.id}: isDone = ${updatedTask.isDone}")
        TaskRepository.updateTask(updatedTask)

    }

    /**
     * Deletes a task from the repository based on its ID.
     */
    fun deleteTask(taskId: String) {
        TaskRepository.deleteTask(taskId)
    }
}
