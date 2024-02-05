package com.bvktest.order.common.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponse {
    private String traceId;
    private String time;
    private Error error;
}
