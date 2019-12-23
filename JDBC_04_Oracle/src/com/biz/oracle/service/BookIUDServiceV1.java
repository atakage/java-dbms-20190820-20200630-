package com.biz.oracle.service;

import java.util.Scanner;

import com.biz.oracle.persistentce.BookDTO;
import com.biz.oracle.persistentce.dao.BookDao;
import com.biz.oracle.persistentce.dao.BookDaoImp;

public class BookIUDServiceV1 {

	private BookDao bookDao = null;
	private Scanner scanner = null;

	public BookIUDServiceV1() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
		bookDao = new BookDaoImp();
	}

	public void inputBook() {

		while (true) {
			System.out.println("=======================");
			System.out.println("도서정보 등록");

			String strB_name = null;

			while (true) {

				System.out.print("1. 도서명(-q:quit)>> ");
				strB_name = scanner.nextLine();

				if (strB_name.equalsIgnoreCase("-q"))
					break;
				if(strB_name.isEmpty()) {
					System.out.println("도서명은 반드시 입력해야 함");
					continue;
				}

				break;

			}
			if(strB_name.equalsIgnoreCase("-q"))break;
			
			
			
			

			System.out.print("2. 출판사(-q:quit)>> ");
			String strB_comp = scanner.nextLine();
			if (strB_comp.equalsIgnoreCase("-q"))
				break;

			System.out.print("3. 저자(-q:quit)>> ");
			String strB_writer = scanner.nextLine();
			if (strB_writer.equalsIgnoreCase("-q"))
				break;

			String strB_price = null;
			int intB_price = 0;
			while (true) {

				try {

					System.out.print("4. 가격(-q:quit)>> ");
					strB_price = scanner.nextLine();
					intB_price = Integer.valueOf(strB_price);
					if (strB_price.equalsIgnoreCase("-q"))
						break;

				} catch (Exception e) {
					// TODO: handle exception

					System.out.println("가격은 숫자로만 입력");
					continue;
				}

				break;

			}
			
			if(strB_price.equalsIgnoreCase("-q"))break;
			
			
			BookDTO bookDTO = BookDTO.builder().b_comp(strB_comp).b_name(strB_name).b_price(intB_price).b_writer(strB_writer).build();
					
					
					
					
				int ret	= bookDao.insert(bookDTO);
			if(ret > 0) {
				System.out.println("도서 정보 저장 완료");
			}else {
				System.out.println("도서 정보 저장 실패");
			}
	

		}
	}

	public void deleteBook() {
		// TODO Auto-generated method stub
		
		while(true) {
			System.out.println("===================================");
			System.out.print("삭제할 코드(-q:quit)>> ");
			String strCode = scanner.nextLine();
			
			if(strCode.equalsIgnoreCase("-q")) break;
			
			
			BookDTO dto  = bookDao.findById(strCode);

			if(dto == null) {
				System.out.println("도서 코드가 없음");
				continue;
			}
			int ret = bookDao.delete(strCode);
			
			if(ret > 0) {
				System.out.println("삭제 완료");
			}else {
				System.out.println("삭제 실패");
			}
		
		}
		
	}

}
