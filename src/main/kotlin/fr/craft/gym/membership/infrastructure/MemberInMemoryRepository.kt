package fr.craft.gym.membership.infrastructure

import fr.craft.gym.membership.domain.EmailAddress
import fr.craft.gym.membership.domain.Member
import fr.craft.gym.membership.domain.MemberId
import fr.craft.gym.membership.domain.MemberRepository
import fr.craft.gym.membership.domain.MemberRepositoryException
import java.time.LocalDate
import java.util.HashMap
import java.util.UUID

class MemberInMemoryRepository : MemberRepository {

    private val members = HashMap<MemberId, Member>()

    override fun nextId(): MemberId {
        return MemberId(UUID.randomUUID().toString())
    }

    override fun store(member: Member) {
        members[member.id] = member
    }

    override fun get(memberId: MemberId): Member {
        return members[memberId]
            ?: throw MemberRepositoryException.notFound(memberId)
    }

    override fun findByEmail(email: EmailAddress): Member? {
        return members.filterValues {
            it.email == email
        }.values.firstOrNull()
    }

    override fun threeYearsAnniversaryMembers(asOfDate: LocalDate): Map<MemberId, Member> {
        return members.filterValues {
            it.isThreeYearsAnniversary(asOfDate)
        }
    }
}
