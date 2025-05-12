package sopt.collaboration.alarmy.alarm.domain;

import jakarta.persistence.*;
import sopt.collaboration.alarmy.member.domain.Member;

import java.time.LocalDateTime;

@Entity
public class Alarm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timeStamp;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
