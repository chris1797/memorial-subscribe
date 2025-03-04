package memorial.core.api.error;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Unexpected Internal Server Error", LogLevel.ERROR),
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
    private final LogLevel logLevel;

    ErrorType(HttpStatus httpStatus, int errorCode, String message, LogLevel logLevel) {
        this.httpStatus = httpStatus;
        this.code = errorCode;
        this.message = message;
        this.logLevel = logLevel;
    }

}
