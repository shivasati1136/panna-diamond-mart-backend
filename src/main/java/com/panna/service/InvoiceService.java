package com.panna.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.panna.entity.Order;
import com.panna.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final OrderRepository orderRepository;

    public byte[] generateInvoice(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        try {

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            Document document =
                    new Document();

            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("PANNA STORE"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph("---------------------------"));

            document.add(new Paragraph("Order ID : " + order.getId()));
            document.add(new Paragraph("Customer : " + order.getFullName()));
            document.add(new Paragraph("Phone : " + order.getMobile()));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Product : " + order.getProductName()));
            document.add(new Paragraph("Price : ₹" + order.getPrice()));
            document.add(new Paragraph("Quantity : " + order.getQuantity()));

            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                    "Total : ₹" +
                            (order.getPrice() * order.getQuantity())
            ));

            document.close();

            return out.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}