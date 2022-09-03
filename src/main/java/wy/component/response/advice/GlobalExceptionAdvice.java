package wy.component.response.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wy.component.response.config.ExceptionCodeConfig;
import wy.component.response.exception.CustomizeException;
import wy.component.response.exception.HttpException;
import wy.component.response.vo.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * <h2>全局异常捕获处理</h2>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {


    @Autowired
    private ExceptionCodeConfig codeConfig;

    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handleException(HttpServletRequest req, Exception e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        log.error("commerce service has error: [{}]", e.toString());
        return new CommonResponse<>(9999, "服务器异常", method + " " + requestUrl);
    }


    @ExceptionHandler(HttpException.class)
    public CommonResponse<String> handleHttpException(HttpServletRequest req, HttpException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        return new CommonResponse<>(e.getCode(), codeConfig.getMessage(e.getCode()), method + " " + requestUrl);
    }

    @ExceptionHandler(CustomizeException.class)
    public CommonResponse<String> handleCustomizeException(HttpServletRequest req, CustomizeException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        return new CommonResponse<>(e.getCode(), e.getMessage(), method + " " + requestUrl);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<String> handleBeanValidation(HttpServletRequest req, MethodArgumentNotValidException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = this.formatAllErrorMessages(errors);

        return new CommonResponse<>(10001, message, method + " " + requestUrl);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResponse<String> handleConstraintException(HttpServletRequest req, ConstraintViolationException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = e.getMessage();

        return new CommonResponse<>(10001, message, method + " " + requestUrl);
    }

    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage()).append(';')
        );
        return errorMsg.toString();
    }
}
