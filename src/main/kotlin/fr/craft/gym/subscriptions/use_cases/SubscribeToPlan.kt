package fr.craft.gym.subscriptions.use_cases

import fr.craft.gym.subscriptions.domain.PlanSubscribed
import fr.craft.gym.subscriptions.domain.Subscription
import fr.craft.gym.subscriptions.domain.SubscriptionRepository

class SubscribeToPlan(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: SubscribeToPlanCommand): PlanSubscribed {
        val subscription = Subscription(
            command.subscriptionId,
            command.chosenPlan,
            command.startDate,
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
