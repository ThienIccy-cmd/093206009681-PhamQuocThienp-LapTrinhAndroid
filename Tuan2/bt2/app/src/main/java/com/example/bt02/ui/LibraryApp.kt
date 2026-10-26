package com.example.bt02.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt02.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryApp() {
    val libraryManager = remember { LibraryManager() }
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Quản lý, 1: DS Sách, 2: Sinh viên, 3: OOP Info
    var showEditStudentDialog by remember { mutableStateOf(false) }
    var showAddBookDialog by remember { mutableStateOf(false) }
    var showAddStudentDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hệ thống Quản lý Thư viện",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(onClick = { selectedTab = 3 }) {
                        Icon(Icons.Default.Info, contentDescription = "OOP Info")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Quản lý") },
                    label = { Text("Quản lý") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "DS Sách") },
                    label = { Text("DS Sách") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Sinh viên") },
                    label = { Text("Sinh viên") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "OOP 4 Đặc trưng") },
                    label = { Text("OOP 4ĐC") },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> ManageScreen(
                    libraryManager = libraryManager,
                    onChangeStudentClick = { showEditStudentDialog = true }
                )
                1 -> BookListScreen(
                    libraryManager = libraryManager,
                    onAddBookClick = { showAddBookDialog = true }
                )
                2 -> StudentListScreen(
                    libraryManager = libraryManager,
                    onAddStudentClick = { showAddStudentDialog = true }
                )
                3 -> OopInfoScreen()
            }
        }
    }

    // Dialog Thay đổi / Chọn sinh viên
    if (showEditStudentDialog) {
        AlertDialog(
            onDismissRequest = { showEditStudentDialog = false },
            title = { Text("Chọn Sinh Viên Mượn Sách") },
            text = {
                LazyColumn {
                    items(libraryManager.students) { student ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    libraryManager.currentStudent = student
                                    showEditStudentDialog = false
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = if (libraryManager.currentStudent == student)
                                    MaterialTheme.colorScheme.primaryContainer
                                else
                                    MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = student.getName(), fontWeight = FontWeight.Bold)
                                Text(text = "Vai trò: ${student.getRole()} (Tối đa ${student.getMaxBorrowLimit()} sách)", fontSize = 12.sp)
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showEditStudentDialog = false }) {
                    Text("Đóng")
                }
            }
        )
    }

    // Dialog Thêm sách mới
    if (showAddBookDialog) {
        var title by remember { mutableStateOf("") }
        var author by remember { mutableStateOf("") }
        var isTextBook by remember { mutableStateOf(false) }
        var subject by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddBookDialog = false },
            title = { Text("Thêm Sách Mới") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Tên sách") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = author,
                        onValueChange = { author = it },
                        label = { Text("Tác giả") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = isTextBook,
                            onCheckedChange = { isTextBook = it }
                        )
                        Text("Là Sách giáo khoa")
                    }
                    if (isTextBook) {
                        OutlinedTextField(
                            value = subject,
                            onValueChange = { subject = it },
                            label = { Text("Môn học / Chuyên ngành") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (title.isNotBlank() && author.isNotBlank()) {
                            val id = "B${System.currentTimeMillis() % 1000}"
                            val newBook = if (isTextBook) {
                                TextBook(id, title, author, subject.ifBlank { "Chung" })
                            } else {
                                Book(id, title, author)
                            }
                            libraryManager.addBook(newBook)
                            showAddBookDialog = false
                        }
                    }
                ) {
                    Text("Thêm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddBookDialog = false }) {
                    Text("Hủy")
                }
            }
        )
    }

    // Dialog Thêm sinh viên mới
    if (showAddStudentDialog) {
        var name by remember { mutableStateOf("") }
        var isVip by remember { mutableStateOf(false) }

        AlertDialog(
            onDismissRequest = { showAddStudentDialog = false },
            title = { Text("Thêm Sinh Viên Mới") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Họ tên sinh viên") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = isVip,
                            onCheckedChange = { isVip = it }
                        )
                        Text("Sinh viên VIP (Được mượn tới 10 sách)")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            val id = "S${System.currentTimeMillis() % 1000}"
                            val newStudent = if (isVip) {
                                VIPStudent(id, name)
                            } else {
                                Student(id, name)
                            }
                            libraryManager.addStudent(newStudent)
                            showAddStudentDialog = false
                        }
                    }
                ) {
                    Text("Thêm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddStudentDialog = false }) {
                    Text("Hủy")
                }
            }
        )
    }
}

