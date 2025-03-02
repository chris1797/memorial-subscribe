package memorial.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import memorial.core.common.enums.BenefitType;
import memorial.core.common.enums.MemberGrade;
import memorial.core.common.enums.MemberStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id", nullable = false)
    private Long id;

    private String name;

    private String phone;
    private LocalDate birthDate;
    private MemberGrade memberGrade;
    private MemberStatus memberStatus;

    private Boolean isBenefit;
    private BenefitType benefitType;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "member")
    private List<Memorial> memorials;
}