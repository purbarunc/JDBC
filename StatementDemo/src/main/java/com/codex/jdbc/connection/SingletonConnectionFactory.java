package com.codex.jdbc.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SingletonConnectionFactory {
	private static final SingletonConnectionFactory SINGLE;
	private Connection connection;
	InputStream inputStream = null;

	static {
		SINGLE = new SingletonConnectionFactory();
	}

	private SingletonConnectionFactory() {
		try {
			inputStream = new FileInputStream("src\\main\\resources\\dbConfig.properties");
			Properties prop = new Properties();
			prop.load(inputStream);

			String url = prop.getProperty("url");
			String driver = prop.getProperty("driver");
			String dbUser = prop.getProperty("user");
			String dbpwd = prop.getProperty("password");

			/* 1.load the driver */
			Class.forName(driver);

			/* 2.Establish a connection */
			connection = DriverManager.getConnection(url, dbUser, dbpwd);
			System.out.println("Connection successfully created:" + connection);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public static SingletonConnectionFactory getObject() {
		return SINGLE;
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	protected void finalize() throws Throwable {
		/* 3.Closing the Connection before the object is garbage collected */
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
}
