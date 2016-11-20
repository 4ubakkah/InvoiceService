package com.invoicing.service.impl;

import com.invoicing.dao.InvoiceDao;
import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.Invoice;
import com.invoicing.service.InvoicingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;


@Service
@EnableTransactionManagement
public class InvoicingServiceImpl implements InvoicingService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Override
    public List<Invoice> getMonthlyHousing(long customerId, int month) {
        return invoiceDao.getMonthlyHousing(customerId, month);
    }

    @Override
    public List<Invoice> getMonthlyShopping(long customerId, int month) {
        return invoiceDao.getMonthlyShopping(customerId, month);
    }

    @Override
    public List<Invoice> getAll(long customerId, long addressId) {
        return invoiceDao.getAll(customerId, addressId);
    }

    @Override
    public List<Invoice> getAll(long customerId) {
        return invoiceDao.getAll(customerId);
    }

    @Override
    public void create(InvoiceDto dto) {
        invoiceDao.create(dto);
    }

    @Override
    public void generateInvoice(long customerId) {
        invoiceDao.generateMonthlyInvoice(customerId);
    }
}