@Composable
fun ManageScreen(
    libraryManager: LibraryManager,
    onChangeStudentClick: () -> Unit
) {
    var message by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sinh viên Section
        Text(text = "Sinh viên", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = libraryManager.currentStudent.getName(),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.weight(1f)
            )
            Button(onClick = onChangeStudentClick) {
                Text("Thay đổi")
            }
        }

        // Student Role & Limit Info (Demonstrating Polymorphism & Encapsulation)
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Loại tài khoản: ${libraryManager.currentStudent.getRole()}", fontWeight = FontWeight.Bold)
                Text(text = "Đã mượn: ${libraryManager.currentStudent.getBorrowedBookIds().size} / ${libraryManager.currentStudent.getMaxBorrowLimit()} cuốn")
            }
        }

        // Danh sách sách
        Text(text = "Danh sách sách thư viện", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        message?.let {
            Surface(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = it, fontSize = 14.sp)
                    TextButton(onClick = { message = null }) { Text("Đóng") }
                }
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(libraryManager.books) { book ->
                val isBorrowedByCurrent = libraryManager.currentStudent.getBorrowedBookIds().contains(book.getId())
                val isBorrowedByAnyone = book.isBorrowed()

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isBorrowedByCurrent)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = book.getTitle(), fontWeight = FontWeight.Bold)
                            Text(text = book.getDetails(), fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }

                        if (isBorrowedByCurrent) {
                            Button(
                                onClick = {
                                    val success = libraryManager.returnBookForCurrentStudent(book)
                                    if (success) message = "Đã trả sách: ${book.getTitle()}"
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                            ) {
                                Text("Trả sách")
                            }
                        } else if (!isBorrowedByAnyone) {
                            Button(
                                onClick = {
                                    val success = libraryManager.borrowBookForCurrentStudent(book)
                                    if (success) {
                                        message = "Mượn thành công: ${book.getTitle()}"
                                    } else {
                                        message = "Không thể mượn (Đã đạt giới hạn hoặc lỗi)"
                                    }
                                }
                            ) {
                                Text("Mượn")
                            }
                        } else {
                            Text(
                                text = "Đã được mượn",
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookListScreen(
    libraryManager: LibraryManager,
    onAddBookClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Quản lý Sách (${libraryManager.books.size})", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Button(onClick = onAddBookClick) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Thêm sách")
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(libraryManager.books) { book ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = book.getTitle(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Badge { Text(book.getItemType()) }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = book.getDetails(), fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun StudentListScreen(
    libraryManager: LibraryManager,
    onAddStudentClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Danh sách Sinh viên (${libraryManager.students.size})", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Button(onClick = onAddStudentClick) {
                Icon(Icons.Default.PersonAdd, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Thêm SV")
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(libraryManager.students) { student ->
                val isCurrent = libraryManager.currentStudent == student
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isCurrent) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = student.getName(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Badge(containerColor = MaterialTheme.colorScheme.secondary) {
                                Text(student.getRole())
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Mã số: ${student.getUserId()}", fontSize = 14.sp)
                        Text(text = "Giới hạn mượn: ${student.getMaxBorrowLimit()} sách", fontSize = 14.sp)
                        Text(text = "Đang mượn: ${student.getBorrowedBookIds().size} sách", fontSize = 14.sp)

                        if (!isCurrent) {
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedButton(
                                onClick = { libraryManager.currentStudent = student },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("Chọn làm SV hiện tại")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OopInfoScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "4 Đặc trưng Lập trình hướng đối tượng (OOP)",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Hệ thống Quản lý Thư viện này được thiết kế và hiện thực đầy đủ 4 đặc trưng cốt lõi của OOP:",
                fontSize = 14.sp
            )
        }

        item {
            OopCard(
                title = "1. Tính trừu tượng (Abstraction)",
                description = "Sử dụng các lớp trừu tượng (Abstract Class) LibraryItem và User để định nghĩa các thuộc tính và phương thức chung (như getDetails(), getRole(), getMaxBorrowLimit()) mà không cần quan tâm chi tiết cụ thể ở tầng lớp cha."
            )
        }

        item {
            OopCard(
                title = "2. Tính kế thừa (Inheritance)",
                description = "• Book kế thừa từ LibraryItem.\n• TextBook (Sách giáo khoa) kế thừa đa cấp từ Book.\n• Student kế thừa từ User.\n• VIPStudent kế thừa từ Student.\nGiúp tái sử dụng mã nguồn và phân cấp rõ ràng."
            )
        }

        item {
            OopCard(
                title = "3. Tính đa hình (Polymorphism)",
                description = "• Phương thức getDetails() được ghi đè (override) ở Book và TextBook để hiển thị thông tin chi tiết riêng biệt.\n• Phương thức getMaxBorrowLimit() và getRole() được ghi đè ở VIPStudent để thay đổi giới hạn mượn sách (10 sách so với 3 sách của Student thường).\n• Xử lý danh sách chung List<LibraryItem> và List<User> một cách linh hoạt."
            )
        }

        item {
            OopCard(
                title = "4. Tính đóng gói (Encapsulation)",
                description = "Các thuộc tính quan trọng (id, title, author, isBorrowed, borrowedBookIds) được bảo vệ (protected / private) và chỉ được truy xuất/thay đổi thông qua các phương thức getter/setter hoặc các hàm nghiệp vụ an toàn (như borrowBook(), returnBook(), setTitle())."
            )
        }
    }
}

@Composable
fun OopCard(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = description, fontSize = 14.sp)
        }
    }
}
