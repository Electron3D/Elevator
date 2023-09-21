package com.electron3d.util;

import com.electron3d.model.Floor;
import com.electron3d.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.electron3d.Main.FIRST_FLOOR;

public class Util {
    private static final Random random = new Random();
    private static final int MIN_NUMBER_OF_PASSENGERS = 0;
    private static final int MAX_NUMBER_OF_PASSENGERS = 10;

    public static int getRandomDestinationFloor(int start, int end, int currentFloor) {
        int destinationFloor = start + random.nextInt(end - start);
        if (destinationFloor == currentFloor) {
            if (destinationFloor < end) {
                destinationFloor++;
            } else {
                destinationFloor--;
            }
        }
        return destinationFloor;
    }

    public static List<Floor> populateBuilding(int floorsCount) {
        List<Floor> floors = new ArrayList<>();
        for (int currentFloor = 1; currentFloor <= floorsCount; currentFloor++) {
            List<Passenger> passengers = new ArrayList<>();
            int thisFloorPassengers = random.nextInt(MIN_NUMBER_OF_PASSENGERS, MAX_NUMBER_OF_PASSENGERS + 1);
            for (int j = 0; j < thisFloorPassengers; j++) {
                int destinationFloor = Util.getRandomDestinationFloor(FIRST_FLOOR, floorsCount, currentFloor);
                passengers.add(new Passenger(currentFloor, destinationFloor));
            }
            floors.add(new Floor(currentFloor, passengers));
        }
        return floors;
    }

    public static int getRandomBuildingHigh(int minHigh, int maxHigh) {
        return random.nextInt(minHigh, maxHigh + 1);
    }
}
