package gym.plans.infrastructure

import gym.plans.domain.PlanId

class PlanRepositoryException(override val message: String) : Throwable(message) {

    companion object {
        fun notFound(planId: PlanId): PlanRepositoryException {
            return PlanRepositoryException("Plan [$planId] not found.")
        }
    }
}
