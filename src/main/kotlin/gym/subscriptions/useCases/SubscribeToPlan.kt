package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class SubscribeToPlanCommand(
    val subscriptionId: String,
    val planPrice: Int,
    val planDurationInMonths: Int,
    val startDate: String,
    val isStudent: Boolean,
)

class SubscribeToPlan(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(command: SubscribeToPlanCommand): Subscription {

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
