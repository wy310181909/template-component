package wy.component.response.exception;

public class ServerErrorException extends HttpException {
    public ServerErrorException(int code){
        this.code = code;
    }
}
