package memorial.core.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import memorial.core.common.enums.MemberGrade;
import memorial.core.domain.member.Member;

@Getter
@Setter
@AllArgsConstructor
public class CustomAuthMemberInfo extends Member {

    private String username;
    private String role;

    public static CustomAuthMemberInfo of(String name, MemberGrade memberGrade) {
        return new CustomAuthMemberInfo(
                name,
                memberGrade.name()
        );
    }
}
