package gym.subscriptions.use_cases

import gym.plans.domain.PlanId
import gym.subscriptions.domain.Subscription
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
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
            LocalDate.parse("2018-06-09"),
            PlanId("abcdef"),
            200,
            1,
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
