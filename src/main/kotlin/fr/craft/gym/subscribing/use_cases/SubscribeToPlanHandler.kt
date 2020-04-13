package fr.craft.gym.subscribing.use_cases

import fr.craft.gym.subscribing.domain.PlanSubscribed
import fr.craft.gym.subscribing.domain.Subscription
import fr.craft.gym.subscribing.domain.SubscriptionRepository

class SubscribeToPlanHandler(private val subscriptionRepository: SubscriptionRepository) {

    fun handle(command: SubscribeToPlan): PlanSubscribed {
        val subscription = Subscription(
            command.subscriptionId,
            command.chosenPlan,
            command.startDate,
            command.isStudent
        )

        subscriptionRepository.store(subscription)

        return PlanSubscribed(subscription)
    }
}
