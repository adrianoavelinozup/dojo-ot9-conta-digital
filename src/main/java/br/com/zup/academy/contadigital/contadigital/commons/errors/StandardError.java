package br.com.zup.academy.contadigital.contadigital.commons.errors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class StandardError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    private Integer code;
    private HttpStatus status;
    private String path;

    @Deprecated
    public StandardError() {
    }

    public StandardError(Instant timestamp, Integer code, HttpStatus status, String path) {
        super();
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }
}