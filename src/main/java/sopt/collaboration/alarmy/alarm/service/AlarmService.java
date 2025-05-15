package sopt.collaboration.alarmy.alarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.collaboration.alarmy.alarm.domain.Alarm;
import sopt.collaboration.alarmy.alarm.dto.request.AlarmRequest;
import sopt.collaboration.alarmy.alarm.dto.response.AlarmResponse;
import sopt.collaboration.alarmy.alarm.repository.AlarmRepository;
import sopt.collaboration.alarmy.global.error.ErrorCode;
import sopt.collaboration.alarmy.global.error.exception.BusinessException;
import sopt.collaboration.alarmy.global.error.exception.NoSuchMemberException;
import sopt.collaboration.alarmy.member.domain.Member;
import sopt.collaboration.alarmy.member.repository.MemberRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<AlarmResponse> createAlarm(long userId, AlarmRequest request) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(NoSuchMemberException::new);

        LocalTime time = LocalTime.parse(request.timestamp());

        Alarm alarm = Alarm.builder()
                .timestamp(time)
                .isActive(true) // true 로!
                .member(member)
                .build();

        Alarm savedAlarm = alarmRepository.save(alarm);

        return List.of(new AlarmResponse(savedAlarm.getId(), formatTime(savedAlarm.getTimestamp()), savedAlarm.isActive()));
    }

    @Transactional(readOnly = true)
    public List<AlarmResponse> getAllAlarms(long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(NoSuchMemberException::new);

        return alarmRepository.findByMember(member).stream()
                .map(alarm -> new AlarmResponse(alarm.getId(), formatTime(alarm.getTimestamp()), alarm.isActive()))
                .collect(Collectors.toList());
    }

    private String formatTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }
}
