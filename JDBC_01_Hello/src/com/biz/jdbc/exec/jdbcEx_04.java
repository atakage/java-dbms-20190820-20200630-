package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCService2;

public class jdbcEx_04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ScoreJDBCService2 sc2 = new ScoreJDBCService2();
		
	
		List<ScoreVO> stdList = sc2.selectAll();
	
	
		
		
		for(ScoreVO vo : stdList) {
			
			System.out.println(vo.toString());	
			
		}
		

	}

}
