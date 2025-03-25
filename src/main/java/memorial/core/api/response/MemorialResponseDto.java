package memorial.core.api.response;

import lombok.Data;

@Data
public class MemorialResponseDto {
    private Long id;
    private Long memberId;
    private Long churchId;

    private String title;

    private boolean isInside;
    private boolean isPublic;

    public MemorialResponseDto(Long id, Long memberId, Long churchId, String title, boolean isInside, boolean isPublic) {
        this.id = id;
        this.memberId = memberId;
        this.churchId = churchId;
        this.title = title;
        this.isInside = isInside;
        this.isPublic = isPublic;
    }
}
