package gym.membership.domain

import java.time.LocalDate

interface MemberRepository {

    fun nextId(): MemberId

    fun store(member: Member)

    fun get(memberId: MemberId): Member

    fun findByEmail(email: EmailAddress): Member?

    fun threeYearsAnniversaryMembers(asOfDate: LocalDate): Map<MemberId, Member>
}
