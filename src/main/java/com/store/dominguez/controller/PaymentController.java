package com.store.dominguez.controller;

import com.store.dominguez.http.PaymentIntentDTO;
import com.store.dominguez.response.PaymentResponse;
import com.store.dominguez.service.gestion.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripe")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    /*@PreAuthorize("hasRole('Cliente') OR hasRole('Administrador')")*/
    public ResponseEntity<PaymentResponse> createPaymentLink(@RequestBody PaymentIntentDTO paymentIntentDTO) {
        try {
            PaymentResponse paymentResponse = paymentService.createPaymentLink(paymentIntentDTO);
            System.out.println(paymentResponse + " " + paymentIntentDTO);
            return ResponseEntity.ok(paymentResponse);
        } catch (StripeException e) {
            // Handle the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
