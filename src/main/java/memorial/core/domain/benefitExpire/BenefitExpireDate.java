package memorial.core.domain.benefitExpire;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class BenefitExpireDate {

    @Id
    private LocalDate benefitExpireAt;
}
