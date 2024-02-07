package com.bvktest.order.order.client.object;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UpdateProductQuantityResponse {
    private String traceId;
    private String time;
    private SuccessResponsePayload data;
}
