package fr.craft.gym.subscriptions.use_cases

import fr.craft.gym.subscriptions.domain.SubscriptionRepository
import fr.craft.gym.subscriptions.domain.SubscriptionsRenewedAutomatically

class RenewSubscriptionsAutomatically(private val subscriptionRepository: SubscriptionRepository) {

    fun handle(command: RenewSubscriptionsAutomaticallyCommand): SubscriptionsRenewedAutomatically {

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
