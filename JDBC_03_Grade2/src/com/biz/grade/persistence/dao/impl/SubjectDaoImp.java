package com.biz.grade.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.SubjectDao;
import com.biz.grade.persistence.domain.SubjectDTO;

public class SubjectDaoImp extends SubjectDao {

	
	
	@Override
	public List<SubjectDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectDTO findById(String sb_num) {
		// TODO Auto-generated method stub
		
		
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_SUBJECT + " WHERE sb_code = ? ";
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, sb_num.toUpperCase());
			ResultSet rs = ps.executeQuery();
			
			SubjectDTO dto = null;
			
			if(rs.next()) {
			
				 dto = this.rs2DTO(rs);
				
			}
			
			rs.close();
			ps.close();
			
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
		
		
	}
	
	private SubjectDTO rs2DTO(ResultSet rs) throws SQLException {
		
		
		return SubjectDTO.builder().SB_CODE(rs.getString("sb_code")).SB_NAME(rs.getString("sb_name")).SB_PRO(rs.getString("sb_pro")).build();
	}

	@Override
	public List<SubjectDTO> findByName(String sb_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubjectDTO> findByPro(int sb_pro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(SubjectDTO sbjDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SubjectDTO sbjDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String sb_code) {
		// TODO Auto-generated method stub

	}

}
