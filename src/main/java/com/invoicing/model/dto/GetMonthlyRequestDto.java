package com.invoicing.model.dto;

import javax.validation.constraints.NotNull;

public class GetMonthlyRequestDto extends RequestDto {

    @NotNull
    private Integer month;

    public GetMonthlyRequestDto(){}

    public GetMonthlyRequestDto(Long customerId, Integer month) {
        super(customerId);
        this.month = month;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
