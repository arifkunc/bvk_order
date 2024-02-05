package com.bvktest.order.common.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DefaultResponse<P> {
    private String traceId;
    private String time;
    private P data;
}
