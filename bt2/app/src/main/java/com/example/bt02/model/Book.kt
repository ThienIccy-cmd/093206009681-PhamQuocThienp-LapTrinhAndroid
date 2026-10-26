package com.example.bt02.model

// 2. Tính kế thừa (Inheritance): Book kế thừa từ LibraryItem
open class Book(
    id: String,
    title: String,
    private var author: String,
    private var isBorrowed: Boolean = false
) : LibraryItem(id, title) {

    // 3. Tính đa hình (Polymorphism): Ghi đè phương thức getDetails()
    override fun getDetails(): String {
        val status = if (isBorrowed) "Đã mượn" else "Có sẵn"
        return "Sách: ${getTitle()} - Tác giả: $author (Trạng thái: $status)"
    }

    override fun getItemType(): String = "Sách thông thường"

    // Tính đóng gói (Encapsulation)
    open fun getAuthor(): String = author
    open fun setAuthor(newAuthor: String) {
        if (newAuthor.isNotBlank()) author = newAuthor
    }

    open fun isBorrowed(): Boolean = isBorrowed
    open fun setBorrowed(borrowed: Boolean) {
        isBorrowed = borrowed
    }
}

// Lớp con kế thừa từ Book (Đa cấp kế thừa)
class TextBook(
    id: String,
    title: String,
    author: String,
    val subject: String,
    isBorrowed: Boolean = false
) : Book(id, title, author, isBorrowed) {

    override fun getDetails(): String {
        val status = if (isBorrowed()) "Đã mượn" else "Có sẵn"
        return "Sách giáo khoa [$subject]: ${getTitle()} - Tác giả: ${getAuthor()} ($status)"
    }

    override fun getItemType(): String = "Sách giáo khoa"
}
