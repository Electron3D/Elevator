package com.electron3d;

import com.electron3d.controller.Controller;
import com.electron3d.model.Building;
import com.electron3d.model.Elevator;
import com.electron3d.util.Util;

import java.util.ArrayList;

public class Main {
    public static final int FIRST_FLOOR = 1;

    public static void main(String[] args) {
        Building building = init();
        Controller controller = new Controller(building);
        controller.start();
    }
    private static Building init() {
        int floorsCount = Util.getRandomBuildingHigh(5, 20);
        Elevator elevator = new Elevator(new ArrayList<>(), FIRST_FLOOR);
        return new Building(Util.populateBuilding(floorsCount), elevator);
    }
}