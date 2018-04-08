package com.lastminute.service.impl;

import com.lastminute.dto.FlightResponseDTO;
import com.lastminute.service.FlightFinderService;
import com.lastminute.utils.BeansFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class FlightFinderServiceImplTest {

    private FlightFinderService flightFinderService;

    @Before
    public void init() {
        this.flightFinderService = new BeansFactory().createFlightFinderService();
        checkNotNull(flightFinderService, "flightFinderService must not be null");
    }

    @Test
    public void should_return_two_three_flights_for_one_passenger() {

        //GIVEN
        final String origin = "AMS";
        final String destination = "FRA";

        final String firstFlightCode = "TK2372";
        final String secondFlightCode = "TK2659";
        final String thirdFlightCode = "LH5909";

        final Double firstExpectedPrice = 157.6;
        final Double secondExpectedPrice = 198.4;
        final Double thirdExpectedPrice = 90.4;


        // WHEN
        List<FlightResponseDTO> expectedResponseFlights = flightFinderService.findFlightByDate(LocalDate.now().plusDays(31),
                1, origin, destination);

        // THEN
        Assert.assertTrue("Wrong number of flights returned",expectedResponseFlights.size() == 3);

        Assert.assertTrue(expectedResponseFlights.get(0).getCode().equalsIgnoreCase(firstFlightCode));
        Assert.assertTrue(expectedResponseFlights.get(1).getCode().equalsIgnoreCase(secondFlightCode));
        Assert.assertTrue(expectedResponseFlights.get(2).getCode().equalsIgnoreCase(thirdFlightCode));

        Assert.assertTrue(expectedResponseFlights.get(0).getTotalPrice().equals(firstExpectedPrice));
        Assert.assertTrue(expectedResponseFlights.get(1).getTotalPrice().equals(secondExpectedPrice));
        Assert.assertTrue(expectedResponseFlights.get(2).getTotalPrice().equals(thirdExpectedPrice));
    }


    @Test
    public void should_return_two_flights_for_one_passenger() {

        // GIVEN
        final String origin = "LHR";
        final String destination = "IST";

        final String firstFlightCode = "TK8891";
        final String secondFlightCode = "LH1085";

        final Double firstFinalPrice = 900.0;
        final Double secondFinalPrice = 532.8;


        // WHEN
        List<FlightResponseDTO> expectedResponseFlights = flightFinderService.findFlightByDate(LocalDate.now().plusDays(15),
                3, origin, destination);

        // THEN
        Assert.assertTrue("Wrong number of flights returned",expectedResponseFlights.size() == 2);

        Assert.assertTrue(expectedResponseFlights.get(0).getCode().equalsIgnoreCase(firstFlightCode));
        Assert.assertTrue(expectedResponseFlights.get(1).getCode().equalsIgnoreCase(secondFlightCode));

        Assert.assertTrue(expectedResponseFlights.get(0).getTotalPrice().equals(firstFinalPrice));
        Assert.assertTrue(expectedResponseFlights.get(1).getTotalPrice().equals(secondFinalPrice));
    }


    @Test
    public void should_return_two_flights_for_two_passengers() {

        // GIVEN
        final String firstFlightCode = "IB2171";
        final String secondFlightCode = "LH5496";

        final Double firstFinalPrice = 777.0;
        final Double secondFinalPrice = 879.0;

        final String origin = "BCN";
        final String destination = "MAD";


        // WHEN
        List<FlightResponseDTO> expectedResponseFlights = flightFinderService.findFlightByDate(LocalDate.now().plusDays(2),
                2, origin, destination);

        // THEN
        Assert.assertTrue("Wrong number of flights returned",expectedResponseFlights.size() == 2);

        Assert.assertTrue(expectedResponseFlights.get(0).getCode().equalsIgnoreCase(firstFlightCode));
        Assert.assertTrue(expectedResponseFlights.get(1).getCode().equalsIgnoreCase(secondFlightCode));

        Assert.assertTrue(expectedResponseFlights.get(0).getTotalPrice().equals(firstFinalPrice));
        Assert.assertTrue(expectedResponseFlights.get(1).getTotalPrice().equals(secondFinalPrice));
    }


    @Test
    public void should_return_no_flights() {

        // GIVEN
        final String origin = "CDG";
        final String destination = "FRA";

        // WHEN
        List<FlightResponseDTO> expectedResponseFlights = flightFinderService.findFlightByDate(LocalDate.now().plusDays(2),
                2, origin, destination);

        // THEN
        Assert.assertTrue("No flights was expected", expectedResponseFlights.isEmpty());

    }
}