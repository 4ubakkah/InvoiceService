package com.invoicing.model.mapper;

import com.invoicing.model.dto.InvoiceDto;
import com.invoicing.model.Invoice;

import java.util.List;

public interface InvoiceMapper {

    Invoice mapDtoToEntity(InvoiceDto dto);

    InvoiceDto mapEntityToDto(Invoice entity);

    List<Invoice> mapDtoToEntity(List<InvoiceDto> dtos);

    List<InvoiceDto> mapEntityToDto(List<Invoice> entities);

}
