package memorial.core.domain.memorial;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.api.response.MemorialResponse;
import memorial.core.domain.church.Church;
import memorial.core.domain.church.ChurchRepository;
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
    private final ChurchRepository churchRepository;


    @Transactional(rollbackFor = Exception.class)
    public MemorialResponse save(MemorialRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Church church = churchRepository.getReferenceById(requestDto.churchId());

        if (!member.canMakeMemorial()) {
            throw new IllegalArgumentException("Member cannot make memorials");
        }

        Memorial memorial = Memorial.of(
                requestDto.title(),
                requestDto.isInside(),
                requestDto.isPublic(),
                member,
                church
        );

        Memorial savedMemorial = memorialRepository.save(memorial);
        return MemorialResponse.from(savedMemorial);
    }
}
