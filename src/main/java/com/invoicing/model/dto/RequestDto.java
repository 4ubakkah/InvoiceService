package com.invoicing.model.dto;

import javax.validation.constraints.NotNull;

public class RequestDto {

    @NotNull
    private Long customerId;

    public RequestDto() {}

    public RequestDto(Long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
