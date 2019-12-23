package com.biz.grade.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.DeptDao;
import com.biz.grade.persistence.domain.DeptDTO;

public class DeptDaoImp extends DeptDao {

	@Override
	public List<DeptDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeptDTO findById(String d_num) {
		// TODO Auto-generated method stub
		
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_DEPT + " WHERE d_num = ? "; 
		
		
		return null;
	}

	@Override
	public List<DeptDTO> findByName(String d_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DeptDTO deptDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DeptDTO deptDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String d_num) {
		// TODO Auto-generated method stub

	}

}
