package gym.subscriptions.use_cases

import gym.subscriptions.domain.PlanSubscribed
import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository

class SubscribeToPlan(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: SubscribeToPlanCommand): PlanSubscribed {
        val subscription = Subscription(
            subscriptionRepository.nextId(),
            command.startDate,
            command.planId,
            command.planPrice,
            command.planDurationInMonths,
            command.isStudent
        )

        subscriptionRepository.store(subscription)

        return PlanSubscribed(
            subscription.id,
            subscription.startDate,
            command.email
        )
    }
}
