package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class ApplyThreeYearsAnniversaryDiscount(val asOfDate: LocalDate) {
    class Handler(
        private val subscriptionRepository: SubscriptionRepository
    ) {
        operator fun invoke(command: ApplyThreeYearsAnniversaryDiscount): List<Subscription> {

            val threeYearsAnniversarySubscriptions =
                subscriptionRepository.threeYearsAnniversarySubscriptions(command.asOfDate)

            threeYearsAnniversarySubscriptions.forEach {
                it.applyThreeYearsAnniversaryDiscount(command.asOfDate)
            }

            subscriptionRepository.storeAll(threeYearsAnniversarySubscriptions)

            return threeYearsAnniversarySubscriptions
        }
    }
}
