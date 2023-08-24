package com.electron3d.controller;

import com.electron3d.Direction;
import com.electron3d.model.Elevator;

public class ElevatorController {
    private Elevator elevator;
    private int floorsCount;

    public ElevatorController(Elevator elevator, int floorsCount) {
        this.elevator = elevator;
        this.floorsCount = floorsCount;
    }

    public void start() {
        while(true) {
            move();
        }
    }

    public void move() {
        if (chooseDirection().equals(Direction.UP)) {
            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
        } else {
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
        }
    }

    public Direction chooseDirection() {
        if (elevator.getCurrentFloor() < floorsCount) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }
}
