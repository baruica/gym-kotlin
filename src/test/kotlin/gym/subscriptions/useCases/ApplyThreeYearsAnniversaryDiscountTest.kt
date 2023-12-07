package gym.subscriptions.useCases

import Id
import gym.subscriptions.domain.Price
import gym.yearlySubscription
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import java.time.LocalDate

internal class ApplyThreeYearsAnniversaryDiscountTest : StringSpec({

    "handle" {
        val repository = InMemorySubscriptionRepository()

        val subscription = yearlySubscription(
            1300,
            LocalDate.parse("2015-07-09"),
            subscriptionId = Id(repository.nextId())
        )
        subscription.renew()
        subscription.renew()

        repository.store(subscription)

        val tested = ApplyThreeYearsAnniversaryDiscount.Handler(repository)

        val subscriptionsBeforeThreeYearsAnniversary = tested(
            ApplyThreeYearsAnniversaryDiscount(LocalDate.parse("2018-07-08"))
        )
        subscriptionsBeforeThreeYearsAnniversary.shouldBeEmpty()

        val subscriptionsWithThreeYearsDiscount = tested(
            ApplyThreeYearsAnniversaryDiscount(LocalDate.parse("2018-07-12"))
        )
        subscriptionsWithThreeYearsDiscount.last().price shouldBe Price(1111.5)

        val subscriptionsAfterThreeYearsAnniversary = tested(
            ApplyThreeYearsAnniversaryDiscount(LocalDate.parse("2018-07-09"))
        )
        subscriptionsAfterThreeYearsAnniversary.shouldBeEmpty()
    }
})
