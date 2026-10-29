package org.example.provap1fullstack.dto;

public class ErrorFieldResponse {
    private String name;
    private String message;

    public ErrorFieldResponse(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}