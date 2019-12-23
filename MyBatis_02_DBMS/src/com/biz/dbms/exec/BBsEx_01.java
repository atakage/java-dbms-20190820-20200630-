package com.biz.dbms.exec;

import com.biz.dbms.service.BBsServiceV1;

public class BBsEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		BBsServiceV1 bs = new BBsServiceV1();
		
		
		
		bs.writeBBS();
		System.out.println("게시판 입력 종료");
		bs.viewBBsList();
		

	}

}
