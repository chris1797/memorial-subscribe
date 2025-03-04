package memorial.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import memorial.core.domain.memorial.Memorial;

@Getter
@Setter
@Entity
@Table(name = "memorial_dead")
public class MemorialDead {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "memorial_id")
    private Memorial memorial;

}