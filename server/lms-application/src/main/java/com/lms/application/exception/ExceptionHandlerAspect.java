package com.lms.application.exception;

import com.lms.application.messages.Messages;
import com.lms.atom.exception.AtomException;
import com.lms.common.dto.response.ActionResponse;
import com.lms.security.exception.SecurityException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;

@Aspect
@Component
public class ExceptionHandlerAspect {

    @Around(value = "execution(* com.lms.application.controller..* (..))")
    public ActionResponse handle(ProceedingJoinPoint point) {
        try {
            return (ActionResponse) point.proceed();
        } catch (Exception ex) {
            if (ex instanceof SecurityException ||
                    ex instanceof AtomException ||
                    ex instanceof LMSAccessDeniedException ||
                    (ex instanceof PersistenceException && ex.getCause() instanceof ConstraintViolationException)) {
                return new ActionResponse(false, ex.getMessage());
            }
            return new ActionResponse(false, Messages.get("unexpectedError"));
        } catch (Throwable throwable) {
            return new ActionResponse(false, Messages.get("unexpectedError"));
        }
    }

}
