package com.lastminute.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteDomain {

    private String origin;

    private String destination;

    private String code;
}
