package com.electron3d.model;

import java.util.List;

public class Floor {
    private int floorNumber;
    private List<Passenger> passengers;

    public Floor(int floorNumber, List<Passenger> passengers) {
        this.floorNumber = floorNumber;
        this.passengers = passengers;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
