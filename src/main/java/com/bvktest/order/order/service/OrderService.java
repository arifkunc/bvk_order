package com.bvktest.order.order.service;

import com.bvktest.order.order.object.OrderProductDto;

public interface OrderService {
    /**
     *
     * @param orderProductDto
     * @return new order id
     */
    String orderProduct(OrderProductDto orderProductDto);
}
