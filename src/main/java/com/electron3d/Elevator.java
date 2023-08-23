package com.electron3d;

import java.util.List;

public class Elevator {
    private List<Passenger> passengers;
    private int currentFloor;

    public Elevator(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void moveUp() {

    }

    public void moveDown() {

    }

    public boolean checkPassengersLimit() {
        return false;
    }

    public Direction chooseDirection() {
        return Direction.DOWN;
    }
}
