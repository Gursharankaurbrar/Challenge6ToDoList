package com.example.challenge6todolist

import com.google.firebase.firestore.PropertyName

/**
 * A data class representing a task in the To-Do list app.
 **/
data class Task(
    val id: String = "",
    val title: String = "",
    @PropertyName("done")
    val isDone: Boolean = false
)
