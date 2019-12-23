package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

public class ScoreJDBCService3 {

	protected Connection dbConn = null;
	protected PreparedStatement ps = null;

	protected List<ScoreVO> scoreList = null;

	public List<ScoreVO> getScoreList() {

		return this.scoreList;

	}

	protected void dbConnection() {

		try {
			Class.forName(DBConstract.DB_INFO.jdbcDriver);

			dbConn = DriverManager.getConnection(DBConstract.DB_INFO.URL, DBConstract.DB_INFO.userName,
					DBConstract.DB_INFO.password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<ScoreVO> selectAll() {

		String sql = "SELECT * FROM tbl_score";
		this.dbConnection();
		try {
			ps = dbConn.prepareStatement(sql);
			this.setScoreList(ps);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.scoreList;

	}

	public List<ScoreVO> findById(int s_id) {

		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_id =  ? ";

		this.dbConnection();
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setInt(1, s_id);

			this.setScoreList(ps);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.scoreList;

	}

	public List<ScoreVO> findById(int s_id, int e_id) {

		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_id BETWWEEN ? AND ? ";

		this.dbConnection();
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setInt(1, s_id);
			ps.setInt(2, e_id);

			this.setScoreList(ps);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.scoreList;

	}

	public List<ScoreVO> findByName(String s_name) {

		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_std = ?";

		this.dbConnection();
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, s_name);
			this.setScoreList(ps);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.scoreList;

	}

	protected void setScoreList(PreparedStatement ps) throws SQLException {

		this.scoreList = new ArrayList<ScoreVO>();
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			ScoreVO vo = ScoreVO.builder().s_id(rs.getInt(DBConstract.SCORE.S_ID))
					.s_std(rs.getString(DBConstract.SCORE.S_STD)).s_score(rs.getInt(DBConstract.SCORE.S_SCORE))
					.s_rem(rs.getString(DBConstract.SCORE.S_REM)).build();
			scoreList.add(vo);

		}
		rs.close();
	}

	public int insert(ScoreVO vo) {
		
		String sql = " INSERT INTO tbl_score ( ";
		sql += " s_id, ";
		sql += " s_std, ";
		sql += " s_subject, ";
		sql += " s_score, ";
		sql += " s_remark ";
		sql += " ) VALUES(?,?,001,?,?) ";
		this.dbConnection();
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setInt(1, vo.getS_id());
			ps.setString(2, vo.getS_std());
			ps.setInt(3, vo.getS_score());
			ps.setString(4, vo.getS_rem());
			
			int ret = ps.executeUpdate();
			
			
			
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}

}
