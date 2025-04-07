package memorial.core.api.request;

public record LoginRequestDto(
        String name,
        String password
) { }
