package br.com.zup.orangetalents.handler;

import br.com.zup.orangetalents.Exception.ArgumentsNotValidExceptionDetails;
import br.com.zup.orangetalents.Exception.NotUniqueException;
import br.com.zup.orangetalents.Exception.ResourceNotFoundException;
import br.com.zup.orangetalents.Exception.ResourceNotFoundExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotUniqueException.class)
    protected ResponseEntity<Object> handleArgumentNotValid(NotUniqueException notUniqueException) {

        ArgumentsNotValidExceptionDetails argumentsNotValidExceptionDetails = new ArgumentsNotValidExceptionDetails(
                "Campos inválido",
                "Por favor, arrume os campos que estão inválidos e tente novamente.",
                notUniqueException.getFieldName(),
                notUniqueException.getMessage()
        );

        return new ResponseEntity<>(argumentsNotValidExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(ResourceNotFoundException resourceNotFoundException) {

        ResourceNotFoundExceptionDetails resourceNotFoundExceptionDetails = new ResourceNotFoundExceptionDetails(
                "Usuário não encontrado",
                "O usuário não foi encontrado."
        );

        return new ResponseEntity<Object>(resourceNotFoundExceptionDetails, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException methodArgumentNotValidException,
             HttpHeaders headers, HttpStatus status, WebRequest request)     {

        List<FieldError> fieldsErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        String fields = fieldsErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldsErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ArgumentsNotValidExceptionDetails argumentsNotValidExceptionDetails = new ArgumentsNotValidExceptionDetails(
                "Campos inválidos",
                "Por favor, arrume os campos que estão inválidos e tente novamente.",
                fields,
                fieldsMessage
        );

        return new ResponseEntity<>(argumentsNotValidExceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
