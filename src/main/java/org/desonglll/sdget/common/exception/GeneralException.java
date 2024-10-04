package org.desonglll.sdget.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: mikeshinoda
 * @date: 2024/10/4
 * @description:
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }
}
