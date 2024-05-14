package com.java.CarConnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.CarConnect.model.Vehicle;
import com.java.CarConnect.util.DBConnUtil;
import com.java.CarConnect.util.DBPropertyUtil;

public class VehicleService implements IVehicleService{
	
	Connection connection;
	PreparedStatement pst;

	@Override
	public Vehicle getVehicleByID(int vehicleID) throws ClassNotFoundException, SQLException {
		
		String connStr = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.GetConnection(connStr);
		String cmd = "Select * from vehicle where VehicleID = ? ";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, vehicleID);
		ResultSet rs = pst.executeQuery();
		Vehicle vehicle = null;
		if(rs.next()) {
			vehicle = new Vehicle();
			vehicle.setVehicleId(rs.getInt("VehicleId"));
            vehicle.setModel(rs.getString("Model"));
            vehicle.setMake(rs.getString("Make"));
            vehicle.setYear(rs.getInt("Year"));
            vehicle.setColor(rs.getString("Color"));
            vehicle.setRegistrationNumber(rs.getString("RegistrationNumber"));
            vehicle.setAvailability(rs.getBoolean("Availability"));
		}

		
		return vehicle;
	}

	@Override
	public List<Vehicle> getAvailableVehicles() throws SQLException, ClassNotFoundException {
		String connStr = DBPropertyUtil.getConnectionString("db");
        connection = DBConnUtil.GetConnection(connStr);
        String cmd = "select * from Vehicle where Availability = ?";
        pst = connection.prepareStatement(cmd);
        pst.setBoolean(1, true);
        ResultSet rs = pst.executeQuery();
        List<Vehicle> availableVehicles = new ArrayList<Vehicle>();
        while (rs.next()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(rs.getInt("VehicleId"));
            vehicle.setModel(rs.getString("Model"));
            vehicle.setMake(rs.getString("Make"));
            vehicle.setYear(rs.getInt("Year"));
            vehicle.setColor(rs.getString("Color"));
            vehicle.setRegistrationNumber(rs.getString("RegistrationNumber"));
            vehicle.setAvailability(rs.getBoolean("Availability"));
            availableVehicles.add(vehicle);
        }
        return availableVehicles;
    }

	@Override
	public String addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		String connStr = DBPropertyUtil.getConnectionString("db");
        connection = DBConnUtil.GetConnection(connStr);
        String cmd = "Insert into Vehicle (VehicleID, Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate)"
        		+ "values (?,?,?,?,?,?,?,?)";
        pst = connection.prepareStatement(cmd);
        pst.setInt(1, vehicle.getVehicleId());
        pst.setString(2, vehicle.getModel());
        pst.setString(3, vehicle.getModel());
        pst.setInt(4,vehicle.getYear());
        pst.setString(5, vehicle.getColor());
        pst.setString(6, vehicle.getRegistrationNumber());
        pst.setBoolean(7, vehicle.isAvailability());
        pst.setDouble(8, vehicle.getDailyRate());
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            return "Vehicle added successfully.";
        } else {
            return "Failed to add vehicle.";
        }
        
	}

	@Override
	public String updateVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		String connStr = DBPropertyUtil.getConnectionString("db");
        connection = DBConnUtil.GetConnection(connStr);
        String cmd = "Update Vehicle set Model = ?, Make = ?, Year = ?, Color = ?, "
        		+ "RegistrationNumber = ?, Availability = ?, DailyRate = ? where vehicleId = ? ";
        
        pst = connection.prepareStatement(cmd);
        pst.setString(1, vehicle.getModel());
        pst.setString(2, vehicle.getMake());
        pst.setInt(3, vehicle.getYear());
        pst.setString(4, vehicle.getColor());
        pst.setString(5, vehicle.getRegistrationNumber());
        pst.setBoolean(6, vehicle.isAvailability());
        pst.setDouble(7, vehicle.getDailyRate());
        pst.setInt(8, vehicle.getVehicleId());
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            return "Vehicle updated successfully.";
        } else {
            return "Failed to update vehicle.";
        }
    }

	@Override
	public String removeVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		String connStr = DBPropertyUtil.getConnectionString("db");
        connection = DBConnUtil.GetConnection(connStr);
        String cmd = "delete from Vehicle where VehicleId=?";
        pst = connection.prepareStatement(cmd);
        pst.setInt(1, vehicle.getVehicleId());
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            return "Vehicle removed successfully.";
        } else {
            return "Failed to remove vehicle.";
        }
	}

}


