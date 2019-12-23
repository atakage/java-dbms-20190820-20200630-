package com.biz.grade.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.StudentDao;
import com.biz.grade.persistence.domain.StudentDTO;

public class StudentDaoImp extends StudentDao{

	@Override
	public List<StudentDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO findById(String st_num) {
		
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_STUDENT + " WHERE st_num = ? ";
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, st_num.toUpperCase());
			ResultSet rs = ps.executeQuery();
			
			StudentDTO dto = null;
			
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
	
	private StudentDTO rs2DTO(ResultSet rs) throws SQLException {
		
	return	 StudentDTO.builder().st_addr(rs.getString("st_addr")).st_dept(rs.getString("st_dept")).st_grade(rs.getInt("st_grade"))
				.st_name(rs.getString("st_name")).st_num(rs.getString("st_num")).st_tel(rs.getString("st_tel")).build();
		
		
	}

	@Override
	public List<StudentDTO> findByName(String st_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDTO> findByGrade(int st_grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(StudentDTO stdDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StudentDTO stdDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String st_num) {
		// TODO Auto-generated method stub
		
	}

}
