package gym.subscriptions.useCases

import Id
import gym.monthlySubscription
import gym.yearlySubscription
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class RenewMonthlySubscriptionsAutomaticallyTest : StringSpec({

    "handle" {
        val repository = InMemorySubscriptionRepository()

        val monthlySubscriptionId = repository.nextId()
        repository.store(
            monthlySubscription(
                200,
                LocalDate.parse("2018-06-09"),
                subscriptionId = Id(monthlySubscriptionId)
            )
        )

        val yearlySubscriptionId = repository.nextId()
        repository.store(
            yearlySubscription(
                1300,
                LocalDate.parse("2018-06-12"),
                subscriptionId = Id(yearlySubscriptionId)
            )
        )

        val tested = RenewMonthlySubscriptionsAutomatically.Handler(repository)

        val renewedSubscriptions = tested(
            RenewMonthlySubscriptionsAutomatically(LocalDate.parse("2018-07-10"))
        )

        renewedSubscriptions.shouldHaveSize(1)
        renewedSubscriptions.last().endDate shouldBe LocalDate.parse("2018-08-09")
    }
})
