package com.electron3d.controller;

import com.electron3d.Direction;
import com.electron3d.model.Building;
import com.electron3d.model.Elevator;
import com.electron3d.model.Floor;
import com.electron3d.model.Passenger;

import java.util.List;

public class Controller {
    private Building building;
    private Elevator elevator;
    private int floorsCount;

    public Controller(Building building, int floorsCount) {
        this.building = building;
        this.elevator = building.getElevator();
        this.floorsCount = floorsCount;
    }

    public void start() {
        while(true) {
            int currentFloorNumber = elevator.getCurrentFloor();
            Floor currentFloor = building.getFloor(currentFloorNumber);
            List<Passenger> currentFloorPassengers = currentFloor.getPassengers();
            //choose passengers direction
            //release passengers
            //set direction
            //take passengers
            int newFloor = elevator.move(chooseDirection());
        }
    }

    private Direction chooseDirection() {
        if (elevator.getCurrentFloor() < floorsCount) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }
}
