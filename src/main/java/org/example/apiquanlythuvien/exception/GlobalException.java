package org.example.apiquanlythuvien.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse BadRequestExceptionHandler(
      BadRequestException ex) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse NotFoundExceptionHandler(
      NotFoundException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(ConflicException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse ConflicExceptionHandler(
      ConflicException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(InternalServcerEception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse InternalServcerEceptionHandler(
      InternalServcerEception ex) {
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
        LocalDateTime.now());
  }

  // Xử lý lỗi validation
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return new ErrorResponse(HttpStatus.BAD_REQUEST, errors.toString(), LocalDateTime.now());
  }

  // Xử lý lỗi vi phạm ràng buộc dữ liệu
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT, ex.getMostSpecificCause().getMessage(),
        LocalDateTime.now());
  }
}
