package gym.subscriptions.use_cases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionEvent
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class SubscribeToPlan(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: SubscribeToPlanCommand): List<SubscriptionEvent> {

        val subscription = Subscription(
            subscriptionRepository.nextId(),
            LocalDate.parse(command.startDate),
            command.planDurationInMonths,
            command.planPrice,
            command.email,
            command.isStudent
        )

        subscriptionRepository.store(subscription)

        return subscription.raisedEvents
    }
}
