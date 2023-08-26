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

    public int move(Direction direction) {
        if (direction.equals(Direction.UP)) {
            return ++currentFloor;
        } else {
            return --currentFloor;
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

    public void takePassengers(List<Passenger> newPassengers) {
        for (Passenger passenger : newPassengers) {
            if (checkPassengersLimit()) {
                break;
            }
            //check direction
            passengers.add(passenger);
        }
    }

    public boolean checkPassengersLimit() {
        return passengers.size() >= PASSENGERS_LIMIT;
    }
}