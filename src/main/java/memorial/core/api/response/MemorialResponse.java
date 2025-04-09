package memorial.core.api.response;

import memorial.core.domain.memorial.Memorial;

public record MemorialResponse(
    Long id,
    Long memberId,
    Long churchId,
    String title,
    boolean isInside,
    boolean isPublic
) {

    public static MemorialResponse from(Memorial memorial) {
        return new MemorialResponse(
            memorial.getId(),
            memorial.getMember().getId(),
            memorial.getChurch().getId(),
            memorial.getTitle(),
            memorial.getIsInside(),
            memorial.getIsPublic()
        );
    }
}
