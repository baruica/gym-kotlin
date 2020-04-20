package gym.reporting.use_cases

import gym.plans.domain.PlanId
import gym.subscriptions.domain.ChosenPlan
import gym.subscriptions.domain.Subscription
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class TurnoverForAGivenMonthTest {

    @Test
    fun `turnover for a given month with ongoing subscriptions`() {
        val subscriptionRepository = SubscriptionInMemoryRepository()

        val today = LocalDate.parse("2018-06-09")
        val inAMonth = LocalDate.parse("2018-07-09")

        subscriptionRepository.store(
            Subscription(
                subscriptionRepository.nextId(),
                ChosenPlan(PlanId("abcdef"), 50, 1, ""),
                today,
                false
            )
        )
        subscriptionRepository.store(
            Subscription(
                subscriptionRepository.nextId(),
                ChosenPlan(PlanId("defghi"), 500, 12, ""),
                inAMonth,
                false
            )
        )

        val tested = TurnoverForAGivenMonth(subscriptionRepository)

        assertEquals(50.toDouble(), tested.handle(TurnoverForAGivenMonthQuery(today)))
        assertEquals(1, subscriptionRepository.onGoingSubscriptions(today).size)

        assertEquals(29.toDouble(), tested.handle(TurnoverForAGivenMonthQuery(inAMonth)))
        assertEquals(1, subscriptionRepository.onGoingSubscriptions(inAMonth).size)
    }
}
