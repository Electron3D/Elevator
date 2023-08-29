package com.electron3d;

import com.electron3d.controller.Controller;
import com.electron3d.model.Building;
import com.electron3d.model.Elevator;
import com.electron3d.util.Util;

import java.util.ArrayList;

public class Main {
    private static int floorsCount;
    public static final int FIRST_FLOOR = 1;

    public static void main(String[] args) {
        Building building = init();
        Controller controller = new Controller(building, floorsCount);
        controller.start();
    }
    private static Building init() {
        floorsCount = Util.getRandomBuildingHigh(5, 20);
        Elevator elevator = new Elevator(new ArrayList<>(), FIRST_FLOOR);
        return new Building(floorsCount, Util.populateBuilding(floorsCount), elevator);
    }
}