package com.vspace.yace.testing;
import java.sql.*;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vspace.yace.domain.*;

public class JDBCTest {


	public static void main(String[] args) {

		System.out.println("testing..");

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"yace-beans.xml"});

		A objA = (A)context.getBean("aDAO");
 
		System.out.println(objA.sayHello("World"));

		//User user = new User(22226, "XYZ", "xxx","yyyy","ee", "1.1.1.1");
		//objA.addUser(user);

		System.out.println(objA.sayHello("Record inserted"));

	}

	// Not used
	public static Connection getConnection() throws Exception {
		
		Driver d = (Driver)Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		Connection c = DriverManager.getConnection(
				"jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=*.accdb"
		);
		return c;
	}	

}
