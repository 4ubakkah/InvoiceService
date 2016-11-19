package com.invoicing.controller.web;

import com.invoicing.model.Invoice;
import com.invoicing.model.dto.GetByAddressRequestDto;
import com.invoicing.model.dto.GetMonthlyRequestDto;
import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.dto.RequestDto;
import com.invoicing.model.mapper.InvoiceMapper;
import com.invoicing.service.InvoicingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/rest/invoicing/")
public class InvoiceController {

    @Autowired
    private InvoicingService service;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @RequestMapping(value = "/getAll", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<InvoiceDto> getAll(@Valid @RequestBody RequestDto dto) {
        List<Invoice> invoices = service.getAll(dto.getCustomerId());
        return invoiceMapper.mapEntityToDto(invoices);
    }

    @RequestMapping(value = "/getByAddress", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<InvoiceDto> getByAddress(@Valid @RequestBody GetByAddressRequestDto dto) {
        List<Invoice> invoices = service.getAll(dto.getCustomerId(), dto.getAddressId());
        return invoiceMapper.mapEntityToDto(invoices);
    }

    @RequestMapping(value = "/getMonthlyHousing", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<InvoiceDto> getMonthlyHousing(@Valid @RequestBody GetMonthlyRequestDto dto) {
        List<Invoice> invoices = service.getMonthlyHousing(dto.getCustomerId(), dto.getMonth());
        return invoiceMapper.mapEntityToDto(invoices);
    }

    @RequestMapping(value = "/getMonthlyShopping", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<InvoiceDto> getMonthlyShopping(@Valid @RequestBody GetMonthlyRequestDto dto) {
        List<Invoice> invoices = service.getMonthlyShopping(dto.getCustomerId(), dto.getMonth());
        return invoiceMapper.mapEntityToDto(invoices);
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public void create(@Valid @RequestBody InvoiceDto dto) {
        service.create(dto);
    }

    @RequestMapping(value = "/generate", method= RequestMethod.POST)
    public void generate(@Valid @RequestBody RequestDto dto) {
        service.generateInvoice(dto.getCustomerId());
    }
}
