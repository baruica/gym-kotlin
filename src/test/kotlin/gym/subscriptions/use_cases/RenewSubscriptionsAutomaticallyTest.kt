package gym.subscriptions.use_cases

import gym.monthlySubscription
import gym.subscriptions.domain.SubscriptionEvent.SubscriptionRenewed
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class RenewSubscriptionsAutomaticallyTest {

    @Test
    fun handle() {
        val subscriptionRepository = SubscriptionInMemoryRepository()
        val subscriptionId = subscriptionRepository.nextId()
        val subscription = monthlySubscription(
            200,
            LocalDate.parse("2018-06-09"),
            subscriptionId = subscriptionId
        )
        subscriptionRepository.store(subscription)

        val tested = RenewSubscriptionsAutomatically(subscriptionRepository)

        val events = tested.handle(
            RenewSubscriptionsAutomaticallyCommand("2018-07-09")
        )

        assertEquals(
            events.last(),
            SubscriptionRenewed(
                subscriptionId.toString(),
                "2018-07-08",
                "2018-08-08"
            )
        )
    }
}
