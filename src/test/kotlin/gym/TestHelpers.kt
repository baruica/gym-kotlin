package gym

import com.github.guepardoapps.kulid.ULID
import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

fun monthlySubscription(
    basePrice: Int,
    startDate: LocalDate,
    isStudent: Boolean = false,
    subscriptionId: SubscriptionId = SubscriptionId(ULID.random())
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
    subscriptionId: SubscriptionId = SubscriptionId(ULID.random())
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
    subscriptionId: SubscriptionId,
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
