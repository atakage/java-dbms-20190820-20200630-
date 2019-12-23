package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ScoreServiceV2 ss = new ScoreServiceV2Ext();
		
		
		List<ScoreVO> scoreList = ss.selectAll();
		
		
		if(scoreList == null || scoreList.size() < 1) {
			System.out.println("데이터가 없음");
		}
		
		for(ScoreVO vo : scoreList) {
			
			System.out.println(vo.toString());
			
		}
	}

}
