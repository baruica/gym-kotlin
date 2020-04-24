package gym.plans.domain

import gym.AggregateId
import gym.Event

data class NewPlanCreated(val newPlanId: PlanId) : Event(AggregateId(newPlanId.toString()))
