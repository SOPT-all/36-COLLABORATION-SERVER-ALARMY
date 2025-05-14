package sopt.collaboration.alarmy.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.collaboration.alarmy.alarm.domain.Alarm;
import sopt.collaboration.alarmy.member.domain.Member;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByMember(Member member);
}
