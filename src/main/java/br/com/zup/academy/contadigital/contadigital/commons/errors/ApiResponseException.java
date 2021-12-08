package br.com.zup.academy.contadigital.contadigital.commons.errors;

import org.springframework.http.HttpStatus;

public class ApiResponseException extends RuntimeException {


    private String fieldName;
    private String message;
    private final HttpStatus httpStatus;

    public ApiResponseException(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ApiResponseException(final String fieldName, final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.fieldName = fieldName;
        this.httpStatus = httpStatus;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}