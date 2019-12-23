package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.utils.DBContract;

public abstract class StudentService {

	protected Connection dbConn = null;

	protected void dbConnection() {

		try {
			Class.forName(DBContract.DBConn.jdbcDriver);
			dbConn = DriverManager.getConnection(DBContract.DBConn.URL, DBContract.DBConn.userName,
					DBContract.DBConn.password);

			System.out.println("DBConnection OK");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC 드라이버를 찾지 못함");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DBMS 접속 오류");
		
		}

	}
	
	public abstract int insert();
		
	
		
	
	public abstract List<StudentDTO> selectAll();
	
	public abstract List<StudentDTO> findByName(String name);
	
	public abstract StudentDTO findById(String id);
	
	public abstract List<StudentDTO> findBySubject(String subject);
	
	public abstract int update(StudentDTO dto);
	
	public abstract int delete(long id);
	
	
	

}
