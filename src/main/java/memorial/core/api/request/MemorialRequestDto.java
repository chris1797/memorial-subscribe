package memorial.core.api.request;

public record MemorialRequestDto(
        Long member_id,
        String title,
        Boolean isInside,
        Boolean isPublic) { }
