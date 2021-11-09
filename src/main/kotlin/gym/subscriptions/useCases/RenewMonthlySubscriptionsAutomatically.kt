package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class RenewMonthlySubscriptionsAutomaticallyCommand(val asOfDate: String)

class RenewMonthlySubscriptionsAutomatically(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(command: RenewMonthlySubscriptionsAutomaticallyCommand): List<Subscription> {

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
