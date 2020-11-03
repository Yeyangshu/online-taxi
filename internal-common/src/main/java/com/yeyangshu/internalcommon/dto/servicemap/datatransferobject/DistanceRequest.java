package com.yeyangshu.internalcommon.dto.servicemap.datatransferobject;

import lombok.Data;

/**
 *
 */
@Data
public class DistanceRequest {

    private String originLongitude;

    private String originLatitude;

    private String destinationLongitude;

    private String destinationLatitude;

}