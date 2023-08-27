package com.electron3d;

import com.electron3d.controller.Controller;
import com.electron3d.model.Building;
import com.electron3d.model.Elevator;
import com.electron3d.model.Floor;
import com.electron3d.model.Passenger;
import com.electron3d.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static int floorsCount;
    private static final Random random = new Random();

    public static void main(String[] args) {
        Building building = init();
        Controller controller = new Controller(building, floorsCount);
        controller.start();
    }

    private static Building init() {
        floorsCount = random.nextInt(5, 21);
        Elevator elevator = new Elevator(new ArrayList<>(), 1);
        return new Building(floorsCount, populateBuilding(), elevator);
    }

    public static List<Floor> populateBuilding() {
        List<Floor> floors = new ArrayList<>();
        for (int currentFloor = 1; currentFloor <= floorsCount; currentFloor++) {
            List<Passenger> passengers = new ArrayList<>();
            int thisFloorPassengers = random.nextInt(0, 11);
            for (int j = 0; j < thisFloorPassengers; j++) {
                int destinationFloor = Util.getRandomDestinationFloor(1, floorsCount, currentFloor);
                passengers.add(new Passenger(currentFloor, destinationFloor));
            }
            floors.add(new Floor(currentFloor, passengers));
        }
        return floors;
    }
}