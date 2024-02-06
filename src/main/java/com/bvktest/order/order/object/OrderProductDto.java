package com.bvktest.order.order.object;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductDto {
    private String productId;
    private Integer quantity;
}
