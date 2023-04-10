package com.bsuir.laboratoryWork.project.advice;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import com.bsuir.laboratoryWork.project.model.ErrorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(InvalidParametersException.class)
    public ResponseEntity<ErrorData> exceptionInvalidParametersHandler(InvalidParametersException e){
        log.info("InvalidParametersException thrown");
        ErrorData errorData = new ErrorData(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorData);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorData> exceptionsNumberFormatHandler(NumberFormatException e){
        log.info("NumberFormatException thrown");
        ErrorData errorData = new ErrorData("Length/Height value is not a number - cannot convert to integer");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorData);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorData> exceptionNoSuchElementException(NoSuchElementException e){
        log.info("NoSuchElementException thrown");
        ErrorData errorData = new ErrorData("POST bulk request has empty list");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorData);
    }
}
