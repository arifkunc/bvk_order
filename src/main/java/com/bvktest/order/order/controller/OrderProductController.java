package com.bvktest.order.order.controller;

import com.bvktest.order.common.model.DefaultResponse;
import com.bvktest.order.order.model.OrderProductRequest;
import com.bvktest.order.order.model.OrderProductResponsePayload;
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
    public ResponseEntity<DefaultResponse<OrderProductResponsePayload>> orderProduct(@RequestBody OrderProductRequest request){
        OrderProductResponsePayload responsePayload = execute(request);

        DefaultResponse<OrderProductResponsePayload> response = DefaultResponse.<OrderProductResponsePayload>builder()
                .traceId(request.getTraceId())
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .data(responsePayload)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private OrderProductResponsePayload execute(OrderProductRequest request){
        OrderProductDto orderProductDto = OrderProductDto.builder()
                .productId(request.getProductId())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();

        String orderId = orderService.orderProduct(orderProductDto);

        return OrderProductResponsePayload.builder()
                .orderId(orderId)
                .build();
    }
}
