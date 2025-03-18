package memorial.core.api.request;


public record MemorialRequestDto(
        Long memberId,
        String title,
        Boolean isInside,
        Boolean isPublic,
        MemorialDeadDto memorialDeadDto
) {

}
