package com.lastminute.repository;

import com.lastminute.domain.RouteDomain;

import java.util.List;

public interface RouteRepository {

    List<RouteDomain> findAll();

    RouteDomain findByCode(String code);

    List<RouteDomain> findByOrigin(String origin);

    List<RouteDomain> findByDestination(String destination);
}
