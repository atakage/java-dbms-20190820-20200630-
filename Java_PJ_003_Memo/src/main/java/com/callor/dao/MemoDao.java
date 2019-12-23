package com.callor.dao;

import java.util.List;

import com.callor.persistence.MemoDTO;

public interface MemoDao {

	
	public List<MemoDTO> selectAll();
	
	
}
