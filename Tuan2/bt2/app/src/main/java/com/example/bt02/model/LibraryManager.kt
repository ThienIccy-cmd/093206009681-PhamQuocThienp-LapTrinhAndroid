package com.example.bt02.model

import androidx.compose.runtime.mutableStateListOf

class LibraryManager {
    val books = mutableStateListOf<Book>(
        Book("B01", "Sách 01", "Nguyễn Văn Tác Giả"),
        Book("B02", "Sách 02", "Trần Thị Tác Giả"),
        TextBook("B03", "Lập trình Android nâng cao", "Lê Văn C", "Công nghệ thông tin"),
        Book("B04", "Cấu trúc dữ liệu và giải thuật", "Phạm Văn D")
    )

    val students = mutableStateListOf<Student>(
        Student("S01", "Nguyen Van A"),
        VIPStudent("S02", "Tran Thi B"),
        Student("S03", "Le Van C")
    )

    var currentStudent: Student = students[0]

    fun borrowBookForCurrentStudent(book: Book): Boolean {
        if (!book.isBorrowed()) {
            if (currentStudent.borrowBook(book.getId())) {
                book.setBorrowed(true)
                return true
            }
        }
        return false
    }

    fun returnBookForCurrentStudent(book: Book): Boolean {
        if (currentStudent.returnBook(book.getId())) {
            book.setBorrowed(false)
            return true
        }
        return false
    }

    fun addBook(book: Book) {
        books.add(book)
    }

    fun addStudent(student: Student) {
        students.add(student)
    }
}
