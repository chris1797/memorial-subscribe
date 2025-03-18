package memorial.core.domain.memorialDead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemorialDeadRepository extends JpaRepository<MemorialDead, Long> {
}
