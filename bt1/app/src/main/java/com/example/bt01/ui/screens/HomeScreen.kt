package com.example.bt01.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt01.model.TaskCategory
import com.example.bt01.model.TaskItem
import com.example.bt01.model.TaskManager
import com.example.bt01.ui.components.UthLogo

/**
 * Main Todo Home Screen using OOP TaskManager and TaskItem models.
 */
@Composable
fun HomeScreen(
    taskManager: TaskManager = remember { TaskManager() },
    onRestartOnboarding: () -> Unit
) {
    var tasks by remember { mutableStateOf(taskManager.getInitialTasks()) }
    var selectedCategoryId by remember { mutableStateOf<String?>(null) }

    val categories = remember { taskManager.getCategories() }

    val filteredTasks = remember(tasks, selectedCategoryId) {
        if (selectedCategoryId == null) tasks
        else tasks.filter { it.category.id == selectedCategoryId }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(20.dp)
    ) {
        // Top App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                UthLogo(size = 40.dp, isDarkTheme = false)
                Column {
                    Text(
                        text = "UTH Todo",
                        color = Color(0xFF0F172A),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Danh sách việc cần làm",
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                }
            }

            // Button to restart onboarding to test navigation
            OutlinedButton(
                onClick = onRestartOnboarding,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(
                    text = "Onboarding",
                    fontSize = 12.sp,
                    color = Color(0xFF2D52F1)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Category Filter Pills
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // All filter
            CategoryFilterChip(
                title = "Tất cả",
                isSelected = selectedCategoryId == null,
                color = Color(0xFF2D52F1),
                onClick = { selectedCategoryId = null }
            )

            // Dynamic categories from OOP model
            categories.forEach { category ->
                CategoryFilterChip(
                    title = category.name,
                    isSelected = selectedCategoryId == category.id,
                    color = Color(category.colorHex),
                    onClick = { selectedCategoryId = category.id }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Nhiệm vụ hôm nay (${filteredTasks.size})",
            color = Color(0xFF0F172A),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Task List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredTasks, key = { it.id }) { task ->
                TaskCard(
                    task = task,
                    onToggleComplete = {
                        tasks = tasks.map {
                            if (it.id == task.id) it.copyWithToggle() else it
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryFilterChip(
    title: String,
    isSelected: Boolean,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) color else Color.White)
            .border(
                width = 1.dp,
                color = if (isSelected) color else Color(0xFFE2E8F0),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            color = if (isSelected) Color.White else Color(0xFF64748B),
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}

@Composable
private fun TaskCard(
    task: TaskItem,
    onToggleComplete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggleComplete),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Checkbox Circle
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(if (task.isCompleted) Color(0xFF2D52F1) else Color.Transparent)
                    .border(
                        width = 2.dp,
                        color = if (task.isCompleted) Color(0xFF2D52F1) else Color(0xFFCBD5E1),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (task.isCompleted) {
                    Canvas(modifier = Modifier.size(14.dp)) {
                        val path = Path().apply {
                            moveTo(size.width * 0.2f, size.height * 0.5f)
                            lineTo(size.width * 0.45f, size.height * 0.75f)
                            lineTo(size.width * 0.85f, size.height * 0.25f)
                        }
                        drawPath(
                            path = path,
                            color = Color.White,
                            style = Stroke(width = 2.dp.toPx())
                        )
                    }
                }
            }

            // Task details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    color = if (task.isCompleted) Color(0xFF94A3B8) else Color(0xFF0F172A),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Category Tag
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(task.category.colorHex).copy(alpha = 0.12f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = task.category.name,
                            color = Color(task.category.colorHex),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Text(
                        text = task.dueTime,
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
