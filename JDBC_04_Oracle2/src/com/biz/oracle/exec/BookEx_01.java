package com.biz.oracle.exec;

import com.biz.oracle.persistentce.dao.BookDao;
import com.biz.oracle.persistentce.dao.BookDaoImp;
import com.biz.oracle.service.BookServiceV1;

public class BookEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BookDao bookDao = new BookDaoImp();
		BookServiceV1 bs = new BookServiceV1();
		
		bs.viewBookList();
		
		
		
		
		
		

	}

}
