package com.bvktest.order.order.repository;

import com.bvktest.order.order.object.OrderProductDto;

public interface OrderRepository {
    /**
     *
     * @param orderProductDto
     * @return new order id
     */
    String insertOrder(OrderProductDto orderProductDto);
}
