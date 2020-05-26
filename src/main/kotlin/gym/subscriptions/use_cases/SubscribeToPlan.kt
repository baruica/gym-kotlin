package gym.subscriptions.use_cases

import common.DomainEvent
import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionId
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

class SubscribeToPlan(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: SubscribeToPlanCommand): List<DomainEvent> {

        val subscription = Subscription.subscribe(
            SubscriptionId(subscriptionRepository.nextId()),
            command.planDurationInMonths,
            LocalDate.parse(command.startDate),
            command.planPrice,
            command.email,
            command.isStudent
        )

        subscriptionRepository.store(subscription)

        return subscription.raisedEvents
    }
}
