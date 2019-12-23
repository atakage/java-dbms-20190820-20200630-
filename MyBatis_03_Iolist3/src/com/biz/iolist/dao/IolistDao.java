package com.biz.iolist.dao;

import java.util.List;

import com.biz.iolist.persistence.IolistDTO;

public interface IolistDao {

	public List<IolistDTO> selectAll();
	public IolistDTO findById(long io_seq);
	public int insert(IolistDTO iolistDTO);
	public int update(IolistDTO iolistDTO);
	public int delete(long io_seq);
	public List<IolistDTO> findByDcode(String io_dcode);
	public List<IolistDTO> findByPcode(String io_pcode);
	public IolistDTO findByIn(String io_pcode);
	public IolistDTO findByOut(String io_pcode);
	
}
