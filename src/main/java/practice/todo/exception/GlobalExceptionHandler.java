package practice.todo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//  Handle @Valid Exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult()
		.getFieldErrors()
		.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"Validation Failed",
				errors);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	// Handle custom TodoRuntimeException
    @ExceptionHandler({TodoRuntimeException.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleTodoRuntimeException(RuntimeException ex) {

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
