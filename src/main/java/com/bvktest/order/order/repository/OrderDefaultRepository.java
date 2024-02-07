package com.bvktest.order.order.repository;

import com.bvktest.order.order.object.OrderProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDefaultRepository implements OrderRepository {
    private final String SQL_GET_SEQ_ORDER_ID_NEXTVAL = "select lpad(nextval('seq_order_id'), 6, '0') from dual";
    private final String SQL_INSERT_ORDER = "insert into orders values (?, ?, ?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDefaultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String insertOrder(OrderProductDto orderProductDto) {
        String orderId = jdbcTemplate.queryForObject(SQL_GET_SEQ_ORDER_ID_NEXTVAL, String.class);
        Double totalPrice = orderProductDto.getQuantity() * orderProductDto.getPrice();

        jdbcTemplate.update(SQL_INSERT_ORDER,
                orderId,
                orderProductDto.getProductId(),
                orderProductDto.getProductName(),
                orderProductDto.getQuantity(),
                orderProductDto.getPrice(),
                totalPrice);

        return orderId;
    }
}
