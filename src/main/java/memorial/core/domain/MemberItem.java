package memorial.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "member_item")
@EntityListeners(AuditingEntityListener.class)
public class MemberItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_item_id", nullable = false)
    private Long id;

}