package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCService2;

public class jdbcEx_05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ScoreJDBCService2 sc2 = new ScoreJDBCService2();
		
	
	
		List<ScoreVO >stdList = sc2.findByName("'갈한수' OR 1= 1");
		
		
		
		for(ScoreVO vo : stdList) {
			
			System.out.println(vo.toString());	
			
		}
		

	}

}
