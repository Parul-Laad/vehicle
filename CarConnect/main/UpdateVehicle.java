package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.exception.VehicleNotFoundException;
import com.java.CarConnect.model.Vehicle;

public class UpdateVehicle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = new Vehicle();

        System.out.println("Enter Vehicle ID to update:");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        vehicle.setVehicleId(vehicleId);

        IVehicleService vehicleService = new VehicleService();
        try {
            Vehicle isValidVehicle = vehicleService.getVehicleByID(vehicleId);
            if (isValidVehicle == null) {
            	VehicleNotFoundException ve = new VehicleNotFoundException("No matching record exists to update.");
            	throw ve;
            } else {
                System.out.println("Enter Model:");
                vehicle.setModel(scanner.nextLine());

                System.out.println("Enter Make:");
                vehicle.setMake(scanner.nextLine());

                
                System.out.print("Enter Year: ");
                int year = scanner.nextInt();
                if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                	InvalidInputException iie = new InvalidInputException("Invalid year. Please enter a valid year.");
                	throw iie;
                }
                vehicle.setYear(year);
                scanner.nextLine();

                System.out.println("Enter Color:");
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
                    String message = vehicleService.updateVehicle(vehicle);
                    System.out.println(message);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (VehicleNotFoundException ve) {
        	System.err.println(ve.getMessage());	
        } catch (InvalidInputException iie) {
        	System.err.println(iie.getMessage());
		}
        
    }
}

