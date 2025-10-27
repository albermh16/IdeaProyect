package es.daw.productoapirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

//        List<String> errores = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                .toList();

        Map<String, String> errores = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errores.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDTO errorDTO = new ErrorDTO(
                "Error de validacion",
                LocalDateTime.now(),
                errores
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Pedazo de error generico");
        errorDTO.setTimestamp(LocalDateTime.now());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());

        return null;
    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorDTO> handleNumberFormatException(NumberFormatException ex) {
        ErrorDTO error = new ErrorDTO(
                "Invalid number format... torpedo",
                LocalDateTime.now(),
                new HashMap<>()

                );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

