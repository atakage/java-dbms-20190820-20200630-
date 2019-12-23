package com.biz.oracle.exec;

import com.biz.oracle.service.BookIUDServiceV1;
import com.biz.oracle.service.BookServiceV1;

public class BookUpdateEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		BookServiceV1 bs = new BookServiceV1();
		BookIUDServiceV1 bi = new BookIUDServiceV1();
		
		String strName = bs.searchBookName();
		if(strName.equalsIgnoreCase("-q")) {
			System.out.println("도서 정보 변경 업무 종료");
		}else {
		bi.updateBook();
		
		bs.searchBookName(strName);
		}
		

	}

}
