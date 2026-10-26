package com.example.bt02.model

// 1. Tính trừu tượng (Abstraction): Lớp trừu tượng định nghĩa khung chung cho các ấn phẩm trong thư viện
abstract class LibraryItem(
    private val id: String,
    private var title: String
) {
    abstract fun getDetails(): String
    abstract fun getItemType(): String

    // Tính đóng gói (Encapsulation): Cung cấp phương thức truy xuất dữ liệu an toàn
    open fun getTitle(): String = title
    open fun setTitle(newTitle: String) {
        if (newTitle.isNotBlank()) {
            title = newTitle
        }
    }

    open fun getId(): String = id
}
