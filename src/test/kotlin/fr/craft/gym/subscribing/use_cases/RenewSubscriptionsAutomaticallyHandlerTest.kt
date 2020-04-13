package fr.craft.gym.subscribing.use_cases

import fr.craft.gym.plans.domain.PlanId
import fr.craft.gym.subscribing.domain.ChosenPlan
import fr.craft.gym.subscribing.domain.Subscription
import fr.craft.gym.subscribing.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertTrue

class RenewSubscriptionsAutomaticallyHandlerTest {

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

        val tested = RenewSubscriptionsAutomaticallyHandler(subscriptionRepository)

        val event = tested.handle(
            RenewSubscriptionsAutomatically(LocalDate.parse("2018-07-09"))
        )

        assertTrue(event.renewedSubscriptions.contains(subscriptionId))

        val onGoingDateAfterRenewing = LocalDate.parse("2018-08-01")
        assertTrue(subscriptionRepository.get(subscription.id).isOngoing(onGoingDateAfterRenewing))

        val dateWillBeEndedAfterRenewal = LocalDate.parse("2018-08-10")
        assertTrue(subscriptionRepository.get(subscription.id).willBeEnded(dateWillBeEndedAfterRenewal))
    }
}
