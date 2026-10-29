package org.example.provap1fullstack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String error;
    private List<ErrorFieldResponse> fields;
    private String path;

    public ErrorResponse(String error, String path) {
        this.error = error;
        this.path = path;
    }

    public ErrorResponse(String error, List<ErrorFieldResponse> fields, String path) {
        this.error = error;
        this.fields = fields;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public List<ErrorFieldResponse> getFields() {
        return fields;
    }

    public String getPath() {
        return path;
    }
}
