package com.example.grade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GradeScreen()
        }
    }
}

// 1 & 2. Hàm nhận điểm và trả về xếp loại
fun getXepLoai(diem: Double): String {
    return if (diem < 0 || diem > 10) {
        "Không hợp lệ"
    } else if (diem >= 8.0) {
        "Giỏi"
    } else if (diem >= 6.5) {
        "Khá"
    } else if (diem >= 5.0) {
        "Trung bình"
    } else {
        "Yếu"
    }
}

// 3. Hàm chọn màu chữ dựa vào xếp loại
fun getMauChu(xepLoai: String): Color {
    return if (xepLoai == "Giỏi") Color(0xFF2E7D32)
    else if (xepLoai == "Khá") Color(0xFF1976D2)
    else if (xepLoai == "Trung bình") Color(0xFFE65100)
    else if (xepLoai == "Yếu") Color(0xFFC62828)
    else Color(0xFF757575)
}

// Hàm chọn màu nền dựa vào xếp loại
fun getMauNen(xepLoai: String): Color {
    return if (xepLoai == "Giỏi") Color(0xFFE8F5E9)
    else if (xepLoai == "Khá") Color(0xFFE3F2FD)
    else if (xepLoai == "Trung bình") Color(0xFFFFF3E0)
    else if (xepLoai == "Yếu") Color(0xFFFFEBEE)
    else Color(0xFFEEEEEE)
}

@Composable
fun GradeScreen() {
    // 1. Biến điểm 8.5
    val diem = 8.5
    // 4. Danh sách các điểm cần kiểm tra thêm
    val danhSachDiem = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F6FB))
            .statusBarsPadding()
    ) {
        // Thanh tiêu đề TopBar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1B3B6F))
                .padding(16.dp)
        ) {
            Text(
                text = "Xếp loại học lực",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            // Thẻ điểm chính
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Điểm trung bình", color = Color.Gray, fontSize = 14.sp)
                    Text(text = "8.5", fontSize = 40.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(12.dp))

                    val xepLoai = getXepLoai(diem)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(getMauNen(xepLoai), RoundedCornerShape(8.dp))
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = xepLoai,
                            color = getMauChu(xepLoai),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Phần Kiểm tra thêm
            Text(
                text = "KIỂM TRA THÊM",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    for (d in danhSachDiem) {
                        val xl = getXepLoai(d)
                        val tenDiem = if (d % 1.0 == 0.0) d.toInt().toString() else d.toString()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Điểm $tenDiem", fontSize = 15.sp)

                            Box(
                                modifier = Modifier
                                    .background(getMauNen(xl), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = xl,
                                    color = getMauChu(xl),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GradeScreenPreview() {
    GradeScreen()
}