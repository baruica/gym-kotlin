package gym.subscriptions.use_cases

import gym.monthlySubscription
import gym.subscriptions.domain.SubscriptionId
import gym.subscriptions.domain.SubscriptionRenewed
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class RenewSubscriptionsAutomaticallyTest {

    @Test
    fun handle() {
        val repository = SubscriptionInMemoryRepository()
        val subscriptionId = repository.nextId()
        val subscription = monthlySubscription(
            200,
            LocalDate.parse("2018-06-09"),
            subscriptionId = SubscriptionId(subscriptionId)
        )
        repository.store(subscription)

        val tested = RenewSubscriptionsAutomatically(repository)

        val events = tested.handle(
            RenewSubscriptionsAutomaticallyCommand("2018-07-09")
        )

        assertEquals(
            events.last(),
            SubscriptionRenewed(
                subscriptionId,
                "2018-07-08",
                "2018-08-08"
            )
        )
    }
}
