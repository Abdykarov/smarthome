package eu.lundegaard.smarthome.exception.handler;

import eu.lundegaard.smarthome.exception.AppRequestException;
import eu.lundegaard.smarthome.exception.AppResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ilias Abdykarov
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AppRequestException.class)
    public ResponseEntity<Object> handleAppException(AppRequestException e, ServletWebRequest webRequest) {
        log.error("An error ocurred while processing request " +
                webRequest.getRequest().getMethod() + " at " +
                webRequest.getRequest().getRequestURI(), e);

        return handleExceptionInternal(e, new AppResponseException(
                e.getMessage(),
                e.getHttpStatus(),
                e.getClass().getName(),
                ZonedDateTime.now(ZoneId.of("Europe/Prague"))
        ), new HttpHeaders(), e.getHttpStatus(), webRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex, ServletWebRequest webRequest) {
        log.error("An error ocurred while processing request " +
                webRequest.getRequest().getMethod() + " at " +
                webRequest.getRequest().getRequestURI(), ex);

        AppResponseException appResponseException = new AppResponseException(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getClass().getName(),
                ZonedDateTime.now(ZoneId.of("Europe/Prague"))
        );

        return handleExceptionInternal(ex, appResponseException,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}