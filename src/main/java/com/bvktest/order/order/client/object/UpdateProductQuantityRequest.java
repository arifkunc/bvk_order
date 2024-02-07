package com.bvktest.order.order.client.object;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UpdateProductQuantityRequest {
    private String traceId;
    /**
     * The value can't be zero. Less than zero value means quantity decreases
     */
    private Integer quantityDelta;
}
