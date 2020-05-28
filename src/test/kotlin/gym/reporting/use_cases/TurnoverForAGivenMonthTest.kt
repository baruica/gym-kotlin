package gym.reporting.use_cases

import gym.monthlySubscription
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import gym.yearlySubscription
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class TurnoverForAGivenMonthTest {

    @Test
    fun `turnover for a given month with ongoing subscriptions`() {
        val subscriptionRepository = SubscriptionInMemoryRepository()

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

        assertEquals(73.toDouble(), tested.handle(TurnoverForAGivenMonthQuery(today)))
        assertEquals(2, subscriptionRepository.onGoingSubscriptions(today).size)

        assertEquals(52.toDouble(), tested.handle(TurnoverForAGivenMonthQuery(inAMonth)))
        assertEquals(2, subscriptionRepository.onGoingSubscriptions(inAMonth).size)
    }
}
