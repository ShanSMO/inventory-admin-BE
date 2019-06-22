package com.ims.inventory.dtos;

import java.util.List;

public class ResponseDto {

    private Object responseObject;
    private List<?> responseItems;
    private String status;
    private String message;

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

    public List<?> getResponseItems() {
        return responseItems;
    }

    public void setResponseItems(List<?> responseItems) {
        this.responseItems = responseItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
