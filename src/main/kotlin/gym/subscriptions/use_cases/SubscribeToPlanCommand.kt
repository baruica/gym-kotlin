package gym.subscriptions.use_cases

import gym.subscriptions.domain.ChosenPlan
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

data class SubscribeToPlanCommand(
    val chosenPlan: ChosenPlan,
    val subscriptionId: SubscriptionId,
    val startDate: LocalDate,
    val isStudent: Boolean,
    val email: String
)
