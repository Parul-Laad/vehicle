package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.CarConnect.model.Vehicle;
import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.exception.VehicleNotFoundException;

public class ShowVehicleById {
	public static void main(String[] args) {
		
		Vehicle vehicle = new Vehicle();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Vehicle Id : ");
		int vehicleId = sc.nextInt();
		
		IVehicleService vehicleService = new VehicleService();
		
		try {
			vehicle = vehicleService.getVehicleByID(vehicleId);
			
			if(vehicle!=null)
			{
				System.out.println(vehicle);
			}
			else
			{
				VehicleNotFoundException ve = new VehicleNotFoundException("No matching record found");
            	throw ve;

			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VehicleNotFoundException ve) {
			System.err.println(ve.getMessage());
		}
		
		
		

	}

}
