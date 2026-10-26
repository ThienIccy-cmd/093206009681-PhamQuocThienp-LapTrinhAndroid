package com.example.bt01.model

/**
 * OOP Repository / Manager class for tasks and categories.
 */
class TaskManager {
    val categoryStudy = TaskCategory(
        id = "cat_study",
        name = "Học tập",
        colorHex = 0xFF2D52F1,
        description = "Bài tập, lịch thi, đồ án"
    )

    val categoryLife = TaskCategory(
        id = "cat_life",
        name = "Cuộc sống",
        colorHex = 0xFFF97316,
        description = "Việc nhà, mua sắm, cá nhân"
    )

    private val initialTasks = listOf(
        TaskItem("1", "Ôn bài Toán cao cấp", categoryStudy, "19:00 Hôm nay", false),
        TaskItem("2", "Nộp bài tập Android Navigation", categoryStudy, "23:59 Hôm nay", false),
        TaskItem("3", "Dọn dẹp phòng học", categoryLife, "08:00 Ngày mai", true),
        TaskItem("4", "Đi siêu thị mua thực phẩm", categoryLife, "17:30 Ngày mai", false)
    )

    fun getCategories(): List<TaskCategory> = listOf(categoryStudy, categoryLife)

    fun getInitialTasks(): List<TaskItem> = initialTasks
}
