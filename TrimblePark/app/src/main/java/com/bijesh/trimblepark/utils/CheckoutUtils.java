package com.bijesh.trimblepark.utils;

import com.bijesh.trimblepark.models.enums.ParkingTypes;

public class CheckoutUtils {


    public static double getCheckout(ParkingTypes parkingTypes,int hour){
        double retAmt = 0.0;
        if (hour == 1)
            return parkingTypes.getFirstHourValue();
        else if(hour > 1){
            int hrCharge = parkingTypes.getHourlyValue();
            retAmt += parkingTypes.getFirstHourValue();
            int restHr = hour - 1;
            int restAmt = restHr * hrCharge;
            retAmt += restAmt;
        }
        return retAmt;
    }

    public static ParkingTypes getParkingType(String s){
        return ParkingTypes.valueOf(s);
    }


}
