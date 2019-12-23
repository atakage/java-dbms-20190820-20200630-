package com.biz.oracle.persistentce.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.oracle.config.DBConnection;
import com.biz.oracle.persistentce.BookDTO;

/*
 * 
 * 추상클래스 선언
 * CRUD 구현
 */
public abstract class BookDao {
	
	protected Connection dbConn = null;
	
	public BookDao() {
		// TODO Auto-generated constructor stub
		
		this.dbConn = DBConnection.getDBConnection();
		
	}

	/*
	 * B_CODE B_NAME B_COMP B_WIRTER B_PRICE
	 */

	public abstract List<BookDTO> selectAll();

	public abstract BookDTO findById(String b_code);

	public abstract List<BookDTO> findByName(String b_name);

	public abstract List<BookDTO> findByComp(String b_comp);

	public abstract List<BookDTO> findByWriter(String b_writer);

	public abstract List<BookDTO> findByPrice(int price);

	public abstract List<BookDTO> findByPrice(int sprice, int eprice);

	public abstract int insert(BookDTO bookDTO);

	public abstract int update(BookDTO bookDTO);

	public abstract int delete(String b_code);

}
