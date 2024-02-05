package com.bvktest.order.common.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SuccessResponsePayload {
    private String status;
    private String message;
}
