package com.invoicing.dao;


import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

/*@Transactional*/
@Service
public interface InvoiceDao  {

    List<Invoice> getMonthlyHousing(long customerId, int month);

    List<Invoice> getMonthlyShopping(long customerId, int month);

    List<Invoice> getAll(long customerId, long addressId);

    List<Invoice> getAll(long customerId);

    void create(InvoiceDto dto);

    void generateInvoice(long customerId);

}
