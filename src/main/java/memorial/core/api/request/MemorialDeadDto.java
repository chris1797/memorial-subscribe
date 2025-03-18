package memorial.core.api.request;

import java.time.LocalDate;

public record MemorialDeadDto(
        String name,
        LocalDate birthDate,
        LocalDate deathDate
) {

}
