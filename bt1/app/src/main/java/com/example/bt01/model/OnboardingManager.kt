package com.example.bt01.model

/**
 * OOP Manager class that handles onboarding step data and navigation flow logic.
 */
class OnboardingManager {
    private val steps: List<OnboardingStep> = listOf(
        OnboardingStep.ChecklistStep(),
        OnboardingStep.CategoryStep(),
        OnboardingStep.NotificationStep()
    )

    fun getStep(stepNumber: Int): OnboardingStep {
        return steps.find { it.stepNumber == stepNumber } ?: steps.first()
    }

    fun getAllSteps(): List<OnboardingStep> {
        return steps
    }

    fun getTotalSteps(): Int {
        return steps.size
    }

    fun getNextStepNumber(currentStep: Int): Int? {
        return if (currentStep < getTotalSteps()) currentStep + 1 else null
    }

    fun getPreviousStepNumber(currentStep: Int): Int? {
        return if (currentStep > 1) currentStep - 1 else null
    }
}
