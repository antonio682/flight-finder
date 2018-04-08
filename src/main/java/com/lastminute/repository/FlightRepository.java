package com.lastminute.repository;

import com.lastminute.domain.FlightDomain;

import java.util.List;

public interface FlightRepository {

    List<FlightDomain> findAll();

    FlightDomain findFlightByCode(String code);
}
