package memorial.core.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import memorial.core.common.enums.BenefitType;
import memorial.core.common.enums.MemberGrade;
import memorial.core.common.enums.MemberStatus;
import memorial.core.domain.BaseEntity;
import memorial.core.domain.memorial.Memorial;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {

    private String name;

    private String phone;
    private LocalDate birthDate;
    private MemberGrade memberGrade;
    private MemberStatus memberStatus;

    private Boolean isBenefit;
    private BenefitType benefitType;


    /**
     * 회원의 추모관 생성 가능여부 검사
     * @return boolean
     */
    public boolean canMakeMemorial() {
        return this.memberStatus == MemberStatus.ACTIVE && this.memberGrade != MemberGrade.FREE;
    }
}