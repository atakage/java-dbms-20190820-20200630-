package com.biz.grade.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

	private static Connection dbConn = null;
	

	static {			// static 생성자, 프로젝트가 시작됨과 동시에 JVM에 의해 자동으로 실행되는 전역 생성자 method(클래스와 무관)
		

		String jdbcDriver = DBContract.DBConn.jdbcDriver;
		String url = DBContract.DBConn.URL;
		String user = DBContract.DBConn.USER;
		String password = DBContract.DBConn.PASSWORD;

		try {
			Class.forName(jdbcDriver);
			dbConn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Connection getDBConnection() {		// dbConn 변수를 직접 접근하지 않고 getter method를 호출해 가져감, singleton 기법과 유사
		return dbConn;
	}

}
