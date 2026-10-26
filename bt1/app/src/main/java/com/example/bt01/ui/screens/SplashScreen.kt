package com.example.bt01.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt01.model.AppVersion
import com.example.bt01.ui.components.UthLogo
import kotlinx.coroutines.delay

/**
 * Start / Splash Screen for UTH Todo app.
 */
@Composable
fun SplashScreen(
    appVersion: AppVersion = remember { AppVersion(2, 1, 0) },
    onNavigateNext: () -> Unit
) {
    // Automatically navigate after 2.5 seconds delay
    LaunchedEffect(Unit) {
        delay(2500)
        onNavigateNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D52F1))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onNavigateNext()
            }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        // Main Logo & Branding content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UthLogo(
                size = 96.dp,
                isDarkTheme = true
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "UTH Todo",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Việc học, việc nhà – gọn gàng mỗi ngày",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

        // Bottom version info section using OOP AppVersion
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = appVersion.getFormattedVersion(),
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
