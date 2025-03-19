package memorial.core.api.request;

import java.time.LocalDate;

public record MemberRequestDto(
//        Long churchId,
        String name,
        String phone,
        String password,
        LocalDate birthDate
) {
}
