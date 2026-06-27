package com.panna.controller;

import com.panna.dto.OrderRequest;
import com.panna.entity.Order;
import com.panna.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.panna.service.InvoiceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;
    private final InvoiceService invoiceService;

    // =========================
    // PLACE ORDER
    // =========================
    @PostMapping("/place")
    public Order placeOrder(

            Authentication authentication,

            @RequestBody OrderRequest request
    ) {

        String email = authentication.getName();

        return orderService.placeOrder(
                email,
                request
        );
    }

    // =========================
    // MY ORDERS
    // =========================
    @GetMapping("/my-orders")
    public List<Order> getMyOrders(

            Authentication authentication
    ) {

        String email = authentication.getName();

        return orderService.getMyOrders(email);
    }

    // =========================
    // ADMIN ALL ORDERS
    // =========================
    @GetMapping("/all")
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    // =========================
    // UPDATE STATUS
    // =========================
    @PutMapping("/status/{id}")
    public Order updateOrderStatus(

            @PathVariable Long id,

            @RequestParam String status
    ) {

        return orderService.updateOrderStatus(
                id,
                status
        );
    }

    // =========================
    // DOWNLOAD INVOICE
    // =========================
    @GetMapping("/invoice/{id}")
    public ResponseEntity<byte[]> downloadInvoice(
            @PathVariable Long id) {

        byte[] pdf = invoiceService.generateInvoice(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=invoice-" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
    // =========================
    // CANCEL ORDER
    // =========================
    @PutMapping("/cancel/{id}")
    public Order cancelOrder(@PathVariable Long id) {

        return orderService.cancelOrder(id);
    }
}