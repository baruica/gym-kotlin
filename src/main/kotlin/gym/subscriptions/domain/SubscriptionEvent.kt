package gym.subscriptions.domain

sealed class SubscriptionEvent(val aggregateId: String) {
    data class NewSubscription(
        val subscriptionId: String,
        val subscriptionStartDate: String,
        val email: String
    ) : SubscriptionEvent(subscriptionId)

    data class SubscriptionRenewed(
        val subscriptionId: String,
        val oldEndDate: String,
        val newEndDate: String
    ) : SubscriptionEvent(subscriptionId)
}
