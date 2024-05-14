package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.exception.VehicleNotFoundException;
import com.java.CarConnect.model.Vehicle;

public class RemoveVehicle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Vehicle ID to delete: ");
        int vehicleId = sc.nextInt();
        
        IVehicleService vehicleService = new VehicleService();
        
        try {
            Vehicle vehicle = vehicleService.getVehicleByID(vehicleId);
            
            if (vehicle != null) {
                String message = vehicleService.removeVehicle(vehicle);
                System.out.println(message);
            } else {
            	VehicleNotFoundException ve = new VehicleNotFoundException("No matching record exists to remove.");
            	throw ve;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (VehicleNotFoundException ve) {
        	System.err.println(ve.getMessage());
		}
    }
}
