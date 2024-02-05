package com.bvktest.order.common.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Error {
    private String code;
    private String message;
}