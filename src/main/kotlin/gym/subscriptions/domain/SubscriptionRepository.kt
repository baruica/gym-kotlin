package gym.subscriptions.domain

import common.Repository
import java.time.LocalDate

interface SubscriptionRepository : Repository {

    fun endedMonthlySubscriptions(date: LocalDate): List<Subscription>

    fun onGoingSubscriptions(date: LocalDate): List<Subscription>

    fun threeYearsAnniversarySubscriptions(date: LocalDate): List<Subscription>
}
