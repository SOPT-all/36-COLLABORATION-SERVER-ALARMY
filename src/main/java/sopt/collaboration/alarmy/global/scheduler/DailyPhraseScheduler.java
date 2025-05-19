package sopt.collaboration.alarmy.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sopt.collaboration.alarmy.global.error.exception.NoSuchPhraseException;
import sopt.collaboration.alarmy.member.domain.Member;
import sopt.collaboration.alarmy.member.repository.MemberRepository;
import sopt.collaboration.alarmy.phrase.domain.Phrase;
import org.springframework.transaction.annotation.Transactional;
import sopt.collaboration.alarmy.phrase.repository.PhraseRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class DailyPhraseScheduler {

    private final MemberRepository memberRepository;
    private final PhraseRepository phraseRepository;


    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateDailyPhrase() {
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            Phrase randomPhrase = getRandomPhrase();
            member.updatePhrase(randomPhrase);
            System.out.println("member : "+ member.getPhrase());

        }
    }

    private Phrase getRandomPhrase() {
        long randomId = ThreadLocalRandom.current().nextLong(1, 7);
        return phraseRepository.findById(randomId)
                .orElseThrow(NoSuchPhraseException::new);
    }

}
