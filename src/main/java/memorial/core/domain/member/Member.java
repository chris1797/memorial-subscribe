package memorial.core.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import memorial.core.api.request.MemberRequestDto;
import memorial.core.common.enums.BenefitType;
import memorial.core.common.enums.MemberGrade;
import memorial.core.common.enums.MemberStatus;
import memorial.core.domain.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Member extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade = MemberGrade.BASIC;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private BenefitType benefitType = BenefitType.WELCOME;

    private Boolean isBenefit;


    private Member(MemberRequestDto requestDto, LocalDate benefitExpireAt) {
        this.name = requestDto.name();
        this.phone = requestDto.phone();
        this.birthDate = requestDto.birthDate();

        setBenefitInfo(benefitExpireAt);
    }

    public static Member of(MemberRequestDto requestDto, LocalDate benefitExpireAt) {
        return new Member(
                requestDto,
                benefitExpireAt
        );
    }

    /**
     * 회원의 추모관 생성 가능여부 검사
     * @return boolean
     */
    public boolean canMakeMemorial() {
        return this.memberStatus == MemberStatus.ACTIVE && this.memberGrade != MemberGrade.FREE;
    }


    public void setBenefitInfo(LocalDate benefitExpireAt) {
        if (LocalDate.now().isAfter(benefitExpireAt)) {
            this.isBenefit = true;
            this.benefitType = BenefitType.WELCOME;
        } else {
            this.isBenefit = false;
            this.benefitType = BenefitType.NONE;
        }

    }

}