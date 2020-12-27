package com.codex.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.codex.jdbc.connection.SingletonConnectionFactory;
public class UserDao {
	private Connection connection = SingletonConnectionFactory.getObject().getConnection();
	private ResultSet rs=null;
	private Statement stmt=null;
	/**
	 * Utility method to Create a Platform.<p>Statement is oone of the Interface 
	 * of JDBC present in java.sql package which is mainly used to create for 
	 * execution of sql statements.<p>At the time of creating the Statement we need 
	 * not to pass any query.<p> 
	 * 
	 * @return Statement Object
	 */
	Statement getStatement() {
		try {
			stmt = connection.createStatement();
			return stmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int findAll(String name) {
		String selectQry = "select * from user where Name='" + name + "'";
		try {
			rs = getStatement().executeQuery(selectQry);
			if (rs.next() != false) {
				return 1;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}
	public void read(String name) {
		if(findAll(name)==1) {
			try {
				System.out.println("Age: "+rs.getInt("Age")+" Salary: "+rs.getDouble("Salary"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int createUser(String name, int age, double sal) {
		String insertQry = "insert into user values('" + name + "'," + age + "," + sal + ")";
		try {
			int p = getStatement().executeUpdate(insertQry);
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int updateUser(String name, double sal) {
		String updateQry = "update user set Salary=" + sal + " where Name='" + name + "'";
		try {
			int p = getStatement().executeUpdate(updateQry);
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteUser(String name) {
		String deleteQry = "delete from user where Name='" + name + "'";
		try {
			int p = getStatement().executeUpdate(deleteQry);
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
