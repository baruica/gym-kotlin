package gym.subscriptions.use_cases

import gym.Event
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class RenewSubscriptionsAutomatically(private val subscriptionRepository: SubscriptionRepository) {

    fun handle(command: RenewSubscriptionsAutomaticallyCommand): List<Event> {

        val endedSubscriptionsAsOf = subscriptionRepository.endedSubscriptions(LocalDate.parse(command.asOfDate))

        endedSubscriptionsAsOf.mapValues {
            it.value.renew()
        }

        subscriptionRepository.storeAll(endedSubscriptionsAsOf)

        return endedSubscriptionsAsOf.values.map {
            it.raisedEvents.last()
        }
    }
}
