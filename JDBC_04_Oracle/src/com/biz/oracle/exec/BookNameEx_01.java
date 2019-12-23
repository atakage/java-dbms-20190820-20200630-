package com.biz.oracle.exec;

import com.biz.oracle.service.BookServiceV1;

public class BookNameEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		BookServiceV1 bs = new BookServiceV1();
		
		
		bs.searchBookName(true);
		System.out.println("도서 검색 종료");

	}

}
