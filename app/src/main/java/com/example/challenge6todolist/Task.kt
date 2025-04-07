package com.example.challenge6todolist

import com.google.firebase.firestore.PropertyName

data class Task(
    val id: String = "",
    val title: String = "",
    @PropertyName("done")
    val isDone: Boolean = false
)
