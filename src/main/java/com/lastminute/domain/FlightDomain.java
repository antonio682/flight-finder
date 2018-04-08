package com.lastminute.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightDomain {

    private String code;

    private String basePrice;
}
