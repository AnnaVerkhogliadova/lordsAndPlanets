package com.home.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddLordRequest {
    @NotNull
    @Positive(message = "Age should be more than zero")
    private Integer lordId;

    public AddLordRequest(){}

    public AddLordRequest(Integer lordId) {
        this.lordId = lordId;
    }

    public Integer getLordId() {
        return lordId;
    }
}
