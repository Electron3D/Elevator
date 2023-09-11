package com.electron3d.model;

import com.electron3d.Direction;

public class Passenger {
    private int startFloor;
    private int destinationFloor;
    private boolean arrived;

    public Passenger(int startFloor, int destinationFloor) {
        this.startFloor = startFloor;
        this.destinationFloor = destinationFloor;
        arrived = false;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public Direction getDirection() {
        return destinationFloor - startFloor > 0 ? Direction.UP : Direction.DOWN;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }
}
