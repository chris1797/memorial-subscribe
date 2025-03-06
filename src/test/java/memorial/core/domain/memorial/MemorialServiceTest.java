package memorial.core.domain.memorial;

import memorial.core.api.request.MemorialRequestDto;
import memorial.core.common.enums.MemorialStatus;
import memorial.core.domain.Member;
import memorial.core.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemorialServiceTest {

    // Test Fixtures
    static MemorialRequestDto requestDto;
    static Member testMember;

    @Autowired
    private MemorialRepository memorialRepository;

    @Autowired
    private MemberRepository memberRepository;


    @BeforeAll
    static void setUp() {
        // Test Fixtures 세팅
        requestDto = new MemorialRequestDto(
                1L,
                "test",
                MemorialStatus.ACTIVE,
                false,
                false);

        Member member = new Member();
        member.setName("testMember");

        testMember = member;
    }


    @DisplayName("추모관 저장 테스트")
    @Test
    void memorialSaveTestWithMember() {
        // given
        Member savedMember = memberRepository.save(testMember);
        Memorial memorial = Memorial.of(requestDto, savedMember);

        // when
        Memorial saveEntity = memorialRepository.save(memorial);

        // then
        assertNotNull(saveEntity);
        assertEquals(savedMember.getId(), memorial.getMember().getId());
    }

}