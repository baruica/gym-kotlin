package gym.plans.use_cases

data class CreateNewPlanCommand(
    val planPrice: Int,
    val planDurationInMonths: Int
)
