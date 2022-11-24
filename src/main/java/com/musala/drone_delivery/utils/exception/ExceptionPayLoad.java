package com.musala.drone_delivery.utils.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
public class ExceptionPayLoad {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public ExceptionPayLoad(String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
