package com.bijesh.trimblepark.models;


import com.bijesh.trimblepark.models.enums.ParkingTypes;

public class Vehicle {

    private String vehicleNumber;
    private String vehicleModel;
    private String parkingTypes;

    public Vehicle(){

    }

    public Vehicle(String number,String model,String parkingTypes){
        this.vehicleNumber = number;
        this.vehicleModel = model;
        this.parkingTypes = parkingTypes;
    }


    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getParkingTypes() {
        return parkingTypes;
    }

    public void setParkingTypes(String parkingTypes) {
        this.parkingTypes = parkingTypes;
    }
}
