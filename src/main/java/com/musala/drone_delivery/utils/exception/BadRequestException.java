package com.musala.drone_delivery.utils.exception;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
public class BadRequestException extends RuntimeException{
    final String message;

    public BadRequestException(String message){
        super(message);
        this.message = message;
    }

    public BadRequestException(String message,Object ... messageConstants){
        this.message = String.format(message,messageConstants);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
