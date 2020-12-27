package com.codex.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codex.jdbc.connection.SingletonConnectionFactory;

public class UserDao {
	private Connection con = SingletonConnectionFactory.getObject().getConnection();
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	PreparedStatement getStatement(String query) {
		try {
			pstmt = con.prepareStatement(query);
			return pstmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public int createUser(String name, int age, double sal) {
		String insertQry = "insert into User values(?,?,?)";
		PreparedStatement pstmt = getStatement(insertQry);
		try {
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, sal);
			int p = pstmt.executeUpdate();
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int findAllUsersByName(String name) {
		String selectQry = "select * from user where Name=?";
		PreparedStatement pstmt = getStatement(selectQry);
		try {
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
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

	public int updateUserByName(String name, double sal) {
		String updateQry = "update user set Salary=? where name=?";
		PreparedStatement pstmt = getStatement(updateQry);
		try {
			pstmt.setDouble(1, sal);
			pstmt.setString(2, name);
			int p = pstmt.executeUpdate();
			return p;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public int deleteUserByName(String name) {
		String deleteQry = "delete from user where name=?";
		PreparedStatement pstmt = getStatement(deleteQry);
		try {
			pstmt.setString(1, name);
			int p = pstmt.executeUpdate();
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void readUserByName(String name) {
		if (findAllUsersByName(name) == 1) {
			try {
				System.out.println("Age: " + rs.getInt("Age") + " Salary: " + rs.getDouble("Salary"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
