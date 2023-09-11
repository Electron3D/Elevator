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
        StringBuilder builder = new StringBuilder();
        String delimiter = " | ";
        String onePassSpace = "    ";
        String builderDelimiter = "--------------------------------------------------------------";
        builder.append(builderDelimiter).append("\n");
        for (int i = floorsCount; i >= 1; i--) {
            Floor floor = building.getFloor(i);
            List<Integer> floorPassDestinFloor = floor.passengers()
                    .stream()
                    .map(Passenger::getDestinationFloor)
                    .toList();
            builder.append(i);
            if (i < 10) {
                builder.append(" ");
            }
            builder.append(delimiter);
            List<Integer> elevPassDestinFloor = elevator.getPassengers().stream().map(Passenger::getDestinationFloor).toList();
            if (elevator.getCurrentFloor() == i) {
                int freeSpace = 5;
                int oneDigitIntCounter = 0;
                for (Integer integer : elevPassDestinFloor) {
                    builder.append("[").append(integer).append("]");
                    if (integer < 10) {
                        oneDigitIntCounter++;
                    }
                    freeSpace--;
                }
                builder.append(" ".repeat(oneDigitIntCounter));
                builder.append(onePassSpace.repeat(Math.max(0, freeSpace)));
            } else {
                builder.append(onePassSpace.repeat(5));
            }
            builder.append(delimiter).append(floorPassDestinFloor).append("\n");
        }
        builder.append(builderDelimiter);
        System.out.println(builder);
    }
}
