package sopt.collaboration.alarmy.member.domain;

import jakarta.persistence.*;
import sopt.collaboration.alarmy.phrase.domain.Phrase;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phrase_id")
    private Phrase phrase;
}
