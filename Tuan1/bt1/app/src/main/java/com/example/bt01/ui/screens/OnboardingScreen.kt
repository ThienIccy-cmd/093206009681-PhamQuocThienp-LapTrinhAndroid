package com.example.bt01.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt01.model.OnboardingManager
import com.example.bt01.ui.components.OnboardingGraphic
import com.example.bt01.ui.components.PageIndicator
import com.example.bt01.ui.components.UthLogo

/**
 * Onboarding screen displaying steps using OOP OnboardingStep and OnboardingManager.
 */
@Composable
fun OnboardingScreen(
    stepNumber: Int,
    onboardingManager: OnboardingManager = remember { OnboardingManager() },
    onNextStep: (Int) -> Unit,
    onFinishOnboarding: () -> Unit
) {
    val currentStep = onboardingManager.getStep(stepNumber)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Header: Logo + Title on left, "Bỏ qua" (Skip) on right
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    UthLogo(
                        size = 36.dp,
                        isDarkTheme = false
                    )
                    Text(
                        text = "UTH Todo",
                        color = Color(0xFF1E293B),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                TextButton(onClick = onFinishOnboarding) {
                    Text(
                        text = "Bỏ qua",
                        color = Color(0xFF64748B),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Middle Section: Graphic + Step Title + Description
            AnimatedContent(
                targetState = currentStep,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "onboardingAnimation"
            ) { step ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    OnboardingGraphic(
                        stepNumber = step.stepNumber
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = step.title,
                        color = Color(0xFF0F172A),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 32.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = step.description,
                        color = Color(0xFF64748B),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 22.sp
                    )
                }
            }

            // Bottom Section: Page Indicators + Action Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PageIndicator(
                    totalSteps = onboardingManager.getTotalSteps(),
                    currentStep = currentStep.stepNumber,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Button(
                    onClick = {
                        val nextStep = onboardingManager.getNextStepNumber(currentStep.stepNumber)
                        if (nextStep != null) {
                            onNextStep(nextStep)
                        } else {
                            onFinishOnboarding()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2D52F1)
                    )
                ) {
                    Text(
                        text = currentStep.buttonText,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
