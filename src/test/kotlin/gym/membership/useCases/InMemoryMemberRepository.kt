package gym.membership.useCases

import common.Aggregate
import common.AggregateId
import common.RepositoryException
import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.domain.MemberRepository
import java.time.LocalDate
import java.util.*

class InMemoryMemberRepository : MemberRepository {

    private val members = mutableMapOf<MemberId, Member>()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun store(aggregate: Aggregate) {
        members[aggregate.id as MemberId] = aggregate as Member
    }

    override fun storeAll(aggregates: List<Aggregate>) {
        aggregates.forEach { store(it) }
    }

    override fun get(aggregateId: AggregateId): Aggregate {
        return members[aggregateId]
            ?: throw RepositoryException.notFound(aggregateId)
    }

    override fun findByEmailAddress(emailAddress: EmailAddress): Member? {
        return members.filter {
            it.value.emailAddress == emailAddress
        }.values.firstOrNull()
    }

    override fun threeYearsAnniversaryMembers(asOfDate: LocalDate): List<Member> {
        return members.filter {
            it.value.isThreeYearsAnniversary(asOfDate)
        }.values.map { it }
    }
}
