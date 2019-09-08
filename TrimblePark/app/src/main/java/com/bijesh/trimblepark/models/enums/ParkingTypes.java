package com.bijesh.trimblepark.models.enums;

 public enum ParkingTypes {


    MotorCycle(1,50,25),
    SmallCar(2,100,45),
    MediumCar(3,150,75),
    LargeCar(4,200,100);

    private int ordinal;
    private int firstHourValue;
    private int hourlyValue;

    ParkingTypes(int ordinal,int firstHourValue,int hourlyValue){
        this.ordinal = ordinal;
        this.firstHourValue = firstHourValue;
        this.hourlyValue = hourlyValue;
    }

     public int getOrdinal() {
         return ordinal;
     }

     public int getFirstHourValue() {
        return firstHourValue;
    }

    public int getHourlyValue() {
        return hourlyValue;
    }

//    Logic for assigning vehicle space is ordinal has to be greater
//    Logic for MotorCycle in slots if small car slot 2,me is 3 lar is 4 just take the ordinal and ths the slot allocation


}
