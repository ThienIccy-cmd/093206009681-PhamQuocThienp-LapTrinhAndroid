package com.example.bt01.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Custom UTH Todo Logo icon matching the design in the image.
 * @param size size of the logo box
 * @param isDarkTheme if true (for splash), logo box is white with blue elements. If false (top bar), logo box is blue with white elements.
 */
@Composable
fun UthLogo(
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    isDarkTheme: Boolean = false
) {
    val containerBg = if (isDarkTheme) Color.White else Color(0xFF2D52F1)
    val elementColor = if (isDarkTheme) Color(0xFF2D52F1) else Color.White

    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(size * 0.28f))
            .background(containerBg)
            .padding(size * 0.2f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(size * 0.08f),
            horizontalAlignment = Alignment.Start
        ) {
            // Row 1: Check circle + text bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(size * 0.08f)
            ) {
                Box(
                    modifier = Modifier
                        .size(size * 0.18f)
                        .clip(CircleShape)
                        .background(elementColor),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize().padding(2.dp)) {
                        val path = Path().apply {
                            moveTo(size.toPx() * 0.03f, size.toPx() * 0.07f)
                            lineTo(size.toPx() * 0.06f, size.toPx() * 0.10f)
                            lineTo(size.toPx() * 0.11f, size.toPx() * 0.04f)
                        }
                        drawPath(
                            path = path,
                            color = containerBg,
                            style = Stroke(width = size.toPx() * 0.025f)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .height(size * 0.07f)
                        .width(size * 0.35f)
                        .clip(CircleShape)
                        .background(elementColor)
                )
            }

            // Row 2: Empty circle + text bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(size * 0.08f)
            ) {
                Box(
                    modifier = Modifier
                        .size(size * 0.18f)
                        .clip(CircleShape)
                        .background(elementColor.copy(alpha = 0.5f))
                )
                Box(
                    modifier = Modifier
                        .height(size * 0.07f)
                        .width(size * 0.42f)
                        .clip(CircleShape)
                        .background(elementColor.copy(alpha = 0.5f))
                )
            }

            // Row 3: Empty circle + text bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(size * 0.08f)
            ) {
                Box(
                    modifier = Modifier
                        .size(size * 0.18f)
                        .clip(CircleShape)
                        .background(elementColor.copy(alpha = 0.5f))
                )
                Box(
                    modifier = Modifier
                        .height(size * 0.07f)
                        .width(size * 0.28f)
                        .clip(CircleShape)
                        .background(elementColor.copy(alpha = 0.5f))
                )
            }
        }
    }
}
