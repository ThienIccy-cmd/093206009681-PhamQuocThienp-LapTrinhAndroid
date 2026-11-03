package com.example.bt1.data

data class TaskItem(
    val id: String,
    val title: String,
    val category: String, // "Học tập", "Cuộc sống"
    val time: String,     // "06:30", "09:00"
    val isCompleted: Boolean = false
)
