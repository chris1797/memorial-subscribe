package memorial.core.domain.church;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import memorial.core.common.enums.ChurchStatus;
import memorial.core.domain.BaseEntity;

@Entity
@Getter
@Setter
public class Church extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated
    private ChurchStatus churchStatus;

    private String latitude;

    private String longitude;

}
