package com.bvktest.order.common.advice;

import com.bvktest.order.common.constant.ErrorType;
import com.bvktest.order.common.exception.OrderFailedException;
import com.bvktest.order.common.model.Error;
import com.bvktest.order.common.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class OrderResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({OrderFailedException.class})
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(Exception exception, WebRequest webRequest){
        OrderFailedException orderFailedException = (OrderFailedException) exception;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .traceId(String.valueOf(Instant.now().toEpochMilli())) // the value should be same with traceId request. This is just simple set
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .error(
                        Error.builder()
                                .code(ErrorType.ORDER_FAILED.toString())
                                .message(orderFailedException.getMessage())
                                .build()
                )
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleDefaultException(Exception exception, WebRequest webRequest){

        ErrorResponse errorResponse = ErrorResponse.builder()
                .traceId(String.valueOf(Instant.now().toEpochMilli())) // the value should be same with traceId request. This is just simple set
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .error(
                        Error.builder()
                                .code(ErrorType.INTERNAL_ERROR.toString())
                                .message(exception.getMessage())
                                .build()
                )
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
