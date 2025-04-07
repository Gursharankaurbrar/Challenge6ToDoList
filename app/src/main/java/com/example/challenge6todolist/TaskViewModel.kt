package com.example.challenge6todolist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf<List<Task>>(emptyList())
        private set

    init {
        TaskRepository.getTasks { taskList ->
            tasks = taskList
        }
    }

    fun addTask(title: String) {
        val task = Task(title = title)
        TaskRepository.addTask(task)
    }

    fun toggleTask(task: Task) {
        TaskRepository.updateTask(task.copy(isDone = !task.isDone))
    }

    fun deleteTask(taskId: String) {
        TaskRepository.deleteTask(taskId)
    }
}
