package memorial.core.api;

import lombok.Getter;
import memorial.core.api.error.ErrorCode;
import memorial.core.api.error.ErrorResponse;

@Getter
public class ApiResponse<T> {

    private final ResultType resultType;
    private final T data;
    private final ErrorResponse error;


    public ApiResponse(ResultType resultType, T data, ErrorResponse error) {
        this.resultType = resultType;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse<?> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse<?> error(ErrorCode errorCode) {
        return new ApiResponse<>(ResultType.FAILURE, null, new ErrorResponse(errorCode));
    }

    public static ApiResponse<?> error(ErrorCode errorCode, Object errorData) {
        return new ApiResponse<>(ResultType.FAILURE, null, new ErrorResponse(errorCode, errorCode.getMessage(), errorData));
    }
}
