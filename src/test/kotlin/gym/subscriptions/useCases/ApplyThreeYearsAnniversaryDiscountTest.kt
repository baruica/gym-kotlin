package gym.subscriptions.useCases

import gym.yearlySubscription
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

internal class ApplyThreeYearsAnniversaryDiscountTest : AnnotationSpec() {

    @Test
    fun handle() {
        val repository = InMemorySubscriptionRepository()

        val subscription = yearlySubscription(
            1300,
            LocalDate.parse("2015-07-09"),
            subscriptionId = repository.nextId()
        )
        subscription.renew()
        subscription.renew()

        repository.store(subscription)

        val tested = ApplyThreeYearsAnniversaryDiscount(repository)

        val subscriptionsBeforeThreeYearsAnniversary = tested.handle(
            ApplyThreeYearsAnniversaryDiscountCommand("2018-07-08")
        )
        subscriptionsBeforeThreeYearsAnniversary.size shouldBe 0

        val subscriptionsWithThreeYearsDiscount = tested.handle(
            ApplyThreeYearsAnniversaryDiscountCommand("2018-07-09")
        )
        subscriptionsWithThreeYearsDiscount.last().price.amount shouldBe 1111.5

        val subscriptionsAfterThreeYearsAnniversary = tested.handle(
            ApplyThreeYearsAnniversaryDiscountCommand("2018-07-10")
        )
        subscriptionsAfterThreeYearsAnniversary.size shouldBe 0
    }
}
