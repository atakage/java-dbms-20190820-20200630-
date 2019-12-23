package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.println("Hello Oracle");
		
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "grade";
		String password = "grade";
		
		
		
		try {
			
			
			
			Class.forName(jdbcDriver);				// jdbcDriver(oracle.jdbc.driver.OracleDriver.class 파일)을 사용할 수 있도록
													// JVM에 등록(Loading)하라는 지시
			
			
			Connection dbConn = null;
			dbConn = DriverManager.getConnection(url, userName, password);			// DBMS와 SQL을 주고받을 통로 설정
			
			
			System.out.println("Connection OK");
			
			
			PreparedStatement ps = null;
			
			
			String sql = "SELECT * FROM tbl_score";
			ps = dbConn.prepareStatement(sql);		// 연결된 통로를 통해 SQL문을 전달
			
			ResultSet rs  = ps.executeQuery();		// 이 ResultSet 은 일종의 iterator처럼 취급
			
			while(rs.next()) {								// rs.next() 실행될 때마다 데이터 리스트의 앞쪽부터 한 개씩 꺼내 읽을 수 있도록 준비
				System.out.print(rs.getInt(1) + "\t");	//마지막 리스트를 모두 읽으 후 rs.next()가 실행되면 false return
				System.out.print(rs.getString(2) + "\t");		// 숫자 get() method로 읽을 수 있음
				System.out.print(rs.getString(3) + "\n");
			}
			rs.close();
			dbConn.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
