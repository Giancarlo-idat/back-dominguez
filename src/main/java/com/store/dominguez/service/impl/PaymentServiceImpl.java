package com.store.dominguez.service.impl;

import com.store.dominguez.http.PaymentIntentDTO;
import com.store.dominguez.response.PaymentResponse;
import com.store.dominguez.service.gestion.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.key.secret}")
    String apiKeyPublic;

    @Override
    public PaymentResponse createPaymentLink(PaymentIntentDTO paymentIntentDTO) throws StripeException {

        Stripe.apiKey = apiKeyPublic;
        BigDecimal total = paymentIntentDTO.getPrecio_total();
        System.out.println("Total " + total);
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3001/payment/success")
                .setCancelUrl("http://localhost:3001/payment/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("PEN")
                                .setUnitAmount(total.multiply(BigDecimal.valueOf(100)).longValue())
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Venta de productos")
                                        .build())
                                .build())
                        .build())
                .build();
        Session session = Session.create(params);

        PaymentResponse res = new PaymentResponse();
        res.setPayment_url(session.getUrl());
        return res;
    }
}
