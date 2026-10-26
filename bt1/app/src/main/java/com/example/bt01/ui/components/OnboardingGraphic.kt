package com.example.bt01.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Renders graphic container for each onboarding step based on design image.
 */
@Composable
fun OnboardingGraphic(
    stepNumber: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = Color.Black.copy(alpha = 0.06f)
            )
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(24.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        when (stepNumber) {
            1 -> Step1Graphic()
            2 -> Step2Graphic()
            3 -> Step3Graphic()
            else -> Step1Graphic()
        }
    }
}

/**
 * Step 1: Checklist preview (3 item card representation).
 */
@Composable
private fun Step1Graphic() {
    Column(
        modifier = Modifier.fillMaxWidth(0.85f),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Item 1: Checked item with light blue background
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEFF6FF))
                .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2D52F1)),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(12.dp)) {
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
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth(0.7f)
                    .clip(CircleShape)
                    .background(Color(0xFF93C5FD))
            )
        }

        // Item 2: Unchecked item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF8FAFC))
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFFCBD5E1), CircleShape)
            )
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth(0.8f)
                    .clip(CircleShape)
                    .background(Color(0xFFE2E8F0))
            )
        }

        // Item 3: Unchecked item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF8FAFC))
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFFCBD5E1), CircleShape)
            )
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth(0.5f)
                    .clip(CircleShape)
                    .background(Color(0xFFE2E8F0))
            )
        }
    }
}

/**
 * Step 2: Categories preview ("Học tập" and "Cuộc sống").
 */
@Composable
private fun Step2Graphic() {
    Column(
        modifier = Modifier.fillMaxWidth(0.85f),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Category 1: "Học tập"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFEFF6FF))
                .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF2D52F1))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Học tập",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Category 2: "Cuộc sống"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFFFF7ED))
                .border(1.dp, Color(0xFFFFEDD5), RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF97316))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Cuộc sống",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/**
 * Step 3: Notification preview (Bell icon + "Ôn bài Toán lúc 19:00").
 */
@Composable
private fun Step3Graphic() {
    Column(
        modifier = Modifier.fillMaxWidth(0.85f),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bell Icon Circle
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFEFF6FF)),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(28.dp)) {
                val path = Path().apply {
                    // Bell dome
                    moveTo(size.width * 0.5f, size.height * 0.15f)
                    cubicTo(
                        size.width * 0.25f, size.height * 0.15f,
                        size.width * 0.2f, size.height * 0.45f,
                        size.width * 0.2f, size.height * 0.65f
                    )
                    lineTo(size.width * 0.1f, size.height * 0.75f)
                    lineTo(size.width * 0.9f, size.height * 0.75f)
                    lineTo(size.width * 0.8f, size.height * 0.65f)
                    cubicTo(
                        size.width * 0.8f, size.height * 0.45f,
                        size.width * 0.75f, size.height * 0.15f,
                        size.width * 0.5f, size.height * 0.15f
                    )
                }
                drawPath(
                    path = path,
                    color = Color(0xFF2D52F1),
                    style = Stroke(width = 2.5.dp.toPx())
                )
                // Bell clapper
                drawCircle(
                    color = Color(0xFF2D52F1),
                    radius = 3.dp.toPx(),
                    center = Offset(size.width * 0.5f, size.height * 0.88f)
                )
            }
        }

        // Notification Banner Pill
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF1F5F9))
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Ôn bài Toán lúc 19:00",
                color = Color(0xFF1E293B),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
