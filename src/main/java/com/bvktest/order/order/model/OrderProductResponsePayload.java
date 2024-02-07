package com.bvktest.order.order.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductResponsePayload {
    private String orderId;
}
