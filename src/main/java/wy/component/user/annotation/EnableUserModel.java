package wy.component.user.annotation;

import org.springframework.context.annotation.Import;
import wy.component.user.config.UserModelConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>忽略统一响应注解定义</h1>
 *
 * @author wy*/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Import(UserModelConfiguration.class)
public @interface EnableUserModel {
}
