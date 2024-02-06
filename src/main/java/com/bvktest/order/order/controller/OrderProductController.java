package com.bvktest.order.order.controller;

import com.bvktest.order.common.model.DefaultResponse;
import com.bvktest.order.common.model.SuccessResponsePayload;
import com.bvktest.order.order.model.OrderProductRequest;
import com.bvktest.order.order.object.OrderProductDto;
import com.bvktest.order.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class OrderProductController {
    private OrderService orderService;

    @Autowired
    public OrderProductController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/v1/order")
    public ResponseEntity<DefaultResponse<SuccessResponsePayload>> orderProduct(@RequestBody OrderProductRequest request){
        SuccessResponsePayload responsePayload = execute(request);

        DefaultResponse<SuccessResponsePayload> response = DefaultResponse.<SuccessResponsePayload>builder()
                .traceId(request.getTraceId())
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .data(responsePayload)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private SuccessResponsePayload execute(OrderProductRequest request){
        OrderProductDto orderProductDto = OrderProductDto.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();

        orderService.orderProduct(orderProductDto);

        return SuccessResponsePayload.builder()
                .status("success")
                .message("process is successfully done")
                .build();
    }
}
