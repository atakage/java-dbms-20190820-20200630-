package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

public class ScoreJDBCService {
	
	/*
	protected String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	protected String url ="jdbc:oracle:thin:@localhost:1521:xe";
	protected String userName = "grade";
	protected String password = "grade";
	
	*/
	
	protected Connection dbConn = null;
	protected PreparedStatement ps = null;
	
	protected List<ScoreVO> scoreList = null;
	
	public List<ScoreVO> getScoreList(){
		
		return this.scoreList;
		
	}
	
	protected void dbConnection() {
		
		try {
			Class.forName(DBConstract.DB_INFO.jdbcDriver);
			
			dbConn = DriverManager.getConnection(DBConstract.DB_INFO.URL, DBConstract.DB_INFO.userName, DBConstract.DB_INFO.password);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void selectAll() {
		
		this.dbConnection();
		
		this.scoreList = new ArrayList<ScoreVO>();
		
		String sql = "SELECT * FROM tbl_score ";
		try {
			ps = dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ScoreVO vo = ScoreVO.builder().s_id(rs.getInt(DBConstract.SCORE.S_ID)).s_std(rs.getString(DBConstract.SCORE.S_STD))
						.s_score(rs.getInt(DBConstract.SCORE.S_SCORE))
						.s_rem(rs.getString(DBConstract.SCORE.S_REM))
						.build();
				scoreList.add(vo);
				
				
				
			}
			
			rs.close();
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
