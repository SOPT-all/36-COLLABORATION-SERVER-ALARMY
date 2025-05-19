package sopt.collaboration.alarmy.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.collaboration.alarmy.global.error.exception.NoSuchMemberException;
import sopt.collaboration.alarmy.global.error.exception.PhraseNotAssignedException;
import sopt.collaboration.alarmy.member.domain.Member;
import sopt.collaboration.alarmy.member.repository.MemberRepository;
import sopt.collaboration.alarmy.phrase.domain.Phrase;
import sopt.collaboration.alarmy.member.dto.response.PhraseResponse;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public PhraseResponse getPhrase(final long userId) {
        Member member = getMember(userId);
        Phrase phrase = member.getPhrase();

        if (phrase == null) {
            throw new PhraseNotAssignedException();
        }

        return new PhraseResponse(phrase.getId().intValue());
    }

    private Member getMember(long userId) {
        return memberRepository.findById(userId)
                .orElseThrow(NoSuchMemberException::new);
    }
}
