package memorial.core.domain.memorial;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import memorial.core.api.request.MemorialRequestDto;
import memorial.core.common.enums.MemorialStatus;
import memorial.core.domain.BaseEntity;
import memorial.core.domain.Member;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
public class Memorial extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private MemorialStatus memorialStatus;

    private Boolean isInside;

    private Boolean isPublic;


    public static Memorial of(MemorialRequestDto requestDto) {
        Memorial memorial = new Memorial();
        memorial.setTitle(requestDto.title());
        memorial.setIsInside(requestDto.isInside());
        memorial.setIsPublic(requestDto.isPublic());
        return memorial;
    }
}