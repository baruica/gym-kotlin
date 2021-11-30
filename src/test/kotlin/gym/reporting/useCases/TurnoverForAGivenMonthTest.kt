package gym.reporting.useCases

import gym.monthlySubscription
import gym.reporting.Turnover
import gym.subscriptions.useCases.InMemorySubscriptionRepository
import gym.yearlySubscription
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class TurnoverForAGivenMonthTest : AnnotationSpec() {

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

        val tested = TurnoverForAGivenMonthHandler(subscriptionRepository)

        subscriptionRepository.onGoingSubscriptions(today).shouldHaveSize(2)
        tested(TurnoverForAGivenMonth(today)) shouldBe Turnover(80)

        subscriptionRepository.onGoingSubscriptions(inAMonth).shouldHaveSize(3)
        tested(TurnoverForAGivenMonth(inAMonth)) shouldBe Turnover(118)
    }
}
