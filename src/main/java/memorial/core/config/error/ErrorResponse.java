package memorial.core.config.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String code;
    private String message;
    private List<FieldError> errors;

    private ErrorResponse(ErrorCode code, List<FieldError> fieldErrors) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.errors = fieldErrors;
    }

    private ErrorResponse(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

}
