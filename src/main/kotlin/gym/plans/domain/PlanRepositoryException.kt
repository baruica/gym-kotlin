package gym.plans.domain

class PlanRepositoryException(override val message: String) : Throwable(message) {

    companion object {
        fun notFound(planId: PlanId): PlanRepositoryException {
            return PlanRepositoryException("Plan [$planId] not found.")
        }
    }
}
