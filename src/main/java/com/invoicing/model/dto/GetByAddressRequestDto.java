package com.invoicing.model.dto;

import javax.validation.constraints.NotNull;

public class GetByAddressRequestDto extends RequestDto {

    @NotNull
    private Long addressId;

    public GetByAddressRequestDto() {}

    public GetByAddressRequestDto(Long customerId, Long addressId) {
        super(customerId);
        this.addressId = addressId;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

}
