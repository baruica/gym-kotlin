package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class ApplyThreeYearsAnniversaryDiscount(val asOfDate: String)

class ApplyThreeYearsAnniversaryDiscountHandler(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(command: ApplyThreeYearsAnniversaryDiscount): List<Subscription> {

        val date = LocalDate.parse(command.asOfDate)

        val threeYearsAnniversarySubscriptions = subscriptionRepository.threeYearsAnniversarySubscriptions(date)

        threeYearsAnniversarySubscriptions.forEach {
            it.applyThreeYearsAnniversaryDiscount(date)
        }

        subscriptionRepository.storeAll(threeYearsAnniversarySubscriptions)

        return threeYearsAnniversarySubscriptions
    }
}
