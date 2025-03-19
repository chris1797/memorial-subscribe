package memorial.core.domain.benefitExpire;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BenefitExpireService {

    private final BenefitExpireRepository benefitExpireRepository;

    public BenefitExpireDate getBenefitExpireDate() {
        return benefitExpireRepository.findAll().getFirst();
    }
}
