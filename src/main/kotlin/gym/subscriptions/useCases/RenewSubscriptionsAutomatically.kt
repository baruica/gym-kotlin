package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class RenewSubscriptionsAutomatically(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: RenewSubscriptionsAutomaticallyCommand): List<Subscription> {

        val endedSubscriptionsAsOf = subscriptionRepository.endedSubscriptions(LocalDate.parse(command.asOfDate))

        endedSubscriptionsAsOf.map {
            it.renew()
        }

        subscriptionRepository.storeAll(endedSubscriptionsAsOf)

        return endedSubscriptionsAsOf
    }
}
