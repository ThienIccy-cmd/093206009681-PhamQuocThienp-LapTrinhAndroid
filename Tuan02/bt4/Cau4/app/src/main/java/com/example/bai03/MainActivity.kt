package com.example.bai03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai03.ui.theme.Bai03Theme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai03Theme {
                GradeScreen()
            }
        }
    }
}

data class Student(
    val name: String,
    val score: Double?
)

fun getRank(score: Double?): String {
    if (score == null) return "Chưa có điểm"
    return when {
        score >= 8.0 -> "Giỏi"
        score >= 6.5 -> "Khá"
        score >= 5.0 -> "Trung bình"
        else -> "Yếu"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeScreen() {
    val students = listOf(
        Student("Nguyễn Văn An", 9.0),
        Student("Trần Thị Bình", 7.2),
        Student("Lê Minh Chi", null),
        Student("Phạm Quốc Dũng", 4.5),
        Student("Võ Thanh Em", 5.8)
    )

    var totalScore = 0.0
    var countValid = 0
    var countGioi = 0
    var countNoScore = 0
    var maxScore = 0.0

    for (s in students) {
        if (s.score == null) {
            countNoScore++
        }
        else {
            totalScore += s.score
            countValid++

            if (s.score >= 8.0) countGioi++
            if (s.score > maxScore) maxScore = s.score
        }
    }

    val avgScore = if (countValid > 0) totalScore / countValid else 0.0

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Bảng điểm lớp",
                        color = Color.White,
                        fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E293B))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF8FAFC))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Summary Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E293B), shape = MaterialTheme.shapes.medium)
                    .padding(20.dp)
            ) {
                Column {
                    Text(text = "Điểm trung bình lớp", color = Color.LightGray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = String.format(Locale.US, "%.2f", avgScore),
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Giỏi", color = Color.LightGray, fontSize = 12.sp)
                            Text(text = "$countGioi", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "Chưa có điểm", color = Color.LightGray, fontSize = 12.sp)
                            Text(text = "$countNoScore", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "Cao nhất", color = Color.LightGray, fontSize = 12.sp)
                            Text(text = if (maxScore > 0) "$maxScore" else "—", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "DANH SÁCH · ${students.size} SINH VIÊN",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            for (s in students) {
                val rank = getRank(s.score)
                val rankColor = when (rank) {
                    "Giỏi" -> Color(0xFF15803D)
                    "Khá" -> Color(0xFF1D4ED8)
                    "Trung bình" -> Color(0xFFB45309)
                    "Yếu" -> Color(0xFFB91C1C)
                    else -> Color.Gray
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = s.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF0F172A))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = rank, color = rankColor, fontSize = 14.sp)
                        }
                        Text(
                            text = if (s.score != null) "${s.score}" else "—",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = if (s.score != null) Color(0xFF0F172A) else Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GradeScreenPreview() {
    Bai03Theme {
        GradeScreen()
    }
}
