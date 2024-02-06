package com.bvktest.order.order.client.object;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddProductResponsePayload {
    private String productId;
}
