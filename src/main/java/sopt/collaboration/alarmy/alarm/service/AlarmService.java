package sopt.collaboration.alarmy.alarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.collaboration.alarmy.alarm.domain.Alarm;
import sopt.collaboration.alarmy.alarm.dto.request.AlarmCheckRequest;
import sopt.collaboration.alarmy.alarm.dto.request.AlarmRequest;
import sopt.collaboration.alarmy.alarm.dto.response.AlarmCheckListResponse;
import sopt.collaboration.alarmy.alarm.dto.response.AlarmCheckResponse;
import sopt.collaboration.alarmy.alarm.dto.response.AlarmResponse;
import sopt.collaboration.alarmy.alarm.repository.AlarmRepository;
import sopt.collaboration.alarmy.global.error.exception.NoSuchMemberException;
import sopt.collaboration.alarmy.member.domain.Member;
import sopt.collaboration.alarmy.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    public AlarmCheckListResponse getTimeCheckAlarm(final long userId, final LocalDateTime currentTime) {
        String formattedTime = currentTimeFormatter(currentTime);

        Member member = memberRepository.findById(userId)
                .orElseThrow(NoSuchMemberException::new);

        List<Alarm> userAlarmList = alarmRepository.findByMember(member);

        List<AlarmCheckResponse> alarmInfo = userAlarmList.stream()
                .map(alarm -> {
                    String alarmTime = formatTime(alarm.getTimestamp());
                    boolean shouldTrigger = formattedTime.equals(alarmTime);
                    return new AlarmCheckResponse(alarm.getId(), shouldTrigger);

                })
                .collect(Collectors.toList());

        return new AlarmCheckListResponse(alarmInfo);

    }

    private String formatTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    private String currentTimeFormatter(LocalDateTime currentTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return currentTime.format(formatter);
    }
}
