package com.invoicing.dao;

import com.invoicing.BootStrapper;
import com.invoicing.model.Invoice;
import com.invoicing.model.InvoiceType;
import com.invoicing.model.dto.InvoiceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootStrapper.class)
@WebAppConfiguration
public class InvoiceDaoITest {

    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    public void shouldFindAllInvoicesPerCustomer() {
        long customerId = 2;

        List<Invoice> invoices = invoiceDao.getAll(customerId);

        assertThat(invoices).isNotNull().hasSize(2);
        assertThat(invoices).extracting(i -> i.getCustomerId()).containsOnly(customerId);
    }

    @Test
    public void shouldFindMonthlyShoppingInvoices() {
        long customerId = 1;
        int month = 11;

        List<Invoice> invoices = invoiceDao.getMonthlyShopping(customerId, month);

        assertThat(invoices).isNotNull().hasSize(1);
        assertThat(invoices).extracting(i -> i.getCustomerId()).containsOnly(customerId);
        assertThat(invoices).extracting(i -> extractMonthFromDate(i.getInvoiceDate())).containsOnly(month);
    }

    @Test
    public void shouldFindMonthlyHousingInvoices() {
        long customerId = 2;
        int month = 11;

        List<Invoice> invoices = invoiceDao.getMonthlyHousing(customerId, month);

        assertThat(invoices).isNotNull().hasSize(2);
        assertThat(invoices).extracting(i -> i.getCustomerId()).containsOnly(customerId);
        assertThat(invoices).extracting(i -> extractMonthFromDate(i.getInvoiceDate())).containsOnly(month);
    }

    @Test
    public void shouldFindInvoicesByCustomerAndAddress() {
        long customerId = 2;
        long addressId = 2;

        List<Invoice> invoices = invoiceDao.getAll(customerId, addressId);

        assertThat(invoices).isNotNull().hasSize(2);
        assertThat(invoices).extracting(i -> i.getAddressId()).containsOnly(addressId);
    }

    @Test
    public void shouldCreateInvoice() {
        long customerId = 3;
        InvoiceDto dto = new InvoiceDto();
        dto.setCustomerId(customerId);
        dto.setInvoiceType(InvoiceType.SHOPPING);
        dto.setInvoiceDate(new Date());
        dto.setAddressId(1L);
        dto.setVatAmount(BigDecimal.TEN);
        dto.setTotalAmount(BigDecimal.TEN);
        dto.setAmount(BigDecimal.TEN);

        invoiceDao.create(dto);

        List<Invoice> persistedInvoices = invoiceDao.getAll(customerId);

        assertThat(persistedInvoices).isNotNull().hasSize(1);
    }

    @Test
    public void shouldGenerateInvoices() {
        long customerId = 3;
        long addressId = 3;

        assertThat(invoiceDao.getAll(customerId, addressId).size()).isEqualTo(0);

        invoiceDao.generateInvoice(customerId);

        List<Invoice> existingInvoices = invoiceDao.getAll(customerId, addressId);

        assertThat(existingInvoices.size()).isEqualTo(1);
        assertThat(existingInvoices.get(0).getCustomerId()).isEqualTo(customerId);
        assertThat(existingInvoices.get(0).getAddressId()).isEqualTo(addressId);
    }

    private int extractMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }
}