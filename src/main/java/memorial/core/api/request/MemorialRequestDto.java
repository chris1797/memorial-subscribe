package memorial.core.api.request;

import memorial.core.common.enums.MemorialStatus;

public record MemorialRequestDto(
        Long memberId,
        String title,
        Boolean isInside,
        Boolean isPublic) { }
