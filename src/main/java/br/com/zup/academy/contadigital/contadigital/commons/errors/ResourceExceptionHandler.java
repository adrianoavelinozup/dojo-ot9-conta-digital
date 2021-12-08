package br.com.zup.academy.contadigital.contadigital.commons.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Integer code = status.value();
        ValidationError err = new ValidationError(Instant.now(), code, status, request.getRequestURI());
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            err.addError(fieldError.getField(), message);
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ApiResponseException.class)
    public ResponseEntity<ValidationError> apiExceptionHandler(ApiResponseException e, HttpServletRequest request) {
        HttpStatus status = e.getHttpStatus();
        Integer code = status.value();
        ValidationError err = new ValidationError(Instant.now(), code, status, request.getRequestURI());
        err.addError(e.getFieldName(), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }
}
