package com.example.bt02.model

// 1. Tính trừu tượng (Abstraction): Lớp trừu tượng User
abstract class User(
    private val userId: String,
    private var name: String
) {
    abstract fun getRole(): String
    abstract fun getMaxBorrowLimit(): Int

    // Tính đóng gói (Encapsulation)
    open fun getName(): String = name
    open fun setName(newName: String) {
        if (newName.isNotBlank()) name = newName
    }

    open fun getUserId(): String = userId
}

// 2. Tính kế thừa (Inheritance): Student kế thừa từ User
open class Student(
    userId: String,
    name: String,
    private val borrowedBookIds: MutableList<String> = mutableListOf()
) : User(userId, name) {

    override fun getRole(): String = "Sinh viên thường"

    // 3. Tính đa hình (Polymorphism): Giới hạn mượn sách chuẩn
    override fun getMaxBorrowLimit(): Int = 3

    open fun getBorrowedBookIds(): List<String> = borrowedBookIds

    open fun borrowBook(bookId: String): Boolean {
        if (borrowedBookIds.size < getMaxBorrowLimit() && !borrowedBookIds.contains(bookId)) {
            borrowedBookIds.add(bookId)
            return true
        }
        return false
    }

    open fun returnBook(bookId: String): Boolean {
        return borrowedBookIds.remove(bookId)
    }
}

// Lớp VIPStudent kế thừa Student (Đa hình qua override phương thức)
class VIPStudent(
    userId: String,
    name: String,
    borrowedBookIds: MutableList<String> = mutableListOf()
) : Student(userId, name, borrowedBookIds) {

    override fun getRole(): String = "Sinh viên VIP"

    // Đa hình: Sinh viên VIP được mượn nhiều sách hơn
    override fun getMaxBorrowLimit(): Int = 10
}
