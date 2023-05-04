package br.com.antunes.gustavo.carrentproject.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       String error = "Malformed JSON request";
       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
   }

   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
   }

   @ExceptionHandler(EntityNotFoundException.class)
   protected ResponseEntity<Object> handleEntityNotFound(
           EntityNotFoundException ex) {
       ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
       apiError.setMessage(ex.getMessage());
       return buildResponseEntity(apiError);
   }

   @ExceptionHandler(NullPointerException.class)
   protected ResponseEntity<Object> handleNullPointerException(
           NullPointerException ex) {
       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
       apiError.setMessage(ex.getMessage());
       return buildResponseEntity(apiError);
   }

   @ExceptionHandler(CustomException.class)
   protected ResponseEntity<Object> handleCustomException(
           CustomException ex) {
       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
       apiError.setMessage(ex.getMessage());
       return buildResponseEntity(apiError);
   }

   @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(
              AccessDeniedException ex) {
         ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
         apiError.setMessage(ex.getMessage());
         return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<Object> handleExpiredJwtException(
              ExpiredJwtException ex) {
         ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
         apiError.setMessage(ex.getMessage());
         return buildResponseEntity(apiError);
    }
}