package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/vat")
public class FakeUaeVatApiController {

    private static final double VAT_RATE = 0.05; // 5%

    // ✅ Calculate VAT
    @PostMapping("/calculate")
    public VatResponse calculateVat(@RequestBody VatRequest request) {
        double vat = request.getAmount() * VAT_RATE;
        double total = request.getAmount() + vat;

        return new VatResponse(request.getAmount(), vat, total, VAT_RATE);
    }
    @PostMapping("/validate-trn")
    public TrnValidationResponse validateTrn(@RequestBody TrnValidationRequest request) {

        boolean isValid = request.getTrn().startsWith("1000") && request.getTrn().length()==15;

        return new TrnValidationResponse(request.getTrn(),isValid,
                isValid ? "Valid TRN registered with UAE FTA" : "Invalid TRN");

    }
    // ✅ Validate TRN
    /*@PostMapping("/validate-trn")
    public TrnValidationResponse validateTrn(@RequestBody TrnValidationRequest request) {
        boolean isValid = request.getTrn().startsWith("1000") && request.getTrn().length() == 15;

        return new TrnValidationResponse(request.getTrn(), isValid,
                isValid ? "Valid TRN registered with UAE FTA" : "Invalid TRN");
    }*/

    // ✅ Generate Invoice
    @PostMapping("/invoice")
    public InvoiceResponse generateInvoice(@RequestBody InvoiceRequest request) {
        double vat = request.getSubtotal() * VAT_RATE;
        double total = request.getSubtotal() + vat;

        return new InvoiceResponse(
                "INV-" + System.currentTimeMillis(),
                request.getCustomerName(),
                request.getSubtotal(),
                vat,
                total,
                "100000978500003", // fake TRN
                "Delivery Hero Talabat DB L.L.C",
                LocalDateTime.now().toString()
        );
    }
}
