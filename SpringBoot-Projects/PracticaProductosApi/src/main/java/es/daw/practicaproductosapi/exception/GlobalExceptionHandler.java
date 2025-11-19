package es.daw.practicaproductosapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationBody(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = ex.getBindingResult()
        .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage()
                ));

        ErrorDTO errorDTO = new ErrorDTO(
                "Error de la validacion",
                LocalDateTime.now(),
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Error de constraint violation");
        errorDTO.setTimestamp(LocalDateTime.now());

        Map<String,String> errors = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(cv -> errors.put(cv.getPropertyPath().toString(), cv.getMessage()));

        errorDTO.setDetails(errors);
        return new  ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Pedazo de error");
        errorDTO.setTimestamp(LocalDateTime.now());

        Map<String,String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        errorDTO.setDetails(errors);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);


    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorDTO> handleNumberFormatException(NumberFormatException ex, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                "Error numerico",
                LocalDateTime.now(),
                new HashMap<>()
        );

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

    }


    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductoNotFoundException(ProductoNotFoundException ex, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Producto no encontrado");
        errorDTO.setTimestamp(LocalDateTime.now());

        Map<String,String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        errorDTO.setDetails(errors);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }




}
