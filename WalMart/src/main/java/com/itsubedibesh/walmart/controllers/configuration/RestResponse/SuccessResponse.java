package com.itsubedibesh.walmart.controllers.configuration.RestResponse;

public class SuccessResponse {
    private String message = "Success";
    private Integer statusCode = 200;
    private Boolean success = true;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
