package com.electron3d.model;

import java.util.List;

public class Building {
    private final int floorsCount;
    private final List<Floor> floors;
    private final Elevator elevator;

    public Building(int floorsCount, List<Floor> floors, Elevator elevator) {
        this.floorsCount = floorsCount;
        this.floors = floors;
        this.elevator = elevator;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public Floor getFloor(int number) {
        return floors.get(number);
    }

    public Elevator getElevator() {
        return elevator;
    }
}
