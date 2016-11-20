package com.invoicing.model.mapper;

import com.invoicing.model.Invoice;
import com.invoicing.model.dto.InvoiceDto;

import java.util.List;

public interface InvoiceMapper {

    Invoice mapDtoToEntity(InvoiceDto dto);

    InvoiceDto mapEntityToDto(Invoice entity);

    List<InvoiceDto> mapEntityToDto(List<Invoice> entities);

}
