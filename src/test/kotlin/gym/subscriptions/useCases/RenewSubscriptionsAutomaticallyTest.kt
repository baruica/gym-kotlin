package gym.subscriptions.useCases

import gym.monthlySubscription
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
            subscriptionId = subscriptionId
        )
        repository.store(subscription)

        val tested = RenewSubscriptionsAutomatically(repository)

        val renewedSubscriptions = tested.handle(
            RenewSubscriptionsAutomaticallyCommand("2018-07-09")
        )

        assertEquals("2018-08-08", renewedSubscriptions.last().endDate.toString())
    }
}
