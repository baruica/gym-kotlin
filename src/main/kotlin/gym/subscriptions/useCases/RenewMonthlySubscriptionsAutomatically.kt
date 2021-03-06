package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class RenewMonthlySubscriptionsAutomatically(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: RenewMonthlySubscriptionsAutomaticallyCommand): List<Subscription> {

        val endedMonthlySubscriptionsAsOf = subscriptionRepository.endedMonthlySubscriptions(
            LocalDate.parse(command.asOfDate)
        )

        endedMonthlySubscriptionsAsOf.forEach {
            it.renew()
        }

        subscriptionRepository.storeAll(endedMonthlySubscriptionsAsOf)

        return endedMonthlySubscriptionsAsOf
    }
}
