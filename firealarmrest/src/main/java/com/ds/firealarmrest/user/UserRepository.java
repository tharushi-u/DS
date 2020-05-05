package com.ds.firealarmrest.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.ws.rs.core.Response;

import com.ds.firealarmrest.DbConnection;

public class UserRepository {

	/********* To validate the user when login to the system  ************/
	public Response login(String userName, String password) {
		User user = new User();
		//sql query to check user name and password from database
		String sql = "select * from registeredusers where userName='" + userName + "'";
		try {
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				if ((getMd5(password).equals(result.getString(6)))) { // Todo: need to user MD5
					user.setFirstName(result.getString(2));
					user.setLastName(result.getString(3));
					user.setIsAdmin(result.getInt(7));
					user.setUserId(result.getInt(1));
					return Response.status(Response.Status.OK).entity(user).build(); // Print 200 ok response code as a success message 
				} else {
					return Response.status(Response.Status.OK).entity("Invalid password").build();// print error msg when password is incorrect
				}
			} else {
				return Response.status(Response.Status.OK).entity("Invalid userName").build();//print error msg when user name is invalid
			}
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();// Print 500 response code as a Internal Server error message
		}
	}

	/***** To encrypt the password *****/
	public static String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashPassword = no.toString(16);
			while (hashPassword.length() < 32) {
				hashPassword = "0" + hashPassword;
			}
			return hashPassword;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/***** To register a user to the system *****/
	public Response Registeruser(User user) {
        // sql query to insert a user to the database
		String sql = "insert into registeredusers(firstName,lastName,userName,email,Password) values (?,?,?,?,?)";
		try {
			System.out.println("user try");
			PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUserName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
//			statement.setString(5, getMd5(user.getPassword()));
			statement.executeUpdate();
//             ResultSet generatedKeys = statement.getGeneratedKeys();
//             if (generatedKeys.next()) {
//                 int insertedId = generatedKeys.getInt(1);
//                 //return getSensor(insertedId);
//                 return getUser(insertedId);
//             } else {
//                 return Response.status(Response.Status.NOT_FOUND).entity("Invalid Data").build();
			return Response.status(Response.Status.OK).entity(user).build();
//             }
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
