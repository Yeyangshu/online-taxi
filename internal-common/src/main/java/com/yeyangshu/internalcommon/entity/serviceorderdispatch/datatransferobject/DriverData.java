package com.yeyangshu.internalcommon.entity.serviceorderdispatch.datatransferobject;

import com.yeyangshu.internalcommon.entity.servicemap.datatransferobject.Vehicle;
import com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject.CarInfo;
import com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject.DriverInfo;
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