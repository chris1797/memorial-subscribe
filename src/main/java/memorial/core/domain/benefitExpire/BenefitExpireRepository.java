package memorial.core.domain.benefitExpire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BenefitExpireRepository extends JpaRepository<BenefitExpireDate, LocalDate> {

}
