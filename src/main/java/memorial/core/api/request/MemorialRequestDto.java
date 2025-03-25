package memorial.core.api.request;


public record MemorialRequestDto(
        Long memberId,
        Long churchId,
        String title,
        Boolean isInside,
        Boolean isPublic,
        MemorialDeadDto memorialDeadDto
) {

}
