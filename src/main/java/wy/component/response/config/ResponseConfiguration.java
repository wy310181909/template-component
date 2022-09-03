package wy.component.response.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wy.component.response.advice.CommonResponseDataAdvice;
import wy.component.response.advice.GlobalExceptionAdvice;

@Data
@Configuration
@ConfigurationProperties("com.wy")
public class ResponseConfiguration {

    private String controllerPath;

    @Bean
    public ExceptionCodeConfig exceptionCodeConfig() {
        return new ExceptionCodeConfig();
    }

    @Bean
    public CommonResponseDataAdvice commonResponseDataAdvice(){
        return new CommonResponseDataAdvice(controllerPath);
    }

    @Bean
    public GlobalExceptionAdvice globalExceptionAdvice(){
        return new GlobalExceptionAdvice();
    }


}
