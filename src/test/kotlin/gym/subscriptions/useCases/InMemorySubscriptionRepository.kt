package gym.subscriptions.useCases

import InMemoryRepository
import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class InMemorySubscriptionRepository : InMemoryRepository<Subscription>(), SubscriptionRepository {

    override fun endedMonthlySubscriptions(date: LocalDate): List<Subscription> {
        return aggregates
            .filter { it.value.isMonthly() }
            .filter { it.value.willBeEndedAsFrom(date) }
            .values.map { it }
    }

    override fun threeYearsAnniversarySubscriptions(date: LocalDate): List<Subscription> {
        return aggregates
            .filter { it.value.hasThreeYearsAnniversaryAfter(date) }
            .values.map { it }
    }

    override fun onGoingSubscriptions(date: LocalDate): List<Subscription> {
        return aggregates.filter {
            it.value.isOngoing(date)
        }.values.map { it }
    }
}
