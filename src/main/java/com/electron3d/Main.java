package com.electron3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Elevator elevator = new Elevator(populateBuilding());
        System.out.println(elevator);
    }

    public static List<Passenger> populateBuilding() {
        List<Passenger> passengers = new ArrayList<>();
        Random random = new Random();
        int floorsCount = random.nextInt(5, 21);
        for (int i = 0; i < floorsCount; i++) {
            int thisFloorPassengers = random.nextInt(0, 11);
            for (int j = 0; j < thisFloorPassengers; j++) {
                int destinationFloor = getDestinationFloor(random, 0, floorsCount, i);
                passengers.add(new Passenger(i, destinationFloor));
            }

        }
        return passengers;
    }

    public static int getDestinationFloor(Random rnd, int start, int end, int currentFloor) {
        int destinationFloor = start + rnd.nextInt(end - start);
        if (destinationFloor == currentFloor) {
            destinationFloor++;
        }
        return destinationFloor;
    }
}