package com.electron3d.model;

import com.electron3d.Direction;

import java.util.List;

public class Elevator {
    private List<Passenger> passengers;

    private int currentFloor;
    private static final int MAX_NUMBER_OF_PASSENGERS = 5;

    public Elevator(List<Passenger> passengers, int currentFloor) {
        this.passengers = passengers;
        this.currentFloor = currentFloor;
    }

    public boolean checkPassengersLimit() {
        if (passengers.size() < MAX_NUMBER_OF_PASSENGERS) {
            return false;
        }
        return true;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
