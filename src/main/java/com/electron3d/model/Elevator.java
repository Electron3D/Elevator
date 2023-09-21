package com.electron3d.model;

import com.electron3d.Direction;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private final List<Passenger> passengers;
    private int currentFloor;
    private Direction currentDirection;
    private static final int PASSENGERS_LIMIT = 5;

    public Elevator(List<Passenger> passengers, int currentFloor) {
        this.passengers = passengers;
        this.currentFloor = currentFloor;
        this.currentDirection = Direction.UP;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void move() {
        if (currentDirection.equals(Direction.UP)) {
            currentFloor++;
        } else {
            currentFloor--;
        }
    }

    public List<Passenger> releasePassengers() {
        List<Passenger> arrivedPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (currentFloor == passenger.getDestinationFloor()) {
                arrivedPassengers.add(passenger);
            }
        }
        for (Passenger passenger : arrivedPassengers) {
            passengers.remove(passenger);
        }
        return arrivedPassengers;
    }

    public List<Passenger> takePassengers(List<Passenger> newPassengers) {
        List<Passenger> takenPassengers = new ArrayList<>();
        for (Passenger passenger : newPassengers) {
            if (checkPassengersLimit()) {
                break;
            }
            if (passenger.isInTransit() && currentDirection.equals(passenger.getDirection())) {
                passengers.add(passenger);
                takenPassengers.add(passenger);
            }
        }
        return takenPassengers;
    }

    public boolean checkPassengersLimit() {
        return passengers.size() >= PASSENGERS_LIMIT;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
