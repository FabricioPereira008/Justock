package com.justeam.justock_api.service;

import com.justeam.justock_api.model.order;
import com.justeam.justock_api.model.order;
import com.justeam.justock_api.repository.orderRepository;
import com.justeam.justock_api.repository.orderRepository;
import com.justeam.justock_api.request.orderCreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class orderService {

    @Autowired
    private orderRepository orderRepository;

    public List<order> listAlloOrders() {
        return orderRepository.findAll();
    }

    public order findoOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public order createorder(orderCreateRequest dto) {
        order order = new order();
        order.setQuantidade(dto.getQuantidade());
        order.setPrecoUnitario(dto.getPrecoUnitario());
        order.setSubtotal(dto.getSubtotal());
        order.setIdItemMarketplace(dto.getIdItemMarketplace());
        order.setStatus(dto.getStatus());
        return orderRepository.save(order);
    }

    public boolean deleteorder(Long id) {
        Optional<order> opt = orderRepository.findById(id);
        if (opt.isEmpty()) return false;

        orderRepository.delete(opt.get());
        return true;
    }
}
