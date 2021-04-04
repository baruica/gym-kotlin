package gym.reporting.useCases

import gym.monthlySubscription
import gym.reporting.Turnover
import gym.subscriptions.useCases.InMemorySubscriptionRepository
import gym.yearlySubscription
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class TurnoverForAGivenMonthTest {

    @Test
    fun `turnover for a given month with ongoing subscriptions`() {
        val subscriptionRepository = InMemorySubscriptionRepository()

        val today = LocalDate.parse("2018-06-09")
        val inAMonth = LocalDate.parse("2018-07-09")

        subscriptionRepository.store(
            monthlySubscription(50, today)
        )
        subscriptionRepository.store(
            yearlySubscription(400, today)
        )
        subscriptionRepository.store(
            yearlySubscription(500, inAMonth)
        )

        val tested = TurnoverForAGivenMonth(subscriptionRepository)

        assertEquals(2, subscriptionRepository.onGoingSubscriptions(today).size)
        assertEquals(Turnover(80.0), tested.handle(TurnoverForAGivenMonthQuery(today)))

        assertEquals(2, subscriptionRepository.onGoingSubscriptions(inAMonth).size)
        assertEquals(Turnover(67.5), tested.handle(TurnoverForAGivenMonthQuery(inAMonth)))
    }
}
