package dev.charu.productcatalogservice.controller;

import dev.charu.productcatalogservice.dtos.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
   @ExceptionHandler
    public ResponseEntity<ErrorResponseDto>createexception(Exception exception){
    ErrorResponseDto errorresponsedtos=new ErrorResponseDto();
    errorresponsedtos.setMessage(exception.getMessage());
    ResponseEntity<ErrorResponseDto>response=new ResponseEntity<>(errorresponsedtos, HttpStatus.OK);
    return response;
}
}
