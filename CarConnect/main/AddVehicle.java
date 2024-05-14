package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.model.Vehicle;

public class AddVehicle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IVehicleService vehicleService = new VehicleService();

        //System.out.println("Enter vehicle details:");

        // Get vehicle details from the user
        Vehicle vehicle = new Vehicle();
        
        System.out.println("Enter Vehicle Id : ");
		int vehicleId = scanner.nextInt();
		vehicle.setVehicleId(vehicleId);
		scanner.nextLine();
		
		try {
			Vehicle isValidVehicle = vehicleService.getVehicleByID(vehicleId);
			
			if(isValidVehicle!=null)
			{
				System.out.println("Vehicle Id already exists.");
			}
			else
			{
				// Set vehicle properties
		        System.out.print("Enter Model: ");
		        vehicle.setModel(scanner.nextLine());

		        System.out.print("Enter Make: ");
		        vehicle.setMake(scanner.nextLine());

		        
		        System.out.print("Enter Year: ");
                int year = scanner.nextInt();
                if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                	InvalidInputException iie = new InvalidInputException("Invalid year. Please enter a valid year.");
                	throw iie;
                }
                vehicle.setYear(year);

		        scanner.nextLine(); // Consume newline

		        System.out.print("Enter Color: ");
		        vehicle.setColor(scanner.nextLine());
		        
		        System.out.print("Enter Registration Number: ");
		        String regNumber = scanner.nextLine();
		        if (regNumber.isEmpty()) {
		        	InvalidInputException iie = new InvalidInputException("Registration number cannot be empty.");
		        	throw iie ;
		        }
		        vehicle.setRegistrationNumber(regNumber);

		        
		        System.out.print("Enter Availability (TRUE/FALSE): ");
		        String availabilityInput = scanner.nextLine().toLowerCase(); // Convert input to lowercase for case-insensitive comparison
		        if (availabilityInput.equals("true")) {
		            vehicle.setAvailability(true);
		        } else if (availabilityInput.equals("false")) {
		            vehicle.setAvailability(false);
		        } else {
		            InvalidInputException iie =  new InvalidInputException("Invalid input for Availability. Please enter 'true' or 'false'.");
		            throw iie;
		        }
		        
		        System.out.print("Enter Daily Rate: ");
		        double dailyRate = scanner.nextDouble();
		        if (dailyRate <= 0) {
		            InvalidInputException iie = new InvalidInputException("Daily rate must be greater than 0.");
		            throw iie;
		        }
		        vehicle.setDailyRate(dailyRate);
		        
		      try {
		            String result = vehicleService.addVehicle(vehicle);
		            System.out.println(result);
			
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
  
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (InvalidInputException iie) {
        	System.err.println(iie.getMessage());
		} 
    }
}

