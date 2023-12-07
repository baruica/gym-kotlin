package gym.subscriptions.domain

import gym.monthlySubscription
import gym.yearlySubscription
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class SubscriptionTest : StringSpec({

    "base price for a monthly subscription" {
        val subscriptionWithoutDiscount = monthlySubscription(300, LocalDate.parse("2018-06-05"), isStudent = false)

        subscriptionWithoutDiscount.price shouldBe Price(300)
    }

    "10 percent discount for yearly subscription" {
        val subscriptionWithYearlyDiscount = yearlySubscription(1000, LocalDate.parse("2018-06-05"), isStudent = false)

        subscriptionWithYearlyDiscount.price shouldBe Price(900)
    }

    "20 percent discount for students" {
        val monthlySubscriptionWithStudentDiscount =
            monthlySubscription(100, LocalDate.parse("2018-06-05"), isStudent = true)
        monthlySubscriptionWithStudentDiscount.price shouldBe Price(80)

        val yearlySubscriptionWithStudentDiscount =
            yearlySubscription(100, LocalDate.parse("2018-06-05"), isStudent = true)
        yearlySubscriptionWithStudentDiscount.price shouldBe Price(72)
    }

    "5 percent discount after 3 years" {
        val subscription = yearlySubscription(1000, LocalDate.parse("2018-06-05"))
        subscription.price shouldBe Price(900)

        subscription.renew()
        subscription.price shouldBe Price(900)

        subscription.renew()
        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))
        subscription.price shouldBe Price(855)
    }

    "3 years anniversary discount can only be applied after 3 years of subscription" {
        val subscription = yearlySubscription(1000, LocalDate.parse("2018-06-05"))

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))
        subscription.price shouldBe Price(900)

        subscription.renew()
        subscription.renew()

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-04"))
        subscription.price shouldBe Price(900)

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))
        subscription.price shouldBe Price(855)
    }

    "3 years anniversary discount can be applied any time after 3 years of subscription" {
        val subscription = yearlySubscription(1000, LocalDate.parse("2018-06-05"))

        subscription.renew()
        subscription.renew()

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-04"))
        subscription.price shouldBe Price(900)

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-10"))
        subscription.price shouldBe Price(855)
    }

    "3 years anniversary discount can only be applied once" {
        val subscription = yearlySubscription(1000, LocalDate.parse("2018-06-05"))

        subscription.renew()
        subscription.renew()

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))
        subscription.price shouldBe Price(855)

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-10"))
        subscription.price shouldBe Price(855)
    }

    "can be renewed" {
        val subscription = monthlySubscription(100, LocalDate.parse("2018-06-05"))

        subscription.willBeEndedAsFrom(LocalDate.parse("2018-07-05")).shouldBeFalse()
        subscription.willBeEndedAsFrom(LocalDate.parse("2018-07-06")).shouldBeTrue()

        subscription.renew()

        subscription.willBeEndedAsFrom(LocalDate.parse("2018-08-05")).shouldBeFalse()
        subscription.willBeEndedAsFrom(LocalDate.parse("2018-08-06")).shouldBeTrue()
    }

    "can be ongoing" {
        val ongoingSubscription = monthlySubscription(100, LocalDate.parse("2018-06-05"))

        ongoingSubscription.isOngoing(LocalDate.parse("2018-06-04")).shouldBeFalse()
        ongoingSubscription.isOngoing(LocalDate.parse("2018-06-05")).shouldBeTrue()
        ongoingSubscription.isOngoing(LocalDate.parse("2018-06-19")).shouldBeTrue()
        ongoingSubscription.isOngoing(LocalDate.parse("2018-07-05")).shouldBeTrue()
        ongoingSubscription.isOngoing(LocalDate.parse("2018-07-06")).shouldBeFalse()
    }

    "can tell if it'll be ended as from a given date" {
        val subscriptionEndingEndOfJune = monthlySubscription(100, LocalDate.parse("2018-06-05"))

        subscriptionEndingEndOfJune.willBeEndedAsFrom(LocalDate.parse("2018-07-05")).shouldBeFalse()
        subscriptionEndingEndOfJune.willBeEndedAsFrom(LocalDate.parse("2018-07-06")).shouldBeTrue()
    }

    "has a monthly turnover" {
        val monthlySubscription = monthlySubscription(100, LocalDate.parse("2018-06-05"))
        monthlySubscription.monthlyTurnover() shouldBe 100

        val yearlySubscription = yearlySubscription(1200, LocalDate.parse("2018-06-05"))
        yearlySubscription.monthlyTurnover() shouldBe 90
    }
})
