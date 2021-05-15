package gym.subscriptions.useCases

import gym.monthlySubscription
import gym.yearlySubscription
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class RenewMonthlySubscriptionsAutomaticallyTest : AnnotationSpec() {

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
            RenewMonthlySubscriptionsAutomaticallyCommand("2018-07-10")
        )

        renewedSubscriptions.shouldHaveSize(1)
        renewedSubscriptions.last().endDate shouldBe LocalDate.parse("2018-08-09")
    }
}
