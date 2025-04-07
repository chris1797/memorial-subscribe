package memorial.core.api.request;

import java.time.LocalDate;

public record SignUpRequest(
//        Long churchId,
        String name,
        String phone,
        String password,
        LocalDate birthDate
) {
}
