package com.lastminute.utils;

import com.lastminute.repository.FlightRepository;
import com.lastminute.repository.RouteRepository;
import com.lastminute.repository.impl.FlightRepositoryImpl;
import com.lastminute.repository.impl.RouteRepositoryImpl;

public class BeansFactory {

    public FlightRepository createFlightRepository() {
        return new FlightRepositoryImpl();
    }

    public RouteRepository createRouteRepository() {
        return new RouteRepositoryImpl();
    }
}
