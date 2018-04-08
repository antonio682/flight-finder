package com.lastminute.service;

import com.lastminute.dto.FlightResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface FlightFinderService {

    List<FlightResponseDTO> findFlightByDate(LocalDate departureDate, Integer passengerNumber, String origin, String destination);

}
