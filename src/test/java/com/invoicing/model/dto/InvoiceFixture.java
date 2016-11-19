package com.invoicing.model.dto;


import com.invoicing.model.InvoiceType;

import java.math.BigDecimal;
import java.util.Calendar;

public final class InvoiceFixture {

    private InvoiceFixture() {}

    public static InvoiceDto anInvoice() {
        InvoiceDto invoice = new InvoiceDto();

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, 1);

        invoice.setAmount(BigDecimal.TEN);
        invoice.setTotalAmount(BigDecimal.TEN);
        invoice.setVatAmount(BigDecimal.ONE);
        invoice.setAddressId(1L);
        invoice.setCustomerId(1L);
        invoice.setEndDate(calendar.getTime());
        invoice.setInvoiceDate(calendar.getTime());
        invoice.setStartDate(calendar.getTime());
        invoice.setPaymentDueDate(calendar.getTime());
        invoice.setInvoiceType(InvoiceType.SHOPPING);
        invoice.setInvoiceNumber("123INV");

        return invoice;
    }
}
