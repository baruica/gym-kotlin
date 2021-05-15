package gym

import gym.subscriptions.domain.Subscription
import java.time.LocalDate
import java.util.*

fun monthlySubscription(
    basePrice: Int,
    startDate: LocalDate,
    isStudent: Boolean = false,
    subscriptionId: String = UUID.randomUUID().toString()
): Subscription {
    return newSubscription(
        subscriptionId,
        basePrice,
        startDate,
        1,
        isStudent
    )
}

fun yearlySubscription(
    basePrice: Int,
    startDate: LocalDate,
    isStudent: Boolean = false,
    subscriptionId: String = UUID.randomUUID().toString()
): Subscription {
    return newSubscription(
        subscriptionId,
        basePrice,
        startDate,
        12,
        isStudent
    )
}

fun newSubscription(
    subscriptionId: String,
    price: Int,
    startDate: LocalDate,
    durationInMonths: Int,
    isStudent: Boolean
): Subscription {
    return Subscription.subscribe(
        subscriptionId,
        durationInMonths,
        startDate,
        price,
        isStudent
    )
}
