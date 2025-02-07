package com.workintech.zoo.exceptions;

public class ZooErrorResponse {
    private String message;
    private int status;
    private long timestamp;


    public ZooErrorResponse(int status, String message,long timestamp ) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }


    public ZooErrorResponse() {

    }

    // Getter metodları
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setter metodları
    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

