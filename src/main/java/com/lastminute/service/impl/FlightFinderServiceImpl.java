package com.lastminute.service.impl;

import com.lastminute.domain.FlightDomain;
import com.lastminute.domain.RouteDomain;
import com.lastminute.dto.FlightResponseDTO;
import com.lastminute.repository.FlightRepository;
import com.lastminute.repository.RouteRepository;
import com.lastminute.service.FlightFinderService;
import com.lastminute.utils.BeansFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class FlightFinderServiceImpl implements FlightFinderService {

    private static final Double MORE_THAN_THIRTY_DAYS_INTEREST = 80.0;
    private static final Double BETWEEN_THIRTY_AND_SIXTEEN_DAYS_INTEREST = 100.0;
    private static final Double BETWEEN_FIFTEEN_AND_THREE_DAYS_INTEREST = 120.0;
    private static final Double LESS_THAN_THREE_DAYS_INTEREST = 150.0;

    private FlightRepository flightRepository;

    private RouteRepository routeRepository;

    public FlightFinderServiceImpl() {

        BeansFactory beansFactory = new BeansFactory();
        this.flightRepository = beansFactory.createFlightRepository();
        this.routeRepository = beansFactory.createRouteRepository();

        checkNotNull(routeRepository, "routeRepository must not be null");
        checkNotNull(flightRepository, "flightRepository must not be null");
    }


    @Override
    public List<FlightResponseDTO> findFlightByDate(LocalDate departureDate, Integer passengerNumber, String origin,
                                              String destination) {

        List<FlightResponseDTO> flightsResponse = new ArrayList<>();

        List<RouteDomain> expectedRoutes = routeRepository.findByOrigin(origin)
                .stream()
                .filter(route -> route.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());

        if(!expectedRoutes.isEmpty()) {

            expectedRoutes.forEach(route -> {

                FlightDomain expectedFlight = flightRepository.findFlightByCode(route.getCode());

                Double finalPricePerPerson = obtainFinalPrice( departureDate,expectedFlight);

                flightsResponse.add(FlightResponseDTO.builder()
                         .code(expectedFlight.getCode())
                         .origin(route.getOrigin())
                         .destination(route.getDestination())
                         .passengersNumber(passengerNumber)
                         .pricePerPerson(finalPricePerPerson)
                         .totalPrice((double) (finalPricePerPerson * passengerNumber))
                         .build());
            });

        }

        return flightsResponse;
    }


    private Double obtainFinalPrice(LocalDate flightDate, FlightDomain flight) {

        LocalDate today = LocalDate.now();

        Double finalPrice = 0.0;

        if (areValidDates(flightDate, today)) {
            Integer daysBeetweenTodayAndDepartureDate = daysPassedBetweenDates(today, flightDate);

            if (daysBeetweenTodayAndDepartureDate > 30) {

                finalPrice = Integer.parseInt(flight.getBasePrice()) * MORE_THAN_THIRTY_DAYS_INTEREST;

            } else if (daysBeetweenTodayAndDepartureDate <= 30 && daysBeetweenTodayAndDepartureDate >= 16) {

                finalPrice = Integer.parseInt(flight.getBasePrice()) * BETWEEN_THIRTY_AND_SIXTEEN_DAYS_INTEREST;

            } else if (daysBeetweenTodayAndDepartureDate <= 15 && daysBeetweenTodayAndDepartureDate >= 3) {

                finalPrice = Integer.parseInt(flight.getBasePrice()) * BETWEEN_FIFTEEN_AND_THREE_DAYS_INTEREST;

            } else {

                finalPrice = Integer.parseInt(flight.getBasePrice()) * LESS_THAN_THREE_DAYS_INTEREST;

            }
        }

        return finalPrice / 100.0;
    }

    private Integer daysPassedBetweenDates(LocalDate dateBefore, LocalDate endDate) {
       return Math.toIntExact(ChronoUnit.DAYS.between(dateBefore, endDate));
    }

    private boolean areValidDates(LocalDate flightDate, LocalDate today) {
        return flightDate.isAfter(today) || flightDate.atStartOfDay().equals(today.atStartOfDay());
    }
}
