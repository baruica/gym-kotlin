package gym.subscriptions.domain

import common.DomainEvent
import java.time.Instant

sealed class SubscriptionEvent : DomainEvent {
    override fun aggregateId(): String = subscriptionId
    override val created: Instant = Instant.now()

    abstract val subscriptionId: String
}

data class NewSubscription(
    override val subscriptionId: String,
    val subscriptionStartDate: String,
    val email: String
) : SubscriptionEvent()

data class SubscriptionRenewed(
    override val subscriptionId: String,
    val oldEndDate: String,
    val newEndDate: String
) : SubscriptionEvent()
