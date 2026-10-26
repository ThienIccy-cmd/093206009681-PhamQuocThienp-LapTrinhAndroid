package com.example.bt01.model

/**
 * Abstract OOP base class representing an Onboarding step in UTH Todo.
 */
sealed class OnboardingStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val buttonText: String
) {
    val isLastStep: Boolean
        get() = stepNumber == TOTAL_STEPS

    companion object {
        const val TOTAL_STEPS = 3
    }

    /**
     * Subclass representing Onboarding Step 1 (Checklist feature).
     */
    class ChecklistStep : OnboardingStep(
        stepNumber = 1,
        title = "Mọi việc, gọn trong một chỗ",
        description = "Ghi lại bài tập, việc nhà, lịch hẹn. Không còn quên việc nào.",
        buttonText = "Tiếp tục"
    )

    /**
     * Subclass representing Onboarding Step 2 (Category categorization).
     */
    class CategoryStep : OnboardingStep(
        stepNumber = 2,
        title = "Chia theo Học tập và Cuộc sống",
        description = "Mỗi việc có nhóm riêng, nhìn là biết hôm nay cần lo gì.",
        buttonText = "Tiếp tục"
    )

    /**
     * Subclass representing Onboarding Step 3 (Notification reminder).
     */
    class NotificationStep : OnboardingStep(
        stepNumber = 3,
        title = "Nhắc đúng lúc",
        description = "Bật thông báo để UTH Todo nhắc bạn trước hạn chót.",
        buttonText = "Bắt đầu ngay"
    )
}
