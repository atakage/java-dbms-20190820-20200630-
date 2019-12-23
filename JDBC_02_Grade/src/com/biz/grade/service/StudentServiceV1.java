package com.biz.grade.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.utils.DBContract;

public class StudentServiceV1 extends StudentService {

	@Override
	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDTO> findByName(String name) {
		// TODO Auto-generated method stub

		this.dbConnection();

		PreparedStatement ps = null;

		String sql = DBContract.SQL.STUDENT_SELECT + " WHERE st_name = ? ";

		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			List<StudentDTO> studentList = new ArrayList<StudentDTO>();
			while (rs.next()) {

				studentList.add(this.rsDTO(rs));

			}

			rs.close();
			dbConn.close();
			ps.close();

			return studentList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public StudentDTO findById(String num) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement ps = null;
		
		String sql = DBContract.SQL.STUDENT_SELECT + "WHERE st_num = ?";
		
		try {
			ps =dbConn.prepareStatement(sql);
			ps.setString(1, num);
			ResultSet rs = ps.executeQuery();
			
			StudentDTO dto = null;
			if(rs.next()) {
				
				 dto = this.rsDTO(rs);
				
			}
			
			rs.close();
			dbConn.close();
			return dto;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<StudentDTO> findBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(StudentDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	private StudentDTO rsDTO(ResultSet rs) {
		
	
		
	
		
			
			try {
				return   StudentDTO.builder().st_num(rs.getString("st_num")).st_name(rs.getString("st_name"))
				.st_addr(rs.getString("st_addr")).st_grade(rs.getInt("st_grade")).st_tel(rs.getString("st_tel"))
				.build();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
		
	}

}
