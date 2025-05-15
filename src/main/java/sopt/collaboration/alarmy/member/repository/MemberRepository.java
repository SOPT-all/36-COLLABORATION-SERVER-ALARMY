package sopt.collaboration.alarmy.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.collaboration.alarmy.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
