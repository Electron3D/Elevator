package com.electron3d.model;

import java.util.List;

public record Floor(int floorNumber, List<Passenger> passengers) {
}