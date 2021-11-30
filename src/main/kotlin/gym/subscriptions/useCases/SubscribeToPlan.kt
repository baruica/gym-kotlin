package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class SubscribeToPlan(
    val subscriptionId: String,
    val planPrice: Int,
    val planDurationInMonths: Int,
    val startDate: String,
    val isStudent: Boolean,
)

class SubscribeToPlanHandler(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(command: SubscribeToPlan): Subscription {

        val subscription = Subscription.subscribe(
            command.subscriptionId,
            command.planDurationInMonths,
            LocalDate.parse(command.startDate),
            command.planPrice,
            command.isStudent
        )

        subscriptionRepository.store(subscription)

        return subscription
    }
}
