package wy.component.response.exception;

public class CustomizeException extends RuntimeException {

    protected Integer code;

    protected String message;

    public CustomizeException(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
