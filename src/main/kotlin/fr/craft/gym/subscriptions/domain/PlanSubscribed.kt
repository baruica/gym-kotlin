package fr.craft.gym.subscriptions.domain

import fr.craft.gym.membership.domain.EmailAddress
import java.time.LocalDate

data class PlanSubscribed(
    val subscriptionId: SubscriptionId,
    val subscriptionStartDate: LocalDate,
    val email: EmailAddress
)
