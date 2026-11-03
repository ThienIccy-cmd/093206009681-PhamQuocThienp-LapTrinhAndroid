package com.example.bt1.data

import com.google.firebase.firestore.DocumentId

data class TaskItem(
    @DocumentId
    val id: String = "",
    val title: String = "",
    val category: String = "Học tập",
    val time: String = "09:00",
    val isCompleted: Boolean = false,
    val userId: String = ""
)
