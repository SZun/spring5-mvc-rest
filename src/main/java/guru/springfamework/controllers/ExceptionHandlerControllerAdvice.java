package guru.springfamework.controllers;

import guru.springfamework.services.ResouceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest req){
        return new ResponseEntity<>("Res Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
