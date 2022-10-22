package com.jacobsheldon.jdbc01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.Test;

public class JDBCTest {
	@Test
	public void testGetConnection() throws Exception {
		Connection conn = null;
		try {
			conn = JDBC.getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn);
		}
	}
	
	@Test
	public void testInsert() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into users (name, email, birth) values (?, ?, ?)";
			conn = JDBC.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, "mike");
			ps.setObject(2, "mike@gmail.com");
			ps.setObject(3, "2003-10-31");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, ps);
		}
	}
	
	@Test
	public void testQuery() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBC.getConnection();
			String sql = "select id, name, email, birth from users where id < ?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 10);
			ResultSet ret = ps.executeQuery();
			while (ret.next()) {
				int id = ret.getInt(1);
				String name = ret.getString(2);
				String email = ret.getString(3);
				Date birth = ret.getDate("birth");
				System.out.print("id = " + id + "name = " + name + " email = " + email + " birth = " + birth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, ps);
		}
	}
}

class JDBC {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		Properties prop = new Properties();
		prop.put("user", "root");

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", prop);
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement statement) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
