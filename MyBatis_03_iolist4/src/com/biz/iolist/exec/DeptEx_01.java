package com.biz.iolist.exec;

import com.biz.iolist.service.dept.DeptServiceV1;

public class DeptEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		DeptServiceV1 ds = new DeptServiceV1();
		
		//ds.viewAllList();
		
		//ds.viewNameList("두영산업");
	
		
		//ds.viewNameList();
		
		ds.viewCEOList();
		
		//	ds.viewNameAndCEO();
	}

}
