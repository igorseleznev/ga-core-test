package ru.seleznev.gacoretest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.seleznev.gacoretest.dto.CommonResponse;
import ru.seleznev.gacoretest.dto.CommonResponseBuilder;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = LoginAlreadyExistsException.class)
    public ResponseEntity<CommonResponse> handleLoginAlreadyExistsException(LoginAlreadyExistsException e,
                                                                            WebRequest request) {
        logger.error("handle LoginAlreadyExistsException", e);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_XML)
                .body(CommonResponseBuilder.loginAlreadyExistsError());
    }

    @ExceptionHandler(value = ClientUserNotFoundException.class)
    public ResponseEntity<CommonResponse> handleClientUserNotFoundException(ClientUserNotFoundException e,
                                                                            WebRequest request) {
        logger.error("handle ClientUserNotFoundException", e);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_XML)
                .body(CommonResponseBuilder.clientUserNotFoundError());
    }

    @ExceptionHandler(value = WrongClientPasswordException.class)
    public ResponseEntity<CommonResponse> handleWrongClientPasswordException(WrongClientPasswordException e,
                                                                             WebRequest request) {
        logger.error("handle WrongClientPasswordException", e);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_XML)
                .body(CommonResponseBuilder.wrongClientPasswordError());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResponse> handleOtherException(Exception e,
                                                               WebRequest request) {
        logger.error("handle other Exception", e);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_XML)
                .body(CommonResponseBuilder.internalError());
    }
}
