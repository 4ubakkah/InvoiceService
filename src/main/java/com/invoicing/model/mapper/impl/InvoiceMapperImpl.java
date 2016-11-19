package com.invoicing.model.mapper.impl;

import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.Invoice;
import com.invoicing.model.mapper.InvoiceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public Invoice mapDtoToEntity(InvoiceDto dto) {
        Invoice invoice = new Invoice();

        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setVatAmount(dto.getVatAmount());
        invoice.setAmount(dto.getAmount());
        invoice.setPeriodDescription(dto.getPeriodDescription());
        invoice.setAddressId(dto.getAddressId());
        invoice.setCustomerId(dto.getCustomerId());
        invoice.setEndDate(dto.getEndDate());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setInvoiceType(dto.getInvoiceType());
        invoice.setPaymentDueDate(dto.getPaymentDueDate());
        invoice.setStartDate(dto.getStartDate());

        return invoice;
    }

    @Override
    public InvoiceDto mapEntityToDto(Invoice entity) {
        InvoiceDto dto = new InvoiceDto();

        dto.setTotalAmount(entity.getTotalAmount());
        dto.setVatAmount(entity.getVatAmount());
        dto.setAmount(entity.getAmount());
        dto.setPeriodDescription(entity.getPeriodDescription());
        dto.setAddressId(entity.getAddressId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setEndDate(entity.getEndDate());
        dto.setInvoiceDate(entity.getInvoiceDate());
        dto.setInvoiceType(entity.getInvoiceType());
        dto.setPaymentDueDate(entity.getPaymentDueDate());
        dto.setStartDate(entity.getStartDate());

        return dto;
    }

    @Override
    public List<Invoice> mapDtoToEntity(List<InvoiceDto> dtos) {
        List<Invoice> invoices = new ArrayList<>(dtos.size());
        dtos.stream().forEach(dto -> invoices.add(mapDtoToEntity(dto)));

        return invoices;
    }

    @Override
    public List<InvoiceDto> mapEntityToDto(List<Invoice> entities) {
        List<InvoiceDto> invoices = new ArrayList<>(entities.size());
        entities.stream().forEach(entity -> invoices.add(mapEntityToDto(entity)));

        return invoices;
    }

}