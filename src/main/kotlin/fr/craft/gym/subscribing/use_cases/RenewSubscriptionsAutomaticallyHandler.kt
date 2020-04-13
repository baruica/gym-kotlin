package fr.craft.gym.subscribing.use_cases

import fr.craft.gym.subscribing.domain.SubscriptionRepository
import fr.craft.gym.subscribing.domain.SubscriptionsRenewedAutomatically

class RenewSubscriptionsAutomaticallyHandler(private val subscriptionRepository: SubscriptionRepository) {

    fun handle(command: RenewSubscriptionsAutomatically): SubscriptionsRenewedAutomatically {

        val endedSubscriptionsAsOf = subscriptionRepository.endedSubscriptions(command.asOfDate)

        endedSubscriptionsAsOf.mapValues {
            it.value.renew()
        }

        subscriptionRepository.storeAll(endedSubscriptionsAsOf)

        return SubscriptionsRenewedAutomatically(
            endedSubscriptionsAsOf.keys.toList()
        )
    }
}
