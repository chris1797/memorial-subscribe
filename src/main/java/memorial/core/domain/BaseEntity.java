package memorial.core.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/*
@MappedSuperclass 자체는 엔티티가 아니고 상속을 위한 클래스
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

}
