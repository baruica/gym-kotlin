package gym.plans.useCases

data class CreateNewPlanCommand(
    val planId: String,
    val planPrice: Int,
    val planDurationInMonths: Int,
)
