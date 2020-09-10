package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public List<ProductMaster> getProductList() throws ClassNotFoundException, SQLException {
		String sql = "select * from product";
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// map it to model
		List<ProductMaster> products = new ArrayList<ProductMaster>();
		while(rs.next()) {
			ProductMaster product = new ProductMaster(rs.getInt("id"), 
											 rs.getString("name"), 
											 rs.getString("cost"), 
											 rs.getString("description"));
			products.add(product);		
		}
		
		rs.close();
		stmt.close();
		this.disconnect();
		
		return products;
	}

	public boolean addNewProduct(String name, String description, String cost) throws SQLException {
		String sql = "insert into product (name,description,cost) values(?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, description);
		pstmt.setString(3, cost);
		
		boolean isProductAdded = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return isProductAdded;
		
	}

	public ProductMaster getProductDetails(String id) throws SQLException {
		ProductMaster product = null;
		String sql = "select * from product where id=" + id;
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			product = new ProductMaster(rs.getInt("id"), 
				 rs.getString("name"), 
				 rs.getString("cost"), 
				 rs.getString("description"));
		}
		
		return product;
	}

	public boolean updateProductDetails(String id, String name, String description, String cost) throws SQLException {
		String sql = "update product set name = ?,description = ? ,cost = ? where id=" + id;
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, description);
		pstmt.setString(3, cost);
		
		boolean isProductUpdated = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return isProductUpdated;
	}

	public boolean deleteProduct(String id) throws SQLException {
		boolean isProductDeleted = false;
		String sql = "delete from product where id = " + id;
		this.connect();
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		isProductDeleted = pstmt.executeUpdate() > 0;
		return isProductDeleted;
	}

	public List<ProductMaster> getProducts() throws SQLException
	{

		List<ProductMaster> plist= new ArrayList<ProductMaster>();
		this.connect();

		Statement stmt= this.jdbcConnection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from product");
		while(rs.next()) {
			ProductMaster product= getProductDetails(String.valueOf(rs.getInt("id")));
			plist.add(product);
		}

		rs=null;
		stmt=null;
		this.disconnect();

		return plist;
	}
	

}