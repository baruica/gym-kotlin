package common

import gym.membership.domain.MemberId
import gym.plans.domain.PlanId
import gym.subscriptions.domain.SubscriptionId
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RepositoryExceptionTest {

    @Test
    fun `has a message that tells which type of aggregate is not found`() {
        assertEquals(
            "Member [member 42] not found.",
            RepositoryException.notFound(MemberId("member 42")).message
        )
        assertEquals(
            "Plan [plan 42] not found.",
            RepositoryException.notFound(PlanId("plan 42")).message
        )
        assertEquals(
            "Subscription [subscription 42] not found.",
            RepositoryException.notFound(SubscriptionId("subscription 42")).message
        )
    }
}
