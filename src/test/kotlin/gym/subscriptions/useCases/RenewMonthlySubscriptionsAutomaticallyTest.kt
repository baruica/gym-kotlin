package gym.subscriptions.useCases

import gym.monthlySubscription
import gym.yearlySubscription
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class RenewMonthlySubscriptionsAutomaticallyTest {

    @Test
    fun handle() {
        val repository = InMemorySubscriptionRepository()

        val monthlySubscriptionId = repository.nextId()
        repository.store(
            monthlySubscription(
                200,
                LocalDate.parse("2018-06-09"),
                subscriptionId = monthlySubscriptionId
            )
        )

        val yearlySubscriptionId = repository.nextId()
        repository.store(
            yearlySubscription(
                1300,
                LocalDate.parse("2018-06-12"),
                subscriptionId = yearlySubscriptionId
            )
        )

        val tested = RenewMonthlySubscriptionsAutomatically(repository)

        val renewedSubscriptions = tested.handle(
            RenewMonthlySubscriptionsAutomaticallyCommand("2018-07-09")
        )

        assertEquals(1, renewedSubscriptions.size)
        assertEquals("2018-08-08", renewedSubscriptions.last().endDate.toString())
    }
}
