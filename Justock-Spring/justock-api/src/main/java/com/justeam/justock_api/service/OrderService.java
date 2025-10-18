package com.justeam.justock_api.service;

import com.justeam.justock_api.model.Order;
import com.justeam.justock_api.repository.OrderRepository;
import com.justeam.justock_api.request.OrderCreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listAlloOrders() {
        return orderRepository.findAll();
    }

    public Order findoOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createorder(OrderCreateRequest dto) {
        Order order = new Order();
        order.setQuantidade(dto.getQuantidade());
        order.setPrecoUnitario(dto.getPrecoUnitario());
        order.setSubtotal(dto.getSubtotal());
        order.setIdItemMarketplace(dto.getIdItemMarketplace());
        order.setStatus(dto.getStatus());
        return orderRepository.save(order);
    }

    public boolean deleteorder(Long id) {
        Optional<Order> opt = orderRepository.findById(id);
        if (opt.isEmpty()) return false;

        orderRepository.delete(opt.get());
        return true;
    }
}
