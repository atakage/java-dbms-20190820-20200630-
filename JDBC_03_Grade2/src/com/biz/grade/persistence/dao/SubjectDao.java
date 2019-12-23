package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.persistence.domain.StudentDTO;
import com.biz.grade.persistence.domain.SubjectDTO;

public abstract class SubjectDao {
	
protected Connection dbConn = null;
	
	
	public SubjectDao() {
		// TODO Auto-generated constructor stub
		this.dbConn = DBConnection.getDBConnection();
	}
	
	public abstract List<SubjectDTO> selectAll();
	public abstract SubjectDTO findById(String sb_num);
	public abstract List<SubjectDTO> findByName(String sb_name);
	public abstract List<SubjectDTO> findByPro(int sb_pro);
	public abstract void insert(SubjectDTO sbjDTO);
	public abstract void update(SubjectDTO sbjDTO);
	public abstract void delete(String sb_code);
	
	

	
}
