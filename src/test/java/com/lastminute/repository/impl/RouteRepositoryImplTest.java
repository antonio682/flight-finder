package com.lastminute.repository.impl;

import com.lastminute.domain.RouteDomain;
import com.lastminute.repository.RouteRepository;
import com.lastminute.utils.BeansFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class RouteRepositoryImplTest {

    private final static String EXPECTED_ROUTE_CODE = "VY4335";
    private final static String EXPECTED_ORIGIN = "FCO";
    private final static String EXPECTED_DESTINATION = "CDG";

    private RouteRepository routeRepository;

    @Before
    public void init() {
        this.routeRepository = new BeansFactory().createRouteRepository();
        checkNotNull(routeRepository, "routeRepository must not be null");
    }

    @Test
    public void should_return_all_routes() {

        // WHEN
        List<RouteDomain> responseRoutes = routeRepository.findAll();
        // THEN
        assertTrue("Response must contain elements", !responseRoutes.isEmpty());
    }

    @Test
    public void should_return_route_by_code() {

        // WHEN
        RouteDomain responseRoute = routeRepository.findByCode(EXPECTED_ROUTE_CODE);

        // THEN
        assertTrue("Wrong Code", responseRoute.getCode().equalsIgnoreCase(EXPECTED_ROUTE_CODE));
        assertTrue("Wrong Origin", responseRoute.getOrigin().equalsIgnoreCase(EXPECTED_ORIGIN));
        assertTrue("Wrong Destination", responseRoute.getDestination().equalsIgnoreCase(EXPECTED_DESTINATION));
    }

    @Test
    public void should_return_route_by_origin() {

        // WHEN
        List<RouteDomain> responseRoute = routeRepository.findByOrigin(EXPECTED_ORIGIN);
        // THEN
        checkExpectedResults(EXPECTED_ROUTE_CODE, EXPECTED_ORIGIN, EXPECTED_DESTINATION, responseRoute);
    }

    @Test
    public void should_return_route_by_destination() {

        // WHEN
        List<RouteDomain> responseRoute = routeRepository.findByDestination(EXPECTED_DESTINATION);
        // THEN
        checkExpectedResults(EXPECTED_ROUTE_CODE, EXPECTED_ORIGIN, EXPECTED_DESTINATION, responseRoute);
    }

    private void checkExpectedResults(String expectedRouteCode, String expectedOrigin, String expectedDestination,
                                      List<RouteDomain>  responseRoutes) {

        RouteDomain responseRoute = responseRoutes.stream()
                .findFirst()
                .orElse( RouteDomain.builder()
                        .build()
                );

        assertTrue("Wrong Code", responseRoute.getCode().equalsIgnoreCase(expectedRouteCode));
        assertTrue("Wrong Origin", responseRoute.getOrigin().equalsIgnoreCase(expectedOrigin));
        assertTrue("Wrong Destination", responseRoute.getDestination().equalsIgnoreCase(expectedDestination));
    }
}