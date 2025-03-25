package memorial.core.domain.memorial;

import jakarta.transaction.Transactional;
import memorial.core.api.request.MemorialDeadDto;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.api.response.MemorialResponseDto;
import memorial.core.common.enums.ChurchStatus;
import memorial.core.common.enums.MemberGrade;
import memorial.core.common.enums.MemberStatus;
import memorial.core.domain.church.Church;
import memorial.core.domain.church.ChurchRepository;
import memorial.core.domain.member.Member;
import memorial.core.domain.member.MemberRepository;
import memorial.core.domain.member.MemberService;
import memorial.core.domain.memorialDead.MemorialDead;
import memorial.core.domain.memorialDead.MemorialDeadRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemorialServiceTest {

    @InjectMocks
    MemorialService memorialService;

    @Mock
    MemorialRepository memorialRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    ChurchRepository churchRepository;

    // Test Fixtures
    static MemorialDeadDto memorialDeadDto;
    static MemorialRequestDto memorialRequestDto;
    static Church testChurch;
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
                1L,
                "test",
                false,
                false,
                memorialDeadDto);

        testMember = Member.builder()
                .id(1L)
                .name("testName")
                .phone("testPhone")
                .password("testPassword")
                .birthDate(LocalDate.of(1990, 1, 1))
                .memberGrade(MemberGrade.BASIC)
                .memberStatus(MemberStatus.ACTIVE)
                .benefitType(null)
                .isBenefit(null)
                .build();

        testChurch = Church.builder()
                .id(1L)
                .name("testChurch")
                .status(ChurchStatus.ACTIVE)
                .build();
    }


    @Transactional
    @DisplayName("추모관 저장 테스트")
    @Test
    @Rollback(false)
    void memorialSaveTestWithMember() {

        // given
        Memorial memorial = Memorial.of(
                memorialRequestDto.title(),
                memorialRequestDto.isInside(),
                memorialRequestDto.isPublic(),
                testMember,
                testChurch
        );

        // when
        when(churchRepository.getReferenceById(testChurch.getId())).thenReturn(testChurch);
        when(memberRepository.findById(memorialRequestDto.memberId())).thenReturn(Optional.ofNullable(testMember));
        when(memorialRepository.save(any(Memorial.class))).thenReturn(memorial);

        MemorialResponseDto responseDto = memorialService.save(memorialRequestDto);

        // then
        assertNotNull(responseDto);
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