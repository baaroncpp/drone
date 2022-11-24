package com.musala.drone_delivery.utils;

import com.musala.drone_delivery.utils.exception.BadRequestException;

import java.util.Optional;

/**
 * @Author bkaaron
 * @Project drone_delivery
 * @Date 11/24/22
 */
public class Validate {

    private Validate() {}

    public static void isTrue(boolean value, String message, Object ... params){
        if (!value) {
            throw new BadRequestException(message, params);
        }
    }

    public static void notNull(Object value, String message, String ... params){

        if (value == null) {
            throw new BadRequestException(message, params);
        }
    }

    public static void isPresent(Optional<?> value, String message, Object ... params){
        if(!value.isPresent()){
            throw new BadRequestException(String.format(message, params));
        }
    }

}
