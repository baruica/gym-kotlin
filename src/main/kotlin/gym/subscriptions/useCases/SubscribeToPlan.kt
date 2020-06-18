package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class SubscribeToPlan(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: SubscribeToPlanCommand): Subscription {

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
