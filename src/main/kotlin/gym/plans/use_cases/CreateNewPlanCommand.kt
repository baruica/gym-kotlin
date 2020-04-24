package gym.plans.use_cases

data class CreateNewPlanCommand(
    val planId: String,
    val planPrice: Int,
    val planDurationInMonths: Int
)
