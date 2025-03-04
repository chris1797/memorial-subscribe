package memorial.core.api.request;

import lombok.Getter;
import memorial.core.domain.memorial.Memorial;

@Getter
public class MemorialRequestDto {

    private Long member_id;
    private String title;
    private Boolean isInside;
    private Boolean isPublic;

}
