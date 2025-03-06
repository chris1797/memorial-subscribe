package memorial.core.domain.memorial;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.common.enums.MemorialStatus;
import memorial.core.domain.BaseEntity;
import memorial.core.domain.member.Member;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Memorial extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String title;

    private MemorialStatus memorialStatus;

    private Boolean isInside;

    private Boolean isPublic;

    private Memorial(String title, MemorialStatus memorialStatus, Boolean isInside, Boolean isPublic, Member member) {
        this.title = title;
        this.memorialStatus = memorialStatus;
        this.isInside = isInside;
        this.isPublic = isPublic;
        this.member = member;
    }

    public static Memorial of(MemorialRequestDto requestDto, Member member) {
        return new Memorial(
                requestDto.title(),
                requestDto.memorialStatus(),
                requestDto.isInside(),
                requestDto.isPublic(),
                member
        );
    }
}