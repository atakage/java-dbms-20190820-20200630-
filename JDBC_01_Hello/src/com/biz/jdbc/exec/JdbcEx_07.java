package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCService3;

public class JdbcEx_07 {
	
	public static void main(String[] args) {
		
		ScoreJDBCService3 sc3 = new ScoreJDBCService3();
		
		
		ScoreVO vo = ScoreVO.builder().s_id(601).s_std("이몽룡").s_score(100).s_rem("연습").build();
		
		int ret = sc3.insert(vo);
		System.out.println(ret);
		
		
		List<ScoreVO> scList = sc3.findById(601);
		
		for(ScoreVO vo2 : scList) {
			
			System.out.println(vo2.toString());
		}
	}

}
