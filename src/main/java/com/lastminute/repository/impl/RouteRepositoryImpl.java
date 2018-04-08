package com.lastminute.repository.impl;

import com.lastminute.CsvFiles;
import com.lastminute.domain.RouteDomain;
import com.lastminute.repository.RepositoryBase;
import com.lastminute.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;

public class RouteRepositoryImpl  extends RepositoryBase implements RouteRepository {


    @Override
    public List<RouteDomain> findAll() {
        List<RouteDomain> response = new ArrayList<>();

        CsvFiles.readAllRecords(fullPathTo("flight-routes.csv")).
                forEach(route -> response.add(RouteDomain.builder()
                        .origin(route.get(0))
                        .destination(route.get(1))
                        .code(route.get(2))
                        .build())
                );

        return response;
    }

    @Override
    public RouteDomain findByCode(String code) {
        return findAll().stream()
                .filter(flight -> flight.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    public RouteDomain findByOrigin(String origin) {
        return findAll().stream()
                .filter(flight -> flight.getOrigin().equalsIgnoreCase(origin))
                .findFirst()
                .orElse(null);
    }

    @Override
    public RouteDomain findByDestination(String destination) {
        return findAll().stream()
                .filter(flight -> flight.getDestination().equalsIgnoreCase(destination))
                .findFirst()
                .orElse(null);
    }
}
