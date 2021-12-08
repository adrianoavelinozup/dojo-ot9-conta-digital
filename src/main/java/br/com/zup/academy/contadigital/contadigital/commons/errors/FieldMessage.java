package br.com.zup.academy.contadigital.contadigital.commons.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

public class FieldMessage {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fieldName;
    private String message;

    @Deprecated
    public FieldMessage() {
    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
