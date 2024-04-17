package com.store.dominguez.service.gestion;

import com.store.dominguez.http.PaymentIntentDTO;
import com.store.dominguez.response.PaymentResponse;
import com.stripe.exception.StripeException;


public interface PaymentService {

    PaymentResponse createPaymentLink(PaymentIntentDTO paymentIntentDTO) throws StripeException;

}
