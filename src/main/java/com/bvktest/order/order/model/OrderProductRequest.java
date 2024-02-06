package com.bvktest.order.order.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductRequest {
    private String productId;
    private Integer quantity;
}
