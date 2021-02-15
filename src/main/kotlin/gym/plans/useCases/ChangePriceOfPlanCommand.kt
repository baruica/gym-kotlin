package gym.plans.useCases

data class ChangePriceOfPlanCommand(
    val planId: String,
    val newPrice: Int,
)
