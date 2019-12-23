package com.biz.grade.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.utils.DBContract;

public class ScoreServiceV1 extends ScoreService {

	@Override
	public int insert(ScoreDTO dto) {
		// TODO Auto-generated method stub
		
		this.dbConnection();
		PreparedStatement ps = null;
		
		
		String sql = " INSERT INTO tbl_score( s_id, s_score, s_remark, s_subject, s_std) VALUES(?,?,?,?,?) ";
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setLong(1, dto.getS_id());
			ps.setInt(2, dto.getS_score());
			ps.setString(3, dto.getS_remark());
			ps.setString(4, dto.getS_subject());
			ps.setString(5, dto.getS_std());
			
			int ret = ps.executeUpdate();
			dbConn.close();
			
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
				
				
		return 0;
	}

	@Override
	public List<ScoreDTO> selectAll() {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement ps = null;

		try {
			ps = dbConn.prepareStatement(DBContract.SQL.SCORE_SELECT);
			ResultSet rs = ps.executeQuery();

			List<ScoreDTO> scoreList = new ArrayList<ScoreDTO>();

			while (rs.next()) { // ResultSet으로부터 데이터를 getter할 때 컬럼의 위칫값(숫자)으로 사용하던 것을 컬럼의 이름으로 설정

				scoreList.add(ScoreDTO.builder().s_id(rs.getLong("s_id")).s_std(rs.getString("s_std"))
						.s_subject(rs.getString("s_subject")).s_score(rs.getInt("s_score"))
						.s_remark(rs.getString("s_remark")).build());

			}

			rs.close();
			dbConn.close();
			ps.close();

			return scoreList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public ScoreDTO findById(long id) {
		// TODO Auto-generated method stub

		this.dbConnection();

		PreparedStatement ps = null;

		String sql = DBContract.SQL.SCORE_SELECT + " WHERE s_id = ? ";

		try {
			ps = dbConn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			ScoreDTO dto = null;
			if (rs.next()) {

				dto = ScoreDTO.builder().s_id(rs.getLong("s_id")).s_std(rs.getString("s_std"))
						.s_score(rs.getInt("s_score")).s_subject(rs.getString("s_subject"))
						.s_remark(rs.getString("s_remark")).build();

			}
			rs.close();
			dbConn.close();
			ps.close();
			
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int update(ScoreDTO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreDTO> findByName(String name) {
		
		this.dbConnection();
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SCORE_SELECT + " WHERE s_std = ? ";
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1,  name);
			ResultSet rs = ps.executeQuery();
			
			List<ScoreDTO> scoreList = new ArrayList<ScoreDTO>();
			while(rs.next()) {
				
			  scoreList.add( ScoreDTO.builder().s_id(rs.getLong("s_id")).s_std(rs.getString("s_std")).s_subject(rs.getString("s_subject")).s_score(rs.getInt("s_score")).s_remark(rs.getString("s_remark")).build());
				
			}
			
			rs.close();
			dbConn.close();
			ps.close();
			
			return scoreList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreDTO> findBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
