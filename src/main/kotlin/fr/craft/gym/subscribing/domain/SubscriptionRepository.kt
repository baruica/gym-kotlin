package fr.craft.gym.subscribing.domain

import java.time.LocalDate

interface SubscriptionRepository {

    fun nextId(): SubscriptionId

    fun store(subscription: Subscription)

    fun storeAll(subscriptions: Map<SubscriptionId, Subscription>)

    fun get(subscriptionId: SubscriptionId): Subscription

    fun endedSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription>

    fun onGoingSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription>
}
