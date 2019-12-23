package com.biz.mybatis.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDao;
import com.biz.mybatis.persistence.BookDTO;

public class BookServiceV1 {

	SqlSession sqlSession = null;
	Scanner scan = null;

	public BookServiceV1() {

		this.sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scan = new Scanner(System.in);
	}

	public void searchName() {

		while (true) {

			System.out.println("============================");
			System.out.println("도서검색 v2");
			System.out.println("============================");
			System.out.print("도서명(q:quit)>> ");
			String strName = scan.nextLine();

			if (strName.equalsIgnoreCase("q"))
				break;

			BookDao dao = sqlSession.getMapper(BookDao.class);
			List<BookDTO> bookList = dao.findByName(strName);

			
			for (BookDTO dto : bookList) {
				System.out.println(dto.toString());
			}
		}
	}

}
