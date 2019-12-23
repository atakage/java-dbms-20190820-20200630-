package com.biz.grade.service.extend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;

public class ScoreServiceV2Ext extends ScoreServiceV2 {

	@Override
	public List<ScoreVO> selectAll() {

		PreparedStatement ps = null;

		String sql = DBContract.SQL.SELECT_VIEW_SCORE;

		try {
			ps = dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<ScoreVO> scoreList = new ArrayList<ScoreVO>();

			while (rs.next()) {

				scoreList.add(this.rsTOScoreVO(rs));

			}

			rs.close();
			ps.close();

			return scoreList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private ScoreVO rsTOScoreVO(ResultSet rs) throws SQLException {

		// "SELECT S_STD, ST_NAME, ST_DEPT, ST_GRADE, D_NAME, D_TEL, S_SUBJECT, SB_NAME,
		// S_SCORE, S_ID FROM view_score";
		ScoreVO vo = ScoreVO.builder().s_id(rs.getLong("S_ID")).s_score(rs.getInt("S_SCORE"))
				.s_std(rs.getString("S_STD")).s_subject(rs.getString("S_SUBJECT")).sb_name(rs.getString("SB_NAME"))
				.st_dept(rs.getString("ST_DEPT")).st_grade(rs.getInt("ST_GRADE")).st_name(rs.getString("ST_NAME"))
				.d_name(rs.getString("D_NAME")).d_tel(rs.getString("D_TEL")).build();

		return vo;

	}

	@Override
	public ScoreVO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ScoreDTO scoreDTO) {

		PreparedStatement ps = null;
		String sql = "INSERT INTO tbl_score (S_ID," + "S_SCORE," + "S_REMARK," + "S_SUBJECT,"
				+ "S_STD) VALUES(?,?,?,?,?)";

		try {
			ps = dbConn.prepareCall(sql);
			ps.setLong(1, scoreDTO.getS_id());
			ps.setInt(2, scoreDTO.getS_score());
			ps.setString(3, scoreDTO.getS_remark());
			ps.setString(4, scoreDTO.getS_subject());
			ps.setString(5, scoreDTO.getS_std());

			int ret = ps.executeUpdate(); // 쿼리가 정상 수행되면 0보다 큰값 리턴

			ps.close();

			return ret;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int update(ScoreDTO scoreDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

		String sql = " DELETE FROM tbl_score WHERE s_id = ? ";

		try {
			ps = dbConn.prepareCall(sql);
			ps.setLong(1, id);
			int ret = ps.executeUpdate();
			
			ps.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ScoreVO> findByStName(String stName) {

		PreparedStatement ps = null;

		String sql = DBContract.SQL.SELECT_VIEW_SCORE + " WHERE st_name = ?";

		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, stName);
			ResultSet rs = ps.executeQuery();

			List<ScoreVO> scoreList = new ArrayList<ScoreVO>();
			while (rs.next()) {

				scoreList.add(this.rsTOScoreVO(rs));

			}

			rs.close();
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
	public List<ScoreVO> findByStNum(String strStNum) {
		// TODO Auto-generated method stub

		PreparedStatement ps = null;

		String sql = DBContract.SQL.SELECT_VIEW_SCORE + " WHERE s_std = ? ";

		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, strStNum);
			ResultSet rs = ps.executeQuery();

			List<ScoreVO> scoreList = new ArrayList<ScoreVO>();

			while (rs.next()) { // if

				scoreList.add(this.rsTOScoreVO(rs));

			}

			rs.close();
			ps.close();

			return scoreList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ScoreVO> findBySubject(String strSbNum) {

		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE + " WHERE s_subject = ? ";

		try {
			ps = dbConn.prepareCall(sql);
			ps.setString(1, strSbNum);
			ResultSet rs = ps.executeQuery();

			List<ScoreVO> scoreList = new ArrayList<ScoreVO>();
			while (rs.next()) {

				scoreList.add(this.rsTOScoreVO(rs));

			}
			rs.close();
			ps.close();

			return scoreList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

}
