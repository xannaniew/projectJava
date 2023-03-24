package com.bsuir.laboratoryWork.project.advice;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import com.bsuir.laboratoryWork.project.model.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = Logger.getLogger(ExceptionAdvice.class.getName());
    @ExceptionHandler(InvalidParametersException.class)
    public ResponseEntity<ErrorData> exceptionInvalidParametersHandler(InvalidParametersException e){
        logger.info("InvalidParametersException thrown");
        ErrorData errorData = new ErrorData(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorData);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorData> exceptionsNumberFormatHandler(NumberFormatException e){
        logger.info("NumberFormatException thrown");
        ErrorData errorData = new ErrorData("Length/Height value is not a number - cannot convert to integer");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorData);
    }

}
