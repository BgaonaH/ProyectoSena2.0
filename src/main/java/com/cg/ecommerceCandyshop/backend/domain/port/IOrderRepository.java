package com.cg.ecommerceCandyshop.backend.domain.port;

import com.cg.ecommerceCandyshop.backend.domain.model.Order;

public interface IOrderRepository {
    Order save (Order order);
    Order findById (Integer id);
    Iterable<Order> findAll();
    Iterable<Order> findByUserId(Integer userId);
    void updateStateById(Integer id, String state);
}
