package memorial.core.api.response;

import memorial.core.common.enums.BenefitType;
import memorial.core.common.enums.MemberGrade;
import memorial.core.domain.member.Member;

public record SignUpResponse(
    Long id,
    String name,
    String phone,
    MemberGrade memberGrade,
    BenefitType benefitType,
    Boolean isBenefit
) {

    public static SignUpResponse from(Member member) {
        return new SignUpResponse(
                member.getId(),
                member.getName(),
                member.getPhone(),
                member.getMemberGrade(),
                member.getBenefitType(),
                member.getIsBenefit()
        );
    }
}
