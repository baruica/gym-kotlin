package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class RenewMonthlySubscriptionsAutomatically(val asOfDate: String)

class RenewMonthlySubscriptionsAutomaticallyHandler(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(command: RenewMonthlySubscriptionsAutomatically): List<Subscription> {

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
