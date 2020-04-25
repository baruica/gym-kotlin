package gym.subscriptions.use_cases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionEvent.SubscriptionRenewed
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class RenewSubscriptionsAutomaticallyTest {

    @Test
    fun handle() {
        val subscriptionRepository = SubscriptionInMemoryRepository()
        val subscription = Subscription(
            subscriptionRepository.nextId(),
            LocalDate.parse("2018-06-09"),
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

        assertEquals(
            events.last(),
            SubscriptionRenewed(
                subscription.id.toString(),
                "2018-07-08",
                "2018-08-08"
            )
        )
    }
}
