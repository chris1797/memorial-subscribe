package memorial.core.domain.memorial;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.api.response.MemorialResponseDto;
import memorial.core.common.enums.MemorialStatus;
import memorial.core.domain.BaseEntity;
import memorial.core.domain.church.Church;
import memorial.core.domain.member.Member;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Memorial extends BaseEntity {

    @Column(nullable = false)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "church_id", nullable = false)
    private Church church;


    public static Memorial of(String title, Boolean isInside, Boolean isPublic, Member member, Church church) {
        return new Memorial(
                title,
                isInside,
                isPublic,
                member,
                church
        );
    }

    public MemorialResponseDto toResponseDto() {
        return new MemorialResponseDto(
                this.getId(),
                this.member.getId(),
                this.church.getId(),
                this.title,
                this.isInside,
                this.isPublic
        );
    }

    private Memorial(String title, Boolean isInside, Boolean isPublic, Member member, Church church) {
        this.title = title;
        this.isInside = isInside;
        this.isPublic = isPublic;
        this.member = member;
        this.church = church;
    }

}