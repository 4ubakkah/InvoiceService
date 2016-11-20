package com.invoicing.controller.web;

import com.invoicing.model.dto.GetByAddressRequestDto;
import com.invoicing.model.dto.GetMonthlyRequestDto;
import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.dto.RequestDto;

import java.util.List;


public interface InvoiceController {

    /**
     * Returns invoices conforming to criterias.
     * @param @RequestDto dto, criteria for invoice search
     * @return List<@InvoiceDto>
     */
    List<InvoiceDto> getAll(RequestDto dto);

    /**
     * Returns invoices conforming to criterias using given address.
     * @param @GetByAddressRequestDto dto, criteria for invoice search
     * @return List<@InvoiceDto>
     */
    List<InvoiceDto> getByAddress(GetByAddressRequestDto dto);

    /**
     * Returns monthly housing.
     * @param @GetMonthlyRequestDto dto, criteria for invoice search
     * @return List<@InvoiceDto>
     */
    List<InvoiceDto> getMonthlyHousing(GetMonthlyRequestDto dto);

    /**
     * Returns monthly shopping invoices.
     * @param @GetMonthlyRequestDto dto, criteria for invoice search
     * @return List<@InvoiceDto>
     */
    List<InvoiceDto> getMonthlyShopping(GetMonthlyRequestDto dto);

    /**
     * Creates invoice.
     * @param @InvoiceDto dto, criteria to fill new invoice.
     */
    void create(InvoiceDto dto);

    /**
     * Generates monthly invoices.
     * @param @RequestDto dto, criteria to use during monthly invoice generation.
     */
    void generateMonthlyInvoices(RequestDto dto);
}
