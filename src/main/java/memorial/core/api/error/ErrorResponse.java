package memorial.core.api.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ErrorResponse {

    private String code;
    private String message;
    private Object error;

    public ErrorResponse(ErrorCode code, String message, Object error) {
        this.code = code.getCode();
        this.message = message;
        this.error = error;
    }

    public ErrorResponse(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static ErrorResponse of(ErrorCode code, String message, Object error) {
        return new ErrorResponse(code, message, error);
    }

}
