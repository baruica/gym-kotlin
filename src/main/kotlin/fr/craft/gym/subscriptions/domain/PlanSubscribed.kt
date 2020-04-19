package fr.craft.gym.subscriptions.domain

import java.time.LocalDate

data class PlanSubscribed(
    val subscriptionId: SubscriptionId,
    val subscriptionStartDate: LocalDate,
    val email: String
)
