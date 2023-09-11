package com.electron3d.view;

import com.electron3d.model.Building;
import com.electron3d.model.Elevator;
import com.electron3d.model.Floor;
import com.electron3d.model.Passenger;

import java.util.List;

public class ViewPrinter {
    private final Building building;
    private final Elevator elevator;

    public ViewPrinter(Building building) {
        this.building = building;
        this.elevator = building.getElevator();
    }

    public void oldPrint() {
        int currentFloor = elevator.getCurrentFloor();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Building is " + building.getFloorsCount() + " floors high.");
        System.out.println("Current floor: " + currentFloor + " Direction: " + elevator.getCurrentDirection());
        System.out.println("Passengers on the floor: " + building.getFloor(currentFloor)
                .passengers()
                .stream()
                .map(Passenger::getDestinationFloor)
                .toList());
        System.out.println("Passengers in the elevator: " + elevator.getPassengers()
                .stream()
                .map(Passenger::getDestinationFloor)
                .toList());
        System.out.println("--------------------------------------------------------------\n");
    }

    public void print() {
        int floorsCount = building.getFloorsCount();
        for (int i = 0; i < floorsCount; i++) {
            Floor floor = building.getFloor(i);
            List<Passenger> passengers = floor.passengers();
            //add string builder here
            System.out.println(i + " | " + passengers + " | ");
        }
    }
}
