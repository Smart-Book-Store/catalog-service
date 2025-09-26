package dev.cuong.smartbookstore.catalogservice.web;

import dev.cuong.smartbookstore.catalogservice.domain.BookAlreadyExistsException;
import dev.cuong.smartbookstore.catalogservice.domain.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookControllerAdvice {

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(BookAlreadyExistsException.class)
  public ResponseEntity<String> handleBookAlreadyExists(BookAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
  }

  /**
   * Handle exceptions related to validation errors in method arguments.
   * This advice catches MethodArgumentNotValidException and returns a JSON response with field names and error messages for validation failures.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    var errors = new HashMap<String, String>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.badRequest().body(errors);
  }
}
