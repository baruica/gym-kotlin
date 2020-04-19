package fr.craft.gym.reporting.use_cases

import fr.craft.gym.subscriptions.domain.SubscriptionRepository

class TurnoverForAGivenMonth(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: TurnoverForAGivenMonthQuery): Double {

        var turnoverForGivenMonth = 0.0

        subscriptionRepository.onGoingSubscriptions(command.asOfDate).forEach {
            turnoverForGivenMonth = it.value.monthlyTurnover()
        }

        return turnoverForGivenMonth
    }
}
