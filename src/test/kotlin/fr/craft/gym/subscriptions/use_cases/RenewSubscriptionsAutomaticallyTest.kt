package fr.craft.gym.subscriptions.use_cases

import fr.craft.gym.plans.domain.PlanId
import fr.craft.gym.subscriptions.domain.ChosenPlan
import fr.craft.gym.subscriptions.domain.Subscription
import fr.craft.gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertTrue

class RenewSubscriptionsAutomaticallyTest {

    @Test
    fun handle() {
        val subscriptionRepository = SubscriptionInMemoryRepository()
        val subscriptionId = subscriptionRepository.nextId()
        val subscription = Subscription(
            subscriptionId,
            ChosenPlan(PlanId("abcdef"), 200, 1, "description"),
            LocalDate.parse("2018-06-09"),
            false
        )
        subscriptionRepository.store(subscription)

        val tested = RenewSubscriptionsAutomatically(subscriptionRepository)

        val event = tested.handle(
            RenewSubscriptionsAutomaticallyCommand(LocalDate.parse("2018-07-09"))
        )

        assertTrue(event.renewedSubscriptions.contains(subscriptionId))

        val onGoingDateAfterRenewing = LocalDate.parse("2018-08-01")
        assertTrue(subscriptionRepository.get(subscription.id).isOngoing(onGoingDateAfterRenewing))

        val dateWillBeEndedAfterRenewal = LocalDate.parse("2018-08-10")
        assertTrue(subscriptionRepository.get(subscription.id).willBeEnded(dateWillBeEndedAfterRenewal))
    }
}
