package com.example.bt01.model

/**
 * Category data model representing task categories like "Học tập" and "Cuộc sống".
 */
data class TaskCategory(
    val id: String,
    val name: String,
    val colorHex: Long,
    val description: String
)

/**
 * Task item data model representing individual todos in UTH Todo.
 */
data class TaskItem(
    val id: String,
    val title: String,
    val category: TaskCategory,
    val dueTime: String,
    val isCompleted: Boolean = false
) {
    fun copyWithToggle(): TaskItem {
        return copy(isCompleted = !isCompleted)
    }
}
