package sopt.collaboration.alarmy.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import sopt.collaboration.alarmy.phrase.domain.Phrase;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phrase_id")
    private Phrase phrase;

    public void updatePhrase(Phrase newPhrase) {
        this.phrase = newPhrase;
    }
}
