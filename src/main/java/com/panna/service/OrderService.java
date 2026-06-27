package com.panna.service;

import com.panna.dto.OrderRequest;
import com.panna.entity.Order;
import com.panna.entity.Product;
import com.panna.repository.OrderRepository;
import com.panna.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.panna.entity.Address;
import com.panna.repository.AddressRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final AddressRepository addressRepository;

    // =========================
    // PLACE ORDER
    // =========================
    public Order placeOrder(
            String userEmail,
            OrderRequest request
    ) {

        Product product = productRepository.findById(
                request.getProductId()
        ).orElseThrow(() ->
                new RuntimeException("Product not found")
        );

        Order order = new Order();
        order.setUserEmail(userEmail);
        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setPrice(product.getPrice());
        order.setQuantity(request.getQuantity());
        order.setImageUrl(product.getImageUrl());

        // 👇 YAHAN ADD KARNA HAI
        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        order.setFullName(address.getFullName());
        order.setMobile(address.getPhone());
        order.setAddressLine(address.getAddressLine1());
        order.setCity(address.getCity());
        order.setState(address.getState());
        order.setPincode(address.getPincode());

        return orderRepository.save(order);
    }

    // =========================
    // USER ORDER HISTORY
    // =========================
    public List<Order> getMyOrders(
            String userEmail
    ) {

        return orderRepository.findByUserEmail(userEmail);
    }

    // =========================
    // ADMIN GET ALL ORDERS
    // =========================
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    // =========================
    // UPDATE ORDER STATUS
    // =========================
    public Order updateOrderStatus(
            Long orderId,
            String status
    ) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }
    // =========================
    // CANCEL ORDER
    // =========================
    public Order cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        // Sirf PENDING order cancel ho sakta hai
        if (!order.getStatus().equals("PENDING")) {
            throw new RuntimeException("Only PENDING orders can be cancelled.");
        }

        order.setStatus("CANCELLED");

        return orderRepository.save(order);
    }
}