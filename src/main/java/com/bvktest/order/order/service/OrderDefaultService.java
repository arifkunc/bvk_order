package com.bvktest.order.order.service;

import com.bvktest.order.order.client.object.UpdateProductQuantityRequest;
import com.bvktest.order.order.client.object.UpdateProductQuantityResponse;
import com.bvktest.order.order.object.OrderProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class OrderDefaultService implements OrderService{
    private String apiUrlUpdateQuantity;
    private RestTemplate restTemplate;

    @Autowired
    public OrderDefaultService(@Value("${external-api.inventory.url.update-quantity}") String apiUrlUpdateQuantity,
                               RestTemplateBuilder restTemplateBuilder) {
        this.apiUrlUpdateQuantity = apiUrlUpdateQuantity;
        this.restTemplate = restTemplateBuilder
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .build();
    }

    @Override
    public void orderProduct(OrderProductDto orderProductDto) {
        // trigger update quantity to Inventory Microservice
        int quantityDelta = -orderProductDto.getQuantity();

        UpdateProductQuantityRequest updateProductQuantityRequest = UpdateProductQuantityRequest.builder()
                .traceId(String.valueOf(Instant.now().toEpochMilli()))
                .quantityDelta(quantityDelta)
                .build();

        UpdateProductQuantityResponse updateProductQuantityResponse = restTemplate.patchForObject(apiUrlUpdateQuantity,
                updateProductQuantityRequest,
                UpdateProductQuantityResponse.class,
                orderProductDto.getProductId());

        System.out.println(updateProductQuantityResponse.getData().getMessage());

        // insert new order into database
    }
}
