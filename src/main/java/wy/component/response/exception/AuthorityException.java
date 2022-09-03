package wy.component.response.exception;

public class AuthorityException extends HttpException {
    public AuthorityException(int code) {
        this.code = code;
    }
}
