package memorial.core.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.core.api.request.LoginRequestDto;
import memorial.core.api.request.SignUpRequest;
import memorial.core.api.response.LoginResponse;
import memorial.core.api.response.SignUpResponse;
import memorial.core.config.security.JwtTokenProvider;
import memorial.core.domain.benefitExpire.BenefitExpireService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    public Boolean isExistPhone(String phone) {
        return memberRepository.existsByPhone(phone);
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
//        LocalDate benefitExpireAt = benefitExpireService.getBenefitExpireDate().getBenefitExpireAt();
        LocalDate benefitExpireAt = LocalDate.of(2025,12,31);

        memberRepository.findByPhone(signUpRequest.phone())
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
                });

        Member member = Member.of(signUpRequest, benefitExpireAt);
        member.setPassword(passwordEncoder.encode(signUpRequest.password()));

        return SignUpResponse.of(memberRepository.save(member));
    }

    public LoginResponse login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.name(), loginRequestDto.password())
        );

        String jwtToken = jwtTokenProvider.generateToken(authentication);

        // DB를 통한 검증 시
        Member member = memberRepository.findByPhone(loginRequestDto.name())
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 사용자가 없습니다."));

        if (!passwordEncoder.matches(loginRequestDto.password(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponse(jwtToken);
    }
}
