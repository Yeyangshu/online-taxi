package com.yeyangshu.internalcommon.dto.serviceorderdispatch.datatransferobject;

import com.yeyangshu.internalcommon.dto.servicemap.datatransferobject.Vehicle;
import com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject.CarInfo;
import com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject.DriverInfo;
import lombok.Data;

@Data
public class DriverData {

    private Vehicle amapVehicle;

    private DriverInfo driverInfo;

    private double homeDistance;

    private CarInfo carInfo;

    private int isFollowing;

    private int timeSort;
}