package com.lastminute.repository.impl;

import com.lastminute.utils.CsvFiles;
import com.lastminute.domain.RouteDomain;
import com.lastminute.repository.RepositoryBase;
import com.lastminute.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<RouteDomain> findByOrigin(String origin) {

        return findAll().stream()
                .filter(flight -> flight.getOrigin().equalsIgnoreCase(origin))
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteDomain> findByDestination(String destination) {

        return findAll().stream()
                .filter(flight -> flight.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }
}
