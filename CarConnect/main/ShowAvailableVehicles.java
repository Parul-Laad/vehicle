package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.List;

import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.exception.VehicleNotFoundException;
import com.java.CarConnect.model.Vehicle;

public class ShowAvailableVehicles {
    public static void main(String[] args) {
        IVehicleService vehicleService = new VehicleService();
        try {
            List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
            if (!availableVehicles.isEmpty()) {
                for (Vehicle vehicle : availableVehicles) {
                    System.out.println(vehicle);
                }
            } else {
            	VehicleNotFoundException ve = new VehicleNotFoundException("No available vehicles.");
            	throw ve;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (VehicleNotFoundException ve) {
        	System.err.println(ve.getMessage());
		}
    }
}

