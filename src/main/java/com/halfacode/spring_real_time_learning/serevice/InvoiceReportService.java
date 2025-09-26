package com.halfacode.spring_real_time_learning.serevice;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Service
public class InvoiceReportService {

    /*public byte[] generateInvoiceReport(Map<String, Object> invoiceData) throws Exception {
        // Try to load precompiled .jasper first
        InputStream jasperStream = getClass().getResourceAsStream("/report/InvoiceReport.jasper");

        JasperReport jasperReport;

        if (jasperStream != null) {
            // Load compiled report
            jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        } else {
            // Fallback: compile .jrxml at runtime
            InputStream jrxmlStream = getClass().getResourceAsStream("/report/InvoiceReport.jrxml");
            if (jrxmlStream == null) {
                throw new IllegalStateException("InvoiceReport.jrxml not found in resources!");
            }
            jasperReport = JasperCompileManager.compileReport(jrxmlStream);
        }

        // Fill report with parameters and empty datasource
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, invoiceData, new JREmptyDataSource());

        // Export to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }*/
    public byte[] generateInvoiceReport(Map<String, Object> invoiceData) throws Exception {
        try (InputStream jasperStream = getClass().getResourceAsStream("/InvoiceReport.jasper")) {
            JasperReport jasperReport;

            if (jasperStream != null) {
                jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            } else {
                try (InputStream jrxmlStream = getClass().getResourceAsStream("/InvoiceReport.jrxml")) {
                    if (jrxmlStream == null) {
                        throw new IllegalStateException("InvoiceReport.jrxml not found in resources!");
                    }
                    jasperReport = JasperCompileManager.compileReport(jrxmlStream);
                }
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, invoiceData, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }
}

   /* public byte[] generateInvoiceReport(Map<String, Object> invoiceData) throws Exception {
        File file = ResourceUtils.getFile("classpath:report/InvoiceReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass JSON data directly as parameters
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, invoiceData, new JREmptyDataSource());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }*/
   /* public byte[] generateInvoiceReport(Map<String, Object> invoiceData) throws Exception {
        // Load the JRXML file from resources
        InputStream jrxmlStream = getClass().getResourceAsStream("/report/InvoiceReport.jrxml");
        if (jrxmlStream == null) {
            throw new IllegalStateException("InvoiceReport.jrxml not found in resources!");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

        // Fill the report with parameters and an empty data source
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, invoiceData, new JREmptyDataSource());

        // Export the report to PDF format
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }*/

