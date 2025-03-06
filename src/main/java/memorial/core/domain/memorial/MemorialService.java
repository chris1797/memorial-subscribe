package memorial.core.domain.memorial;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.domain.member.Member;
import memorial.core.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemorialService {

    private final MemorialRepository memorialRepository;
    private final MemberRepository memberRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long save(MemorialRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Memorial memorial = Memorial.of(requestDto, member);
        return memorialRepository.save(memorial).getId();
    }
}
