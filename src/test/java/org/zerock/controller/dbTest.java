package org.zerock.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class dbTest {

	 private static final String DRIVER ="oracle.jdbc.driver.OracleDriver";
	 private static final String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
	 private static final String USER="book_ex";
	 private static final String PW="book_ex";
	 
	 @Test
	 public void testConnect() throws Exception{
	  
	  Class.forName(DRIVER);
	  
	  try(Connection con = DriverManager.getConnection(URL, USER, PW)){
	   
	   System.out.println(con);
	   System.out.println("연동성공");
	  }
	 }
	}



