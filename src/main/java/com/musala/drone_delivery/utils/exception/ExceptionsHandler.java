package com.musala.drone_delivery.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException) {

        var httpStatus = HttpStatus.BAD_REQUEST;

        var exceptionPayLoad = new ExceptionPayLoad(
                badRequestException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);

    }

}
