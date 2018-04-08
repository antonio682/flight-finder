package com.lastminute.repository.impl;

import com.lastminute.domain.FlightDomain;
import com.lastminute.repository.FlightRepository;
import com.lastminute.utils.BeansFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class FlightRepositoryImplTest {

    private FlightRepository flightRepository;

    @Before
    public void init() {
       this.flightRepository = new BeansFactory().createFlightRepository();
       checkNotNull(flightRepository, "flightRepository must not be null");
    }

    @Test
    public void should_return_all_flights() {

        // WHEN
        List<FlightDomain> responseFlights = flightRepository.findAll();
        // THEN
        Assert.assertTrue("List must not be empty", !responseFlights.isEmpty());

        responseFlights.forEach(flight -> {

            Assert.assertTrue("Flight code must be String type",
                    flight.getCode() instanceof String);

            Assert.assertTrue("Base Price must be an Integer after be casted from String",
                    ((Integer) Integer.parseInt(flight.getBasePrice())) instanceof Integer);

        });
    }

    @Test
    public void should_return_flight() {

        // GIVEN
        final String expectedFlightCode = "TK4927";

        // WHEN
        FlightDomain responseFlight =  flightRepository.findFlightByCode(expectedFlightCode);

        // THEN
        Assert.assertTrue("Wrong flight code", responseFlight.getCode().equalsIgnoreCase(expectedFlightCode));
        Assert.assertTrue("Wrong expected price" ,Integer.parseInt(responseFlight.getBasePrice()) == 290);
    }

    @Test
    public void should_return_null_find_flight_with_non_existing_code() {

        // GIVEN
        final String expectedFlightCode = "Non_valid_code";

        // WHEN
        FlightDomain responseFlight =  flightRepository.findFlightByCode(expectedFlightCode);

        //THEN
        Assert.assertTrue("Response must be null", responseFlight == null);
    }
}