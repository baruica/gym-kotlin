package gym.plans.useCases

import InMemoryRepository
import gym.plans.domain.Plan
import gym.plans.domain.PlanRepository

class InMemoryPlanRepository : InMemoryRepository<Plan>(), PlanRepository
