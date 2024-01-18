//package com.insurance.exception;
//
//import com.insurance.payload.ErrorDetails;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.Date;
//
//@RestControllerAdvice
//public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> GlobalExceptionHandler(Exception exception, WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), webRequest.getDescription(false), exception.getMessage()  );
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
