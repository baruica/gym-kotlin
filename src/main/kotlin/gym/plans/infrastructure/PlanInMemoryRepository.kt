package gym.plans.infrastructure

import common.InMemoryRepository
import gym.plans.domain.PlanRepository

class PlanInMemoryRepository : InMemoryRepository(), PlanRepository
