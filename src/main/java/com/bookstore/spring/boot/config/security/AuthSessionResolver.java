package com.bookstore.spring.boot.config.security;

import com.bookstore.spring.boot.common.exceptions.ApplicationException;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.common.utils.Validator;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthSessionResolver implements HandlerMethodArgumentResolver {

    public AuthSessionResolver(){}

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterAnnotation(AuthSession.class) != null);
    }

    @Override
    public AuthUser resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        AuthUser authUser = null;
        AuthSession authSession = parameter.getParameterAnnotation(AuthSession.class);

        if (authSession != null) {
            try {
                authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                Validator.notNull(authUser, RestAPIStatus.UNAUTHORIZED, "");
            } catch (Exception e) {
                throw new ApplicationException(RestAPIStatus.UNAUTHORIZED, e.getMessage());
            }
        }
        return authUser;
    }

}
