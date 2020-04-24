package gym.subscriptions.infrastructure

import gym.subscriptions.domain.SubscriptionId

class SubscriptionRepositoryException(override val message: String) : Throwable(message) {

    companion object {
        fun notFound(subscriptionId: SubscriptionId): SubscriptionRepositoryException {
            return SubscriptionRepositoryException("Subscription [$subscriptionId] not found.")
        }
    }
}
