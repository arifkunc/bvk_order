package com.bvktest.order.order.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductRequest {
    private String traceId;
    private String productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
