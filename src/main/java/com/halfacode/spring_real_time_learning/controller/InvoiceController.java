package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.serevice.InvoiceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceReportService invoiceReportService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateInvoice(@RequestBody Map<String, Object> request) throws Exception {
        byte[] pdfBytes = invoiceReportService.generateInvoiceReport(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
