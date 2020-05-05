package com.ds.firealarmrest.sensor;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ds.firealarmrest.DbConnection;

public class SensorRepository {

	/********* To get all sensor details from database  ************/
	
	public Response getSensors() {
		List<Sensor> sensors = new ArrayList<>();
		String sql = "select * from sensor"; // sql query for get all sensor details from database
		try {
			Statement statement = DbConnection.getConnection().createStatement();//Get the database connection
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Sensor sensor = new Sensor();
				getSensorDetails(result, sensor); // call the getSensorDetails() method with parameters
				sensors.add(sensor);
			}
			return Response.status(Response.Status.OK).entity(sensors).build();//Print 200 ok response code as a success message 
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();//Print 500 response code as a Internal Server error message
		}
	}

	/********* To get sensor details for a selected Id from database  ************/
	
	public Response getSensor(int id) {
		
		String sql = "select * from sensor where sensorId=" + id; // sql query for get selected sensor detail from database
		Sensor sensor = new Sensor();
		try {
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				getSensorDetails(result, sensor); // call the getSensorDetails() method with parameters
				return Response.status(Response.Status.OK).entity(sensor).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Invalid Id").build();//Print 404 response code as a Not found error message
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	
	/********* To insert sensor details to database  ************/
	
	public Response create(Sensor sensor) {
		// sql query for insert sensor details to database
		String sql = "insert into sensor(co2Level,smokeLevel,sensorstatus,floorNo,roomNo) values (?,?,?,?,?)"; 

		try {

			PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, sensor.getCo2Level());
			statement.setInt(2, sensor.getSmokeLevel());
			statement.setInt(3, sensor.getStatus());
			statement.setString(4, sensor.getFloorNo());
			statement.setString(5, sensor.getRoomNo());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				int insertedId = generatedKeys.getInt(1);
				return getSensor(insertedId);
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Invalid Data").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/********* To update sensor details of a selected sensor according to the sensor app from database  ************/
	/***** (In here smoke level and co2 level of selected sensor will only updated according to the sensor app) ******/
	
	public Response updateBySensor(Sensor sensor, int id) {
		// sql query for update sensor details of a selected sensor according to the sensor app from database
		String sql = "update sensor set co2Level=?, smokeLevel=? where sensorId=?"; 
		try {
			PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
			statement.setInt(3, id);
			statement.setInt(1, sensor.getCo2Level());
			statement.setInt(2, sensor.getSmokeLevel());
			statement.executeUpdate();
			return getSensor(id);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/********* To update Floor number and Room number of a selected sensor by Admin from database  ************/
	
	public Response updateSensor(Sensor sensor, int id) {
		// sql query for update Floor number and Room number of a selected sensor by Admin from database
		String sql = "update sensor set sensorstatus=?, floorNo=?, roomNo=? where sensorId=?";
		try {
			PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
			statement.setInt(4, id);
			statement.setInt(1, sensor.getStatus());
			statement.setString(2, sensor.getFloorNo());
			statement.setString(3, sensor.getRoomNo());
			statement.executeUpdate();
			return getSensor(id);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/********* To delete the details of a selected sensor by Admin from database  ************/
	
	public Response delete(int id) {
		// sql query for delete the details of a selected sensor by Admin from database
		String sql = "delete from sensor where sensorId=?";
		try {
			PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			//Print 202 response code as a Accepted success message with deleted sensor id
			return Response.status(Response.Status.ACCEPTED).entity(String.format("Deleted sensor with id %d", id))
					.build();
		
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	// private method get Sensor Details which is used by getSensors() and getSensor(int id) methods
	private void getSensorDetails(ResultSet result, Sensor sensor) throws SQLException {
		sensor.setSensorId(result.getInt(1));
		sensor.setCo2Level(result.getInt(2));
		sensor.setSmokeLevel(result.getInt(3));
		sensor.setStatus(result.getInt(4));
		sensor.setFloorNo(result.getString(5));
		sensor.setRoomNo(result.getString(6));
	}
}
