package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.utils.DBContract;

public abstract class ScoreService {

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
	
	public abstract int insert(ScoreDTO dto);
		
	
		
	
	public abstract List<ScoreDTO> selectAll();
	
	public abstract List<ScoreDTO> findByName(String name);
	
	public abstract ScoreDTO findById(long id);
	
	public abstract List<ScoreDTO> findBySubject(String subject);
	
	public abstract int update(ScoreDTO vo);
	
	public abstract int delete(long id);
	
	
	

}
