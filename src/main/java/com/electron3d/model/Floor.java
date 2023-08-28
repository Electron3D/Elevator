package com.electron3d.model;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<Passenger> passengers;

    public Floor(int floorNumber, List<Passenger> passengers) {
        this.floorNumber = floorNumber;
        this.passengers = passengers;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
