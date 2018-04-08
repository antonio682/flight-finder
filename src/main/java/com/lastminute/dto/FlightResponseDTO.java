package com.lastminute.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightResponseDTO {

    private Integer passengersNumber;

    private String code;

    private String origin;

    private String destination;

    private Double pricePerPerson;

    private Double totalPrice;
}
