package fr.craft.gym.subscribing.use_cases

import fr.craft.gym.subscribing.domain.ChosenPlan
import fr.craft.gym.subscribing.domain.SubscriptionId
import java.time.LocalDate

data class SubscribeToPlan(
    val chosenPlan: ChosenPlan,
    val subscriptionId: SubscriptionId,
    val startDate: LocalDate,
    val isStudent: Boolean,
    val email: String
)
