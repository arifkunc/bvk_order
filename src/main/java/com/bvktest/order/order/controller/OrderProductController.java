package com.bvktest.order.order.controller;

import com.bvktest.order.common.model.DefaultResponse;
import com.bvktest.order.common.model.SuccessResponsePayload;
import com.bvktest.order.order.model.OrderProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class OrderProductController {
    @PostMapping("/order/v1/order")
    public ResponseEntity<DefaultResponse<SuccessResponsePayload>> orderProduct(@RequestBody OrderProductRequest request){
        // dummy
        DefaultResponse<SuccessResponsePayload> response = DefaultResponse.<SuccessResponsePayload>builder()
                .traceId("abc")
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .data(
                        SuccessResponsePayload.builder()
                                .status("success")
                                .message("process is successfully done")
                                .build()
                )
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
