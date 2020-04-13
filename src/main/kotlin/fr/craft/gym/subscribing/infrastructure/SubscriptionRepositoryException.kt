package fr.craft.gym.subscribing.infrastructure

import fr.craft.gym.subscribing.domain.SubscriptionId

class SubscriptionRepositoryException(override val message: String) : Throwable(message) {

    companion object {
        fun notFound(subscriptionId: SubscriptionId): SubscriptionRepositoryException {
            return SubscriptionRepositoryException("Subscription [$subscriptionId] not found.")
        }
    }
}
