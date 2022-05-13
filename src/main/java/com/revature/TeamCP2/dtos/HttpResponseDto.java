package com.revature.TeamCP2.dtos;

public class HttpResponseDto {

    private Integer statusCode;
    private String message;
    private Object data;

    public HttpResponseDto() {}

    public HttpResponseDto(Integer statusCode, String message, Object object) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = object;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
