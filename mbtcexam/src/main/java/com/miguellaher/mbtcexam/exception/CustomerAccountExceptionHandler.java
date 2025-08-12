package com.miguellaher.mbtcexam.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import com.miguellaher.mbtcexam.controllers.CustomerAccountController;
import com.miguellaher.mbtcexam.dto.Response;

@ControllerAdvice(assignableTypes = {CustomerAccountController.class})
public class CustomerAccountExceptionHandler {

    private MessageSource messageSource;
    
    public CustomerAccountExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    // Exception handler for invalid body request (e.g no customer name, no customer mobile)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleInvalidBodyRequest(@RequestBody MethodArgumentNotValidException ex) {
        Response response = new Response();
        response.setTransactionStatusCode(HttpStatus.BAD_REQUEST);
        response.setTransactionStatusDescription(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        
        return ResponseEntity.badRequest().body(response);
    }
    
    // Exception handler for invalid account type
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleInvalidAccountType(@RequestBody HttpMessageNotReadableException ex) {
        Response response = new Response();
        response.setTransactionStatusCode(HttpStatus.BAD_REQUEST);
        response.setTransactionStatusDescription(
            messageSource.getMessage("com.miguellaher.mbtcexam.dto.CreateAccountResponse.accountType.invalid",
            null,
            LocaleContextHolder.getLocale()));

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Response> handleCustomerNotFound(@RequestBody CustomerNotFoundException ex) {
        Response response = new Response();
        response.setTransactionStatusCode(HttpStatus.NOT_FOUND);
        response.setTransactionStatusDescription(
            messageSource.getMessage("com.miguellaher.mbtcexam.dto.SearchCustomerResponse.customer.notFound",
            null,
            LocaleContextHolder.getLocale()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response> handleDuplicateFieldError(@RequestBody DataIntegrityViolationException ex) {
        Response response = new Response();
        response.setTransactionStatusCode(HttpStatus.CONFLICT);
        response.setTransactionStatusDescription(
                messageSource.getMessage("com.miguellaher.mbtcexam.dto.CreateAccountResponse.customer.duplicateField",
                        null,
                        LocaleContextHolder.getLocale()));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
