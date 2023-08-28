package com.electron3d.view;

import com.electron3d.model.Building;
import com.electron3d.model.Elevator;
import com.electron3d.model.Passenger;

public class ViewPrinter {
    private Building building;
    private Elevator elevator;

    public ViewPrinter(Building building) {
        this.building = building;
        this.elevator = building.getElevator();
    }

    public void print() {
        int currentFloor = elevator.getCurrentFloor();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Building is " + building.getFloorsCount() + " floors high.");
        System.out.println("Current floor: " + currentFloor + " Direction: " + elevator.getCurrentDirection());
        System.out.println("Passengers on the floor: " + building.getFloor(currentFloor).getPassengers().stream().map(Passenger::getDestinationFloor).toList());
        System.out.println("Passengers in the elevator: " + elevator.getPassengers().stream().map(Passenger::getDestinationFloor).toList());
        System.out.println("--------------------------------------------------------------");
        System.out.println();
    }
}
