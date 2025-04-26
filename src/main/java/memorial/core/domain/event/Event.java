package memorial.core.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import memorial.core.domain.BaseEntity;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event extends BaseEntity {

    @NotNull
    @ColumnDefault("0")
    @Column(name = "apply_count", nullable = false)
    private Integer applyCount;

    @NotNull
    @Column(name = "limit_count", nullable = false)
    private Integer limitCount;

}