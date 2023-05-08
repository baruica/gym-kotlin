package gym.subscriptions.domain

import Repository
import java.time.LocalDate

interface SubscriptionRepository: Repository<String, Subscription> {

    fun endedMonthlySubscriptions(date: LocalDate): List<Subscription>

    fun onGoingSubscriptions(date: LocalDate): List<Subscription>

    fun threeYearsAnniversarySubscriptions(date: LocalDate): List<Subscription>
}
