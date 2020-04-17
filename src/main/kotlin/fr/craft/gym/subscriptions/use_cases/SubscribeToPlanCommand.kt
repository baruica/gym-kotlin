package fr.craft.gym.subscriptions.use_cases

import fr.craft.gym.membership.domain.EmailAddress
import fr.craft.gym.subscriptions.domain.ChosenPlan
import fr.craft.gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

data class SubscribeToPlanCommand(
    val chosenPlan: ChosenPlan,
    val subscriptionId: SubscriptionId,
    val startDate: LocalDate,
    val isStudent: Boolean,
    val email: EmailAddress
)
