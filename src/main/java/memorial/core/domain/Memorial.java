package memorial.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import memorial.core.common.enums.MemorialStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Memorial {

    @Id
    @GeneratedValue
    @Column(name = "memorial_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private MemorialStatus memorialStatus;

    private Boolean isInside;

    private Boolean isPublic;

    @CreatedDate
    private LocalDateTime createdAt;
}