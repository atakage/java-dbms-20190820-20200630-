package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

public class jdbcEx_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "grade";
		String password = "grade";
		Connection dbConn = null;
		PreparedStatement ps = null;
		
		List<ScoreVO> scoreList = null;
		
		try {
			Class.forName(jdbcDriver);
			
			
			dbConn = DriverManager.getConnection(url, userName, password);
			
			String sql = "SELECT * FROM tbl_score";
			ps = dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			scoreList = new ArrayList<ScoreVO>();
			
			
			
			while(rs.next()) {
				
				ScoreVO vo = new ScoreVO();
				
				vo.setS_id(rs.getInt(1));
				vo.setS_std(rs.getString(2));
				vo.setS_score(rs.getInt(3));
				
				scoreList.add(vo);
				
				
				
				
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
		
		System.out.println("===============================");
		System.out.println("성적일람표");
		System.out.println("===============================");
		System.out.println("ID\t학생\t점수\t비고");
		System.out.println("-------------------------------");
		for(ScoreVO vo : scoreList) {
			
			System.out.print(vo.getS_id()+"\t");
			System.out.print(vo.getS_std()+"\t");
			System.out.print(vo.getS_score()+"\t");
			System.out.print(vo.getS_rem()+"\n");
		}

	}

}
