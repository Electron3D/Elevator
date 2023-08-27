package com.electron3d.util;

import java.util.Random;

public class Util {
    public static int getRandomDestinationFloor(int start, int end, int currentFloor) {
        Random random = new Random();
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
}
