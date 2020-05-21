package gym.subscriptions.infrastructure

import common.InMemoryRepository
import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionId
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class SubscriptionInMemoryRepository : InMemoryRepository(), SubscriptionRepository {

    override fun endedSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription> {
        return aggregates.filter {
            (it.value as Subscription).willBeEndedAfter(asOfDate)
        } as Map<SubscriptionId, Subscription>
    }

    override fun onGoingSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription> {
        return aggregates.filter {
            (it.value as Subscription).isOngoing(asOfDate)
        } as Map<SubscriptionId, Subscription>
    }
}
