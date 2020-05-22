package gym.subscriptions.infrastructure

import common.Aggregate
import common.AggregateId
import common.RepositoryException
import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionId
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate
import java.util.*

class SubscriptionInMemoryRepository : SubscriptionRepository {

    private val subscriptions = mutableMapOf<SubscriptionId, Subscription>()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun store(aggregate: Aggregate) {
        subscriptions[aggregate.id as SubscriptionId] = aggregate as Subscription
    }

    override fun storeAll(aggregates: Map<out AggregateId, Aggregate>) {
        aggregates.forEach {
            store(it.value)
        }
    }

    override fun get(aggregateId: AggregateId): Aggregate {
        return subscriptions[aggregateId]
            ?: throw RepositoryException.notFound(aggregateId)
    }

    override fun endedSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription> {
        return subscriptions.filter {
            it.value.willBeEndedAfter(asOfDate)
        }
    }

    override fun onGoingSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription> {
        return subscriptions.filter {
            it.value.isOngoing(asOfDate)
        }
    }
}
