package com.lastminute.utils;

import com.lastminute.repository.FlightRepository;
import com.lastminute.repository.RouteRepository;
import com.lastminute.repository.impl.FlightRepositoryImpl;
import com.lastminute.repository.impl.RouteRepositoryImpl;
import com.lastminute.service.FlightFinderService;
import com.lastminute.service.impl.FlightFinderServiceImpl;

public class BeansFactory {

    public FlightRepository createFlightRepository() {
        return new FlightRepositoryImpl();
    }

    public RouteRepository createRouteRepository() {
        return new RouteRepositoryImpl();
    }

    public FlightFinderService createFlightFinderService() {
        return new FlightFinderServiceImpl();
    }
}
