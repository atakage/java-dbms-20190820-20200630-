package com.biz.oracle.exec;

import com.biz.oracle.service.BookIUDServiceV1;
import com.biz.oracle.service.BookServiceV1;

public class BookDeleteEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		BookServiceV1 bs = new BookServiceV1();
		BookIUDServiceV1 bi = new BookIUDServiceV1();
		
		bs.viewBookList();
		bi.deleteBook();

	}

}
