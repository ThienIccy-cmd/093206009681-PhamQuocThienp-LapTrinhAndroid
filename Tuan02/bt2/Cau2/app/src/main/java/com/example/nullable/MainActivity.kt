package com.example.nullable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Bước 1: class Student, email và phone có thể null
class Student(val name: String, val email: String?, val phone: String?)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { StudentListScreen() } }
    }
}

// Hiển thị 1 dòng, null thì dùng ?: thay bằng "Chưa cập nhật"
@Composable
fun Field(label: String, value: String?) {
    Row {
        Text("$label: ", fontSize = 12.sp, color = Color.Gray)
        Text(
            text = value ?: "Chưa cập nhật",
            fontSize = 12.sp,
            color = if (value == null) Color.Red else Color.Gray,
            fontStyle = if (value == null) FontStyle.Italic else FontStyle.Normal
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen() {
    // Bước 2: list 5 sinh viên
    val students = listOf(
        Student("Nguyễn Văn An", "an@sv.edu.vn", "0901 234 567"),
        Student("Trần Thị Bình", null, "0912 888 999"),
        Student("Lê Minh Chi", "chi@sv.edu.vn", null),
        Student("Phạm Quốc Dũng", null, null),
        Student("Võ Thanh Em", "em@sv.edu.vn", "0933 111 222")
    )

    // Bước 5: dùng for để đếm sinh viên thiếu email
    var missingEmail = 0
    for (s in students) {
        if (s.email == null) missingEmail++
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách sinh viên") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2B4380),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(Color(0xFFF1F3F9))
                .fillMaxSize()
                .padding(12.dp)
        ) {
            // Bước 3: dùng for để tạo các thẻ sinh viên
            for (s in students) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0x1F000000), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text(s.name, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    Field("Email", s.email)
                    Field("SĐT", s.phone)
                }
                Spacer(Modifier.height(10.dp))
            }

            // Thanh tổng kết cuối danh sách
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE3E9F5), RoundedCornerShape(8.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Tổng: ${students.size} sinh viên", fontWeight = FontWeight.Bold)
                Text("Thiếu email: $missingEmail", color = Color.Red, fontWeight = FontWeight.Bold)
            }
        }
    }
}