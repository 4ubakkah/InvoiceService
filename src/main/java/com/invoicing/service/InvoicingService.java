package com.invoicing.service;

import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.Invoice;

import java.util.List;

public interface InvoicingService {

    List<Invoice> getMonthlyHousing(long customerId, int month);

    List<Invoice> getMonthlyShopping(long customerId, int month);

    List<Invoice> getAll(long customerId, long addressId);

    List<Invoice> getAll(long customerId);

    void create(InvoiceDto dto);

    void generateInvoice(long customerId);
}
