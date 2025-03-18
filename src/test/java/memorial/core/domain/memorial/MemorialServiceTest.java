package memorial.core.domain.memorial;

import jakarta.transaction.Transactional;
import memorial.core.api.request.MemorialDeadDto;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.common.enums.MemberGrade;
import memorial.core.domain.member.Member;
import memorial.core.domain.member.MemberRepository;
import memorial.core.domain.memorialDead.MemorialDead;
import memorial.core.domain.memorialDead.MemorialDeadRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemorialServiceTest {

    @Autowired private MemorialRepository memorialRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private MemorialDeadRepository memorialDeadRepository;

    // Test Fixtures
    static MemorialDeadDto memorialDeadDto;
    static MemorialRequestDto memorialRequestDto;
    static Member testMember;


    @BeforeAll
    static void setUp() {
        // Test Fixtures 세팅
        memorialDeadDto = new MemorialDeadDto(
                "deadName",
                LocalDate.of(1970, 1, 1),
                LocalDate.of(2025, 1, 1)
        );

        memorialRequestDto = new MemorialRequestDto(
                1L,
                "test",
                false,
                false,
                memorialDeadDto);

        Member member = new Member();
        member.setName("testMember");

        testMember = member;
    }


    @Transactional
    @DisplayName("추모관 저장 테스트")
    @Test
    @Rollback(false)
    void memorialSaveTestWithMember() {
        // given
        testMember.setMemberGrade(MemberGrade.FREE);
        Member savedMember = memberRepository.save(testMember);
        Memorial memorial = Memorial.of(memorialRequestDto, savedMember);
        MemorialDead memorialDead = MemorialDead.of(memorialDeadDto, memorial);

        // when
        Memorial saveEntity = memorialRepository.save(memorial);
        MemorialDead savedMemorialDead = memorialDeadRepository.save(memorialDead);

        // then
        assertNotNull(saveEntity);
        assertNotNull(savedMemorialDead);
        assertEquals(savedMember.getId(), memorial.getMember().getId());
    }

    @Transactional
    @DisplayName("FREE 회원 추모관 저장 사전검사 테스트")
    @Test
    @Rollback(false)
    void freeMemberMakeMemorialFailTest() {
        // given
        testMember.setMemberGrade(MemberGrade.FREE);

        // when
        boolean canMakeMemorial = testMember.canMakeMemorial();

        // then
        assertFalse(canMakeMemorial);
    }

}