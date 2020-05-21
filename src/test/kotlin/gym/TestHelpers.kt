package gym

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate
import java.util.*

fun fifthOfJune(): LocalDate = LocalDate.parse("2018-06-05")

fun monthlySubscription(
    basePrice: Int,
    startDate: LocalDate,
    isStudent: Boolean = false,
    subscriptionId: SubscriptionId = SubscriptionId(UUID.randomUUID().toString())
): Subscription {
    return newSubscription(subscriptionId, basePrice, startDate, 1, isStudent)
}

fun yearlySubscription(
    basePrice: Int,
    startDate: LocalDate,
    isStudent: Boolean = false,
    subscriptionId: SubscriptionId = SubscriptionId(UUID.randomUUID().toString())
): Subscription {
    return newSubscription(subscriptionId, basePrice, startDate, 12, isStudent)
}

fun newSubscription(
    subscriptionId: SubscriptionId,
    price: Int,
    startDate: LocalDate,
    durationInMonths: Int,
    isStudent: Boolean
): Subscription {
    return Subscription(
        subscriptionId,
        startDate,
        durationInMonths,
        price,
        "bob@gmail.com",
        isStudent
    )
}
