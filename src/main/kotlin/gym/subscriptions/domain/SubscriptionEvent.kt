package gym.subscriptions.domain

import common.DomainEvent

sealed class SubscriptionEvent(override val aggregateId: String) : DomainEvent

data class NewSubscription(
    val subscriptionId: String,
    val subscriptionStartDate: String,
    val email: String
) : SubscriptionEvent(subscriptionId)

data class SubscriptionRenewed(
    val subscriptionId: String,
    val oldEndDate: String,
    val newEndDate: String
) : SubscriptionEvent(subscriptionId)
