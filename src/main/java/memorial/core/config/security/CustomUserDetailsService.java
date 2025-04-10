package memorial.core.config.security;

import lombok.RequiredArgsConstructor;
import memorial.core.domain.member.Member;
import memorial.core.domain.member.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username));

        CustomAuthMemberInfo customAuthMemberInfo = CustomAuthMemberInfo.of(
                member.getName(),
                member.getPassword(),
                member.getMemberGrade()
        );

        return User.builder()
                .username(customAuthMemberInfo.getUsername())
                .password(customAuthMemberInfo.getPassword())
                .roles(customAuthMemberInfo.getRole())
                .build();
    }
}
