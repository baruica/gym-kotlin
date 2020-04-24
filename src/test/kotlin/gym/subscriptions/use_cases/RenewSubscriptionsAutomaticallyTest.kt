package gym.subscriptions.use_cases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionEvent
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
            "planId abcdef",
            200,
            1,
            false,
            "bob@gmail.com"
        )
        subscriptionRepository.store(subscription)

        val tested = RenewSubscriptionsAutomatically(subscriptionRepository)

        val events = tested.handle(
            RenewSubscriptionsAutomaticallyCommand("2018-07-09")
        )

        assertTrue(events.last() is SubscriptionEvent.SubscriptionRenewed)

        val onGoingDateAfterRenewing = LocalDate.parse("2018-08-01")
        assertTrue(subscriptionRepository.get(subscription.subscriptionId).isOngoing(onGoingDateAfterRenewing))

        val dateWillBeEndedAfterRenewal = LocalDate.parse("2018-08-10")
        assertTrue(subscriptionRepository.get(subscription.subscriptionId).willBeEnded(dateWillBeEndedAfterRenewal))
    }
}
