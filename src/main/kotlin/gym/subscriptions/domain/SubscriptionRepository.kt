package gym.subscriptions.domain

import common.Repository
import java.time.LocalDate

interface SubscriptionRepository : Repository {

    fun endedSubscriptions(asOfDate: LocalDate): List<Subscription>

    fun onGoingSubscriptions(asOfDate: LocalDate): List<Subscription>
}
