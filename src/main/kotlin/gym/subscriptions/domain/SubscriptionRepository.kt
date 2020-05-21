package gym.subscriptions.domain

import common.Repository
import java.time.LocalDate

interface SubscriptionRepository : Repository {

    fun endedSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription>

    fun onGoingSubscriptions(asOfDate: LocalDate): Map<SubscriptionId, Subscription>
}
