package med.hospital.api.infra.Errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){
//        var errors = e.getFieldErrors(); is a List<FieldError> (*)
        var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ValidationIntegrity.class)
    public ResponseEntity errorHandleValidationsIntegrity(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandleBusinessValidations(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DataErrorValidation(String campo, String error) {
        public DataErrorValidation(FieldError error) { // (*) That's why is the argument here
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
