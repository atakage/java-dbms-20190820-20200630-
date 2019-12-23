package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCService3;

public class JdbcEx_06 {
	
	public static void main(String[] args) {
		
		ScoreJDBCService3 sc3 = new ScoreJDBCService3();
		
		
		List<ScoreVO> scoreList = sc3.findById(30);
		
		
		for(ScoreVO vo : scoreList) {
			
			System.out.println(vo.toString());
			
		}
		
		
	}

}
