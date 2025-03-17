package memorial.core.domain.memorial;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.common.enums.MemorialStatus;
import memorial.core.domain.BaseEntity;
import memorial.core.domain.member.Member;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Memorial extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String title;

    @Column(name = "memorial_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemorialStatus memorialStatus = MemorialStatus.ACTIVE;

    // columnDefinition: DDL 생성 시 기본값을 설정함
    // columnDefinition 설정, 혹은 아래처럼 직접 default 값을 설정
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean isInside = true;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isPublic = false;


    public static Memorial of(MemorialRequestDto requestDto, Member member) {
        return new Memorial(
                requestDto.title(),
                requestDto.isInside(),
                requestDto.isPublic(),
                member
        );
    }

    private Memorial(String title, Boolean isInside, Boolean isPublic, Member member) {
        this.title = title;
        this.isInside = isInside;
        this.isPublic = isPublic;
        this.member = member;
    }

}