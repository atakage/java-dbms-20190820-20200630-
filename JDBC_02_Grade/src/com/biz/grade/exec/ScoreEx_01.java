package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.service.ScoreServiceV1;

public class ScoreEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ScoreServiceV1 ss = new ScoreServiceV1();
		
		
		List<ScoreDTO> scoreList = ss.selectAll();
		
		
		if(scoreList == null) {
			System.out.println("성적데이터를 읽을 수 없음");
			return;
		}
		
		
		for(ScoreDTO dto : scoreList) {
			
			
			System.out.println(dto.toString());
			
		}
		
		

	}

}
