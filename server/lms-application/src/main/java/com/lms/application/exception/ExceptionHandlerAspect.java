package com.lms.application.exception;

import com.lms.application.messages.Messages;
import com.lms.atom.exception.AtomException;
import com.lms.client.api.exception.ClientApiException;
import com.lms.common.dto.response.ActionResponse;
import com.lms.configuration.exception.ConfigurationException;
import com.lms.security.exception.SecurityException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;

@Aspect
@Component
public class ExceptionHandlerAspect {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAspect.class);

    @Around(value = "execution(* com.lms.application.controller..* (..))")
    public ActionResponse handle(ProceedingJoinPoint point) {
        try {
            return (ActionResponse) point.proceed();
        } catch (Exception ex) {
            if (ex instanceof SecurityException ||
                    ex instanceof AtomException ||
                    ex instanceof ConfigurationException ||
                    ex instanceof LMSAccessDeniedException ||
                    (ex instanceof PersistenceException && ex.getCause() instanceof ConstraintViolationException)) {
                return new ActionResponse(false, ex.getMessage());

            } else if ((ex instanceof PersistenceException && ex.getCause() instanceof ConstraintViolationException)
                    || (ex instanceof DataIntegrityViolationException && ex.getCause() instanceof ConstraintViolationException)) {
                logger.error(ex.getMessage());
                return new ActionResponse(false, Messages.get("objectIsInUse"));
            } else if (ex instanceof ClientApiException) {
                logger.error(ex.getMessage());
                return new ActionResponse(false, ex.getMessage(), ((ClientApiException) ex).getCode().getValue());
            }
            return new ActionResponse(false, Messages.get("unexpectedError"));
        } catch (Throwable throwable) {
            return new ActionResponse(false, Messages.get("unexpectedError"));
        }
    }

}
