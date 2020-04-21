package gym.subscriptions.use_cases

import gym.plans.domain.PlanId
import java.time.LocalDate

data class SubscribeToPlanCommand(
    val planId: PlanId,
    val planPrice: Int,
    val planDurationInMonths: Int,
    val startDate: LocalDate,
    val isStudent: Boolean,
    val email: String
)
