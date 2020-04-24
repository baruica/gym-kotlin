package gym

sealed class Event(val aggregateId: String) {

    data class NewPlanCreated(val planId: String) : Event(planId)
    data class PlanPriceChanged(val planId: String) : Event(planId)

    data class NewSubscription(
        val subscriptionId: String,
        val subscriptionStartDate: String,
        val email: String
    ) : Event(subscriptionId)

    data class SubscriptionRenewed(val subscriptionId: String) : Event(subscriptionId)

    data class NewMemberSubscribed(val memberId: String, val memberEmail: String) : Event(memberId)
    data class WelcomeEmailWasSentToNewMember(val memberId: String) : Event(memberId)
    data class ThreeYearsAnniversaryThankYouEmailSent(val memberId: String) : Event(memberId)
}
