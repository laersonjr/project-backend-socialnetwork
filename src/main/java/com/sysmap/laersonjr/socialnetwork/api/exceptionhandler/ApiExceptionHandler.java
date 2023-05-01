package com.sysmap.laersonjr.socialnetwork.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

    private List<ErrorDetails.ProblemError> getProblemErrors(List<ObjectError> allErrors) {
        return allErrors.stream()
                .map(objectError -> new ErrorDetails.ProblemError(((FieldError) objectError).getField(),
                        messageSource.getMessage(objectError, LocaleContextHolder.getLocale())))
                .collect(Collectors.toList());
    }

}
