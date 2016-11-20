package com.invoicing.service.impl;

import com.invoicing.dao.InvoiceDao;
import com.invoicing.model.dto.InvoiceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InvoicingServiceImplTest {

    @InjectMocks
    @Spy
    private InvoicingServiceImpl service;

    @Mock
    private InvoiceDao invoiceDao;

    @Test
    public void shouldDelegateGetMonthlyHousingCall() throws Exception {
        long customerId = 1L;
        int month = 1;
        service.getMonthlyHousing(customerId, month);

        verify(invoiceDao).getMonthlyHousing(customerId, month);
    }

    @Test
    public void shouldDelegateGetMonthlyShoppingCall() throws Exception {
        long customerId = 1L;
        int month = 1;
        service.getMonthlyShopping(customerId, month);

        verify(invoiceDao).getMonthlyShopping(customerId, month);
    }

    @Test
    public void shouldDelegateGetAllByCustomerCall() throws Exception {
        long customerId = 1L;

        service.getAll(customerId);

        verify(invoiceDao).getAll(customerId);
    }

    @Test
    public void shouldDelegateGetAllByCustomerAndAddressCall() throws Exception {
        long customerId = 1L;
        long addressId = 1L;

        service.getAll(customerId, addressId);

        verify(invoiceDao).getAll(customerId, addressId);
    }

    @Test
    public void shouldDelegateCreateCall() throws Exception {
        InvoiceDto invoiceDto = new InvoiceDto();
        service.create(invoiceDto);

        verify(invoiceDao).create(invoiceDto);
    }

    @Test
    public void shouldDelegateGenerateInvoiceCall() throws Exception {
        long customerId = 1L;

        service.generateInvoice(customerId);

        verify(invoiceDao).generateMonthlyInvoice(customerId);
    }

}