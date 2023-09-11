package com.electron3d.controller;

import com.electron3d.Direction;
import com.electron3d.model.Building;
import com.electron3d.model.Elevator;
import com.electron3d.model.Floor;
import com.electron3d.model.Passenger;
import com.electron3d.view.ViewPrinter;

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
    private int floorsDoneCounter;

    public Controller(Building building) {
        this.building = building;
        this.elevator = building.getElevator();
        this.floorsCount = building.getFloorsCount();
        printer = new ViewPrinter(building);
    }

    public void start() {
        while(floorsDoneCounter < floorsCount) {
            printer.print();
            int currentFloorNumber = elevator.getCurrentFloor();
            Floor currentFloor = building.getFloor(currentFloorNumber);
            List<Passenger> currentFloorPassengers = currentFloor.passengers();

            List<Passenger> releasedPassengers = elevator.releasePassengers();
            elevator.setCurrentDirection(chooseDirection(currentFloorPassengers));

            List<Passenger> takenPassengers = elevator.takePassengers(currentFloorPassengers);
            currentFloorPassengers.removeAll(takenPassengers);

            releasedPassengers.forEach(this::updatePassengersGoals);
            currentFloorPassengers.addAll(releasedPassengers);

            elevator.move();
        }
    }

    private Direction chooseDirection(List<Passenger> currentFloorPassengers) {
        Direction direction;
        List<Passenger> passengers = elevator.getPassengers();
        if (passengers.isEmpty()) {
            if (currentFloorPassengers.isEmpty()) {
                direction = elevator.getCurrentFloor() == FIRST_FLOOR ? Direction.UP : Direction.DOWN;
            } else {
                direction = currentFloorPassengers
                        .stream()
                        .map(Passenger::getDirection)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElseThrow(() -> new RuntimeException("exception in the stream"));
            }
        } else {
            direction = elevator.getCurrentFloor() == FIRST_FLOOR ? Direction.UP : elevator.getCurrentDirection();
        }
        //catch none direction situation
        if (direction == Direction.NONE) {
            floorsDoneCounter++;
        }
        return direction;
    }

    public void updatePassengersGoals(Passenger passenger) {
        passenger.setStartFloor(passenger.getDestinationFloor());
        /*passenger.setDestinationFloor(
                Util.getRandomDestinationFloor(FIRST_FLOOR, floorsCount, passenger.getStartFloor()));*/
    }
}
