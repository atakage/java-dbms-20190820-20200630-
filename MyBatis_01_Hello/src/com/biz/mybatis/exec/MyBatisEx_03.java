package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDao;
import com.biz.mybatis.persistence.BookDTO;

public class MyBatisEx_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);		// JDBC의 다양한 클래스를 대신하여 Java 어플리케이션과 DBMS간의 연결을 대신 관리
		
		
		// session은 네트워크 환경에서 지점과 지점사이가 다양한 방법으로 연결되고 데이터를 주고받을 준비가 된 통로

		
		BookDao bookDao = sqlSession.getMapper(BookDao.class);
		
		BookDTO dto = BookDTO.builder().b_name("MyBatis").b_comp("경영원").b_writer("내멋으로").b_price(50000).build();
		
		bookDao.insert(dto);
		
		List<BookDTO> bookList = bookDao.selectAll();
		
		for(BookDTO dto1 : bookList) {
			System.out.println(dto1.toString());
		}
	}

}
