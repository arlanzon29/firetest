package com.daromar.firebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FireAppSQLLite
extends FireApp {
	
	protected   Connection connection = null;

	public FireAppSQLLite(String basePath,String sqllitePath) {
		super(basePath);
		// TODO Auto-generated constructor stub
	      
	   try
	   {
	     // create a database connection
		   Class.forName("org.sqlite.JDBC");
		   connection = DriverManager.getConnection("jdbc:sqlite:"+sqllitePath);
		   
		/* Statement statement = connection.createStatement();
		 statement.setQueryTimeout(30);  // set timeout to 30 sec.
		
		 statement.executeUpdate("drop table if exists person");
		 statement.executeUpdate("create table person (id integer, name string)");
		 statement.executeUpdate("insert into person values(1, 'leo')");
		 statement.executeUpdate("insert into person values(2, 'yui')");
		 ResultSet rs = statement.executeQuery("select * from person");
		 while(rs.next())
		 {
		   // read the result set
		   System.out.println("name = " + rs.getString("name"));
		   System.out.println("id = " + rs.getInt("id"));
		 }*/
	   }
	   catch(Exception e)
	   {
	     
	     System.err.println(e.getMessage());
	   }	     
	}

}
