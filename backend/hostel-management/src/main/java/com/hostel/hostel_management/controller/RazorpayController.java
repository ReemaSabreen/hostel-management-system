package com.hostel.hostel_management.controller;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.hostel_management.entity.Fee;
import com.hostel.hostel_management.repository.FeeRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class RazorpayController {

    @Value("${RAZORPAY_KEY_ID}")
    private String keyId;

    @Value("${RAZORPAY_KEY_SECRET}")
    private String keySecret;

    private final FeeRepository feeRepository;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestParam Long feeId) throws Exception {

        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee not found"));

        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();
        options.put("amount", fee.getAmount().multiply(new BigDecimal(100))); // paise
        options.put("currency", "INR");
        options.put("receipt", "fee_" + feeId);

        Order order = client.orders.create(options);

        return ResponseEntity.ok(order.toJson().toString());
    }
}
