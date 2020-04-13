package fr.craft.gym.reporting.use_cases

import fr.craft.gym.plans.domain.PlanId
import fr.craft.gym.subscribing.domain.ChosenPlan
import fr.craft.gym.subscribing.domain.Subscription
import fr.craft.gym.subscribing.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class TurnoverForAGivenMonthHandlerTest {

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

        val tested = TurnoverForAGivenMonthHandler(subscriptionRepository)

        assertEquals(50.toDouble(), tested.handle(TurnoverForAGivenMonth(today)))
        assertEquals(1, subscriptionRepository.onGoingSubscriptions(today).size)

        assertEquals(29.toDouble(), tested.handle(TurnoverForAGivenMonth(inAMonth)))
        assertEquals(1, subscriptionRepository.onGoingSubscriptions(inAMonth).size)
    }
}
