package fr.craft.gym.subscriptions.domain

class SubscriptionRepositoryException(override val message: String) : Throwable(message) {

    companion object {
        fun notFound(subscriptionId: SubscriptionId): SubscriptionRepositoryException {
            return SubscriptionRepositoryException("Subscription [$subscriptionId] not found.")
        }
    }
}
