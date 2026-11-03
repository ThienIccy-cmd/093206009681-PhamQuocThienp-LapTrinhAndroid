package com.example.bt1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt1.data.AuthViewModel

data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val time: String,
    val isRead: Boolean = false
)

@Composable
fun NotificationsScreen(
    authViewModel: AuthViewModel,
    onBack: () -> Unit
) {
    val tasks by authViewModel.tasks.collectAsState()

    val notificationList = remember(tasks) {
        listOf(
            NotificationItem(
                id = "1",
                title = "Nhắc việc: Tập thể dục 30 phút",
                description = "Đã đến lịch tập thể dục buổi sáng lúc 06:30.",
                time = "Hôm nay, 06:30",
                isRead = true
            ),
            NotificationItem(
                id = "2",
                title = "Hạn nộp: Nộp bài tập Lập trình di động",
                description = "Công việc học tập cần hoàn thành trước 09:00 hôm nay.",
                time = "Hôm nay, 08:30",
                isRead = false
            ),
            NotificationItem(
                id = "3",
                title = "Hệ thống UTH Todo",
                description = "Bạn có công việc cần hoàn thành trong ngày hôm nay. Hãy cố gắng hoàn thành đúng hạn nhé!",
                time = "Hôm qua",
                isRead = true
            )
        )
    }

    var pushEnabled by remember { mutableStateOf(true) }
    var studyReminder by remember { mutableStateOf(true) }
    var lifeReminder by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = Color(0xFFF8FAFC)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }

            // 1. Top Bar
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onBack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = "Back",
                            tint = Color(0xFF2563EB),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Trang chủ",
                            color = Color(0xFF2563EB),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Text(
                        text = "Thông báo & Nhắc việc",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 60.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // 2. Settings Card (User-friendly reminder settings, no technical FCM stuff)
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Cài đặt nhắc nhở",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1E293B)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Nhận thông báo đẩy", fontSize = 14.sp, color = Color(0xFF475569))
                            Switch(
                                checked = pushEnabled,
                                onCheckedChange = { pushEnabled = it },
                                colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF2563EB))
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Nhắc việc học tập hàng ngày", fontSize = 14.sp, color = Color(0xFF475569))
                            Switch(
                                checked = studyReminder,
                                onCheckedChange = { studyReminder = it },
                                colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF2563EB))
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Nhắc việc cuộc sống", fontSize = 14.sp, color = Color(0xFF475569))
                            Switch(
                                checked = lifeReminder,
                                onCheckedChange = { lifeReminder = it },
                                colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFF2563EB))
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Lịch sử thông báo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
            }

            // 3. Notification List items
            items(notificationList) { notification ->
                NotificationCard(notification = notification)
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (notification.isRead) Color.White else Color(0xFFEFF6FF)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (notification.isRead) Color(0xFFF1F5F9) else Color(0xFFDBEAFE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (notification.isRead) Icons.Outlined.Notifications else Icons.Default.NotificationsActive,
                    contentDescription = null,
                    tint = if (notification.isRead) Color(0xFF64748B) else Color(0xFF2563EB),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = notification.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF1E293B),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = notification.time,
                        fontSize = 11.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.description,
                    fontSize = 13.sp,
                    color = Color(0xFF475569)
                )
            }
        }
    }
}
