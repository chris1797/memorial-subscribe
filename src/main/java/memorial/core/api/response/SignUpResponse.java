package memorial.core.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import memorial.core.common.enums.BenefitType;
import memorial.core.common.enums.MemberGrade;
import memorial.core.domain.member.Member;

@Getter
@Setter
@AllArgsConstructor
public class SignUpResponse {

    private Long id;
    private String name;
    private String phone;
    private MemberGrade memberGrade;
    private BenefitType benefitType;
    private Boolean isBenefit;

    public static SignUpResponse of(Member savedMember) {
        return new SignUpResponse(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getPhone(),
                savedMember.getMemberGrade(),
                savedMember.getBenefitType(),
                savedMember.getIsBenefit()
        );
    }
}
