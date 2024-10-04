package org.desonglll.sdget.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: mikeshinoda
 * @date: 2024/10/4
 * @description:
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyListException extends RuntimeException {
    public EmptyListException(String message) {
        super(message);
    }
}
