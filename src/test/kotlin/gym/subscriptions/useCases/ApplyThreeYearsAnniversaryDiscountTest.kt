package gym.subscriptions.useCases

import gym.yearlySubscription
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

internal class ApplyThreeYearsAnniversaryDiscountTest {

    @Test
    fun handle() {
        val repository = InMemorySubscriptionRepository()

        repository.store(
            yearlySubscription(
                1300,
                LocalDate.parse("2015-07-09"),
                subscriptionId = repository.nextId()
            )
        )

        val tested = ApplyThreeYearsAnniversaryDiscount(repository)

        val subscriptionsBeforeThreeYearsAnniversary = tested.handle(
            ApplyThreeYearsAnniversaryDiscountCommand("2018-07-08")
        )
        assertEquals(0, subscriptionsBeforeThreeYearsAnniversary.size)

        val subscriptionsWithThreeYearsDiscount = tested.handle(
            ApplyThreeYearsAnniversaryDiscountCommand("2018-07-09")
        )
        assertEquals(1111.5, subscriptionsWithThreeYearsDiscount.last().price.amount)

        val subscriptionsAfterThreeYearsAnniversary = tested.handle(
            ApplyThreeYearsAnniversaryDiscountCommand("2018-07-10")
        )
        assertEquals(0, subscriptionsAfterThreeYearsAnniversary.size)
    }
}
