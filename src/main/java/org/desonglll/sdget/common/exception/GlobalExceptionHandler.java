package org.desonglll.sdget.common.exception;

import org.desonglll.sdget.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: mikeshinoda
 * @date: 2024/10/4
 * @description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Result> handleRuntimeException(RuntimeException ex) {
        Result result = new Result(0, "error", ex.getMessage(), null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Result> handleGeneralException(GeneralException ex) {
        Result result = new Result(0, "error", ex.getMessage(), null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException ex) {
        Result result = new Result(0, "error", ex.getMessage(), null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<Result> handleEmptyListException(GeneralException ex) {
        Result result = new Result(0, "error", ex.getMessage(), null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
