package com.electron3d.controller;

import com.electron3d.Direction;
import com.electron3d.model.Building;
import com.electron3d.model.Elevator;
import com.electron3d.model.Floor;
import com.electron3d.model.Passenger;
import com.electron3d.view.ViewPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.electron3d.Main.FIRST_FLOOR;

public class Controller {
    private final ViewPrinter printer;
    private final Building building;
    private final Elevator elevator;
    private final int floorsCount;

    public Controller(Building building) {
        this.building = building;
        this.elevator = building.getElevator();
        this.floorsCount = building.getFloorsCount();
        printer = new ViewPrinter(building);
    }

    public void start() {
        while(!checkIfAllArrived()) {
            int currentFloorNumber = elevator.getCurrentFloor();
            Floor currentFloor = building.getFloor(currentFloorNumber);
            List<Passenger> currentFloorPassengers = currentFloor.passengers();

            List<Passenger> releasedPassengers = elevator.releasePassengers();
            elevator.setCurrentDirection(chooseDirection(currentFloorPassengers));

            List<Passenger> takenPassengers = elevator.takePassengers(currentFloorPassengers);
            currentFloorPassengers.removeAll(takenPassengers);

            updatePassengersGoals(releasedPassengers);
            currentFloorPassengers.addAll(releasedPassengers);

            printer.print();
            elevator.move();
        }
    }

    private Direction chooseDirection(List<Passenger> currentFloorPassengers) {
        Direction direction;
        List<Passenger> passengers = elevator.getPassengers();
        /*if (elevator.checkPassengersLimit()) {
            return elevator.getCurrentDirection();
        }*/
        if (passengers.isEmpty()) {
            if (currentFloorPassengers.isEmpty()) {
                direction = elevator.getCurrentFloor() == FIRST_FLOOR ? Direction.UP : Direction.DOWN;
            } else {
                direction = currentFloorPassengers
                        .stream()
                        .filter(passenger -> !passenger.isArrived())
                        .map(Passenger::getDirection)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse(elevator.getCurrentFloor() == FIRST_FLOOR ? Direction.UP : Direction.DOWN);
            }
        } else {
            direction = elevator.getCurrentFloor() == FIRST_FLOOR ? Direction.UP : elevator.getCurrentDirection();
        }
        return direction;
    }

    private boolean checkIfAllArrived() {
        List<Passenger> allPassengers = new ArrayList<>();
        for (int i = 1; i <= floorsCount; i++) {
            allPassengers.addAll(building.getFloor(i).passengers());
        }
        for (Passenger passenger : allPassengers) {
            if (!passenger.isArrived()) {
                return false;
            }
        }
        return true;
    }
    private void updatePassengersGoals(List<Passenger> releasedPassengers) {
        for (Passenger passenger : releasedPassengers) {
            passenger.setStartFloor(passenger.getDestinationFloor());
            passenger.setArrived(true);
        }
    }
}
