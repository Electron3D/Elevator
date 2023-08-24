package com.electron3d;

import com.electron3d.controller.ElevatorController;
import com.electron3d.model.Elevator;
import com.electron3d.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static int floorsCount;
    private static final Random random = new Random();
    public static void main(String[] args) {
        Elevator elevator = init();
        ElevatorController controller = new ElevatorController(elevator, floorsCount);
        controller.start();
    }

    private static Elevator init() {
        floorsCount = random.nextInt(5, 21);
        return new Elevator(populateBuilding(), 1);
    }

    public static List<Passenger> populateBuilding() {
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i <= floorsCount; i++) {
            int thisFloorPassengers = random.nextInt(0, 11);
            for (int j = 0; j < thisFloorPassengers; j++) {
                int destinationFloor = getDestinationFloor(random, 1, floorsCount, i);
                passengers.add(new Passenger(i, destinationFloor));
            }
        }
        return passengers;
    }

    public static int getDestinationFloor(Random rnd, int start, int end, int currentFloor) {
        int destinationFloor = start + rnd.nextInt(end - start);
        if (destinationFloor == currentFloor) {
            if (destinationFloor < end) {
                destinationFloor++;
            } else {
                destinationFloor--;
            }
        }
        return destinationFloor;
    }
}