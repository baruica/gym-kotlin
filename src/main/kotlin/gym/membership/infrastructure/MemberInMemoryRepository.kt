package gym.membership.infrastructure

import common.Aggregate
import common.AggregateId
import common.RepositoryException
import gym.membership.domain.Email
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.domain.MemberRepository
import java.time.LocalDate
import java.util.*

class MemberInMemoryRepository : MemberRepository {

    private val members = mutableMapOf<MemberId, Member>()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun store(aggregate: Aggregate) {
        members[aggregate.id as MemberId] = aggregate as Member
    }

    override fun storeAll(aggregates: Map<out AggregateId, Aggregate>) {
        aggregates.forEach {
            store(it.value)
        }
    }

    override fun get(aggregateId: AggregateId): Aggregate {
        return members[aggregateId]
            ?: throw RepositoryException.notFound(aggregateId)
    }

    override fun findByEmail(email: Email): Member? {
        return members.filter {
            it.value.email == email
        }.values.firstOrNull()
    }

    override fun threeYearsAnniversaryMembers(asOfDate: LocalDate): Map<MemberId, Member> {
        return members.filter {
            it.value.isThreeYearsAnniversary(asOfDate)
        }
    }
}
