package gym.subscriptions.use_cases

import common.DomainEvent
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class RenewSubscriptionsAutomatically(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: RenewSubscriptionsAutomaticallyCommand): List<DomainEvent> {

        val endedSubscriptionsAsOf = subscriptionRepository.endedSubscriptions(LocalDate.parse(command.asOfDate))

        endedSubscriptionsAsOf.map {
            it.renew()
        }

        subscriptionRepository.storeAll(endedSubscriptionsAsOf)

        return endedSubscriptionsAsOf.map {
            it.raisedEvents.last()
        }
    }
}
