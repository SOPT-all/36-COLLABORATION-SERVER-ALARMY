package sopt.collaboration.alarmy.phrase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.collaboration.alarmy.phrase.domain.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {
}
