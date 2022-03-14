package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class SubscribeToPlan(
    val subscriptionId: String,
    val planPrice: Int,
    val planDurationInMonths: Int,
    val startDate: LocalDate,
    val isStudent: Boolean,
) {
    class Handler(
        private val subscriptionRepository: SubscriptionRepository
    ) {
        operator fun invoke(command: SubscribeToPlan): Subscription {

            val subscription = Subscription.subscribe(
                command.subscriptionId,
                command.planDurationInMonths,
                command.startDate,
                command.planPrice,
                command.isStudent
            )

            subscriptionRepository.store(subscription)

            return subscription
        }
    }
}
