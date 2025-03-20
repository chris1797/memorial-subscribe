package memorial.core.domain.member;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    public MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    static Member testMember;


    @BeforeAll
    static void testFixturesSetup() {
        // Test Fixtures 세팅
        Member member = new Member();
        member.setName("Chris");
        member.setPhone("01011113333");
        member.setPassword("1234567");
        testMember = member;
    }

    @Test
    @DisplayName("전화번호 중복 확인 테스트")
    void isExistPhone() {
        // given
        String phone = "01011112222";
        when(memberRepository.existsByPhone(phone)).thenReturn(true);

        // when
        Boolean isExist = memberService.isExistPhone(phone);

        // then
        assertTrue(isExist);
    }
}