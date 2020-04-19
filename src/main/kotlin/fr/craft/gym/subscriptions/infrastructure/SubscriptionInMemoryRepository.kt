package fr.craft.gym.subscriptions.infrastructure

import fr.craft.gym.subscriptions.domain.Subscription
import fr.craft.gym.subscriptions.domain.SubscriptionId
import fr.craft.gym.subscriptions.domain.SubscriptionRepository
import fr.craft.gym.subscriptions.domain.SubscriptionRepositoryException
import java.time.LocalDate
import java.util.HashMap
import java.util.UUID

class SubscriptionInMemoryRepository : SubscriptionRepository {

    private val subscriptions = HashMap<SubscriptionId, Subscription>()

    override fun nextId(): SubscriptionId {
        return SubscriptionId(UUID.randomUUID().toString())
    }

    override fun store(subscription: Subscription) {
        subscriptions[subscription.id] = subscription
    }

    override fun storeAll(subscriptions: Map<SubscriptionId, Subscription>) {
        subscriptions.forEach {
            store(it.value)
        }
    }

    override fun get(subscriptionId: SubscriptionId): Subscription {
        return subscriptions[subscriptionId]
            ?: throw SubscriptionRepositoryException.notFound(subscriptionId)
    }

    override fun endedSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription> {
        return subscriptions.filterValues {
            it.willBeEnded(asOfDate)
        }
    }

    override fun onGoingSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription> {
        return subscriptions.filterValues {
            it.isOngoing(asOfDate)
        }
    }
}