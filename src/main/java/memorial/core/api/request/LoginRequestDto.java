package memorial.core.api.request;

import memorial.core.common.enums.MemberGrade;

public record LoginRequestDto(
        String name,
        String password,
        MemberGrade memberGrade
) { }
