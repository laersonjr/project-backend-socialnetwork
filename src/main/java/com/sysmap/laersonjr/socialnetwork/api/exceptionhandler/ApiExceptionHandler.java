package com.sysmap.laersonjr.socialnetwork.api.exceptionhandler;

import com.sysmap.laersonjr.socialnetwork.domain.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ErrorDetails.ProblemError> errors = getProblemErrors(ex.getBindingResult().getAllErrors());

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(status.value());
        errorDetails.setTitle(messageSource.getMessage("invalid-fields", null, LocaleContextHolder.getLocale()));
        errorDetails.setProblemErrorList(errors);

        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }

    @ExceptionHandler(ForbiddenActionException.class)
    private ResponseEntity<Object> handleForbiddenActionException(ForbiddenActionException ex, WebRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorDetails errorDetails = getDetailsErrors(status, ex);
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    private ResponseEntity<Object> handleTokenNotFoundException(TokenNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorDetails errorDetails = getDetailsErrors(status, ex);
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(TokenInvalidException.class)
    private ResponseEntity<Object> handleTokenInvalidException(TokenInvalidException ex, WebRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorDetails errorDetails = getDetailsErrors(status, ex);
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    private ResponseEntity<Object> handleIncorrectPasswordException(IncorrectPasswordException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDetails errorDetails = getDetailsErrors(status, ex);
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails errorDetails = getDetailsErrors(status, ex);
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(PostNotFoundException.class)
    private ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails errorDetails = getDetailsErrors(status, ex);
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    private ResponseEntity<Object> handleCommentNotFoundException(CommentNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails errorDetails = getDetailsErrors(status, ex);
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    private List<ErrorDetails.ProblemError> getProblemErrors(List<ObjectError> allErrors) {
        return allErrors.stream()
                .map(objectError -> new ErrorDetails.ProblemError(((FieldError) objectError).getField(),
                        messageSource.getMessage(objectError, LocaleContextHolder.getLocale())))
                .collect(Collectors.toList());
    }

    private ErrorDetails getDetailsErrors(HttpStatus httpStatus, RuntimeException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(httpStatus.value());
        errorDetails.setTitle(ex.getMessage());
        return errorDetails;
    }

}
