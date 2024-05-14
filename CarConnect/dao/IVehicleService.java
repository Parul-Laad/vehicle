package com.java.CarConnect.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.CarConnect.model.Vehicle;

public interface IVehicleService {
	Vehicle getVehicleByID(int vehicleID) throws ClassNotFoundException, SQLException;
	List<Vehicle> getAvailableVehicles() throws SQLException, ClassNotFoundException;
	String addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException;
	String updateVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException;
	String removeVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException;

}
