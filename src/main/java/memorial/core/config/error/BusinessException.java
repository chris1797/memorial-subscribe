package memorial.core.config.error;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private Integer code;
    private String message;
    private Exception error;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = Integer.valueOf(errorCode.getCode());
    }

}
