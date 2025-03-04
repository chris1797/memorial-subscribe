package memorial.core.config.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR(500, "-1", "서버 오류"),
    INVALID_INPUT_VALUE(400, "-2", "잘못된 요청입니다.");

    private final int status;
    private final String code;
    private final String message;
}
