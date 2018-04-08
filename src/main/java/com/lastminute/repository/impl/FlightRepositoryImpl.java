package com.lastminute.repository.impl;


import com.lastminute.CsvFiles;
import com.lastminute.domain.FlightDomain;
import com.lastminute.repository.FlightRepository;
import com.lastminute.repository.RepositoryBase;

import java.util.ArrayList;
import java.util.List;


public class FlightRepositoryImpl extends RepositoryBase implements FlightRepository {


    @Override
    public List<FlightDomain> findAll() {

        List<FlightDomain> flightsResponse = new ArrayList<>();

        CsvFiles.readAllRecords(fullPathTo("flight-prices.csv")).forEach(flight ->
               flightsResponse.add(FlightDomain.builder()
                                            .code(flight.get(0))
                                            .basePrice(flight.get(1))
                                            .build()
               )
        );

        return flightsResponse;
    }

    @Override
    public FlightDomain findFlightByCode(String code) {

     return findAll().stream()
                .filter(flight -> flight.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }
}
