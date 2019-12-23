package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.domain.DeptDTO;
import com.biz.grade.persistence.domain.StudentDTO;

public abstract class DeptDao {
	
	protected Connection dbConn = null;
	
	
	public DeptDao() {
		// TODO Auto-generated constructor stub
		this.dbConn = DBConnection.getDBConnection();
	}
	
	public abstract List<DeptDTO> selectAll();
	public abstract DeptDTO findById(String d_num);
	public abstract List<DeptDTO> findByName(String d_name);
	//public abstract List<DeptDTO> findByGrade(int st_grade);
	public abstract void insert(DeptDTO deptDTO);
	public abstract void update(DeptDTO deptDTO);
	public abstract void delete(String d_num);
	
	

	
}
