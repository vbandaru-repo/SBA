package com.iiht.evaluation.coronokit.dao;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.model.UserProfile;

public class KitDao {

	private String jdbcURL;

	private String jdbcUsername;

	private String jdbcPassword;

	private Connection jdbcConnection;



	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {

        this.jdbcURL = jdbcURL;

        this.jdbcUsername = jdbcUsername;

        this.jdbcPassword = jdbcPassword;

    }



	protected void connect() throws SQLException {

		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}



	protected void disconnect() throws SQLException {

		if (jdbcConnection != null && !jdbcConnection.isClosed()) {

			jdbcConnection.close();

		}

	}

	public boolean insertUser(String name,String email,String contact, String address) throws SQLException{

		String sql = "insert into user (name,email,contact,address) values(?,?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, email);
		pstmt.setString(3, contact);
		pstmt.setNString(4, address);
		
		boolean isUserAdded = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return isUserAdded;
	}
	
	public UserProfile getUserDetails(String contact) throws SQLException {
		UserProfile userProfile = null;
		String sql = "select * from user where contact=" + contact;
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			userProfile = new UserProfile(rs.getInt("id"), 
				 rs.getString("name"), 
				 rs.getString("email"), 
				 rs.getString("contact"),
				 rs.getString("address"));
		}
		
		return userProfile;
	}
	
	
}