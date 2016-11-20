package com.invoicing.dao;


import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceDao  {

    /**
     * Returns invoices where type is @see com.invoicing.model.InvoiceType.HOUSING}
     */
    List<Invoice> getMonthlyHousing(long customerId, int month);

    /**
     * Returns invoices where type is @see com.invoicing.model.InvoiceType.SHOPPING
     */
    List<Invoice> getMonthlyShopping(long customerId, int month);

    List<Invoice> getAll(long customerId, long addressId);

    List<Invoice> getAll(long customerId);

    void create(InvoiceDto dto);

    /**
     * Generates monthly invoice per customer address using consumed services.
     * After invoice is generated consumed services for given customer are cleared.
     */
    void generateMonthlyInvoice(long customerId);

}
