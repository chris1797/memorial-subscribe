package memorial.core.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.core.api.request.MemberRequestDto;
import memorial.core.domain.benefitExpire.BenefitExpireService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final BenefitExpireService benefitExpireService;
    private final MemberRepository memberRepository;


    public Boolean isExistPhone(String phone) {
        return memberRepository.existsByPhone(phone);
    }

    public Member save(MemberRequestDto memberRequestDto) {
        LocalDate benefitExpireAt = benefitExpireService.getBenefitExpireDate().getBenefitExpireAt();

        Member member = Member.of(memberRequestDto, benefitExpireAt);
        member.setPassword(passwordEncoder.encode(memberRequestDto.password()));

        return memberRepository.save(member);
    }

}
