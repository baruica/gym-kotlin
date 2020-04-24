package gym.plans.use_cases

data class ChangePriceOfPlanCommand(val planId: String, val newPrice: Int)
