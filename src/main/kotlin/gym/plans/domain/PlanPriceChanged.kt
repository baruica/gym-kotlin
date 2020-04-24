package gym.plans.domain

import gym.AggregateId
import gym.Event

data class PlanPriceChanged(val planId: PlanId) : Event(AggregateId(planId.toString()))
