package memorial.core.domain.memorialDead;

import jakarta.persistence.*;
import lombok.Getter;
import memorial.core.api.request.MemorialDeadDto;
import memorial.core.domain.BaseEntity;
import memorial.core.domain.memorial.Memorial;

import java.time.LocalDate;

@Entity
@Getter
public class MemorialDead extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private LocalDate birthDate;

    private LocalDate deathDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memorial_id", nullable = false)
    private Memorial memorial;



    public static MemorialDead of(MemorialDeadDto memorialDeadDto, Memorial memorial) {
        return new MemorialDead(
                memorialDeadDto.name(),
                memorialDeadDto.birthDate(),
                memorialDeadDto.deathDate(),
                memorial
        );
    }

    private MemorialDead(String name, LocalDate birthDate, LocalDate deathDate, Memorial memorial) {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.memorial = memorial;
    }
}
