package com.biz.addr.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.addr.persistence.AddrDTO;

public abstract class AddrDao {
	
	protected Connection dbConn = null;
	
	
	public AddrDao() {
		this.dbConn = DBConnection.getDBConnection();
	}
	
	public abstract List<AddrDTO> selectAll();
	public abstract AddrDTO findById(long id);
	public abstract List<AddrDTO> findByName(String name);
	public abstract AddrDTO findByTel(String tel);
	public abstract List<AddrDTO> findByChain(String chain);
	public abstract int insert(AddrDTO dto);
	public abstract int update(AddrDTO dto);
	public abstract int delete(int id);
}
