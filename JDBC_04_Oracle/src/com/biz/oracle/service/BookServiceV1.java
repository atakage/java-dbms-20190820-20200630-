package com.biz.oracle.service;

import java.util.List;
import java.util.Scanner;

import com.biz.oracle.persistentce.BookDTO;
import com.biz.oracle.persistentce.dao.BookDao;
import com.biz.oracle.persistentce.dao.BookDaoImp;

public class BookServiceV1 {

	BookDao bookDao = null;
	Scanner scan = null;

	public BookServiceV1() {
		// TODO Auto-generated constructor stub
		scan = new Scanner(System.in); // 초기화된 클래스, 인스턴스화 되었음
		bookDao = new BookDaoImp();
	}

	public void viewBookList() { // 도서 정보 전체리스트 DB로부터 읽어 console에 보이기

		List<BookDTO> bookList = bookDao.selectAll();

		this.viewList(bookList);
	}

	private void viewList(List<BookDTO> bookList) {

		System.out.println("==========================");
		System.out.println("전체 도서 리스트 V1");
		System.out.println("==========================");

		System.out.println("코드\t도서명\t출판사\t저자\t가격");

		System.out.println("==================================");
		for (BookDTO dto : bookList) {
			System.out.printf("%s\t", dto.getB_code());
			System.out.printf("%s\t", dto.getB_name());
			System.out.printf("%s\t", dto.getB_comp());
			System.out.printf("%s\t", dto.getB_writer());
			System.out.printf("%d\n", dto.getB_price());
		}

		System.out.println("===============================");

	}

	public void searchBookName(boolean bCon) {

		while (true) {

			if(this.searchBookName()) break;
		}

	}

	public boolean searchBookName() {

		System.out.println("============================");
		System.out.println("도서 검색");
		System.out.print("도서명(-Q:quit)>> ");
		String strName = scan.nextLine();

		if (strName.equalsIgnoreCase("-q"))
			return true;

		List<BookDTO> bookList = bookDao.findByName(strName);

		if (bookList == null || bookList.size() < 1) {

			System.out.println("찾는 도서명이 없음");
			return false;
		}

		this.viewList(bookList);
		return true;

	}

	public void searchBookPrice() {

		// TODO Auto-generated method stub

		while (true) {
			System.out.println("=====================");
			System.out.println("도서 가격 검색");
			System.out.println("====================");

			try {

				System.out.print("최저가(-q:quit)>> ");
				String sprice = scan.nextLine();
				if (sprice.equalsIgnoreCase("-q"))
					break;
				int intSprice = Integer.valueOf(sprice);

				System.out.print("최고가(-q:quit)>> ");
				String eprice = scan.nextLine();
				if (sprice.equalsIgnoreCase("-q"))
					break;
				int intEprice = Integer.valueOf(eprice);

				List<BookDTO> bookList = bookDao.findByPrice(intSprice, intEprice);

				this.viewList(bookList);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격은 숫자로만 입력");
				continue;
			}
		}

	}
}
