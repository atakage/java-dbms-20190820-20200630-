package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV3;

public class ScoreUpdateEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		ScoreServiceV3 ss = new ScoreServiceV3();
		ScoreDTO scDTO = null;
		
		
		List<ScoreVO> scList = ss.updateStudent();
		if(scList == null) {
			System.out.println("성적 입력 종료");
			return;
		}

		
		
		for(ScoreVO vo : scList) {
			System.out.print(vo.getS_id()+"\t");
			System.out.print(vo.getSb_name()+"\t");
			System.out.println(vo.getS_score());
		}
		
		
		ScoreVO vo = ss.selectScore();
		

		
		if(vo == null) {
			System.out.println("성적 입력 종료");
			return;	// 프로젝트 종료
		}
		
		System.out.println(vo.toString());
		
		int ret = ss.updateScore(vo);
		
		if(ret > 0) {
			System.out.println("데이터 변경 성공");
		}else {
			System.out.println("데이터 변경 실패");
		}
		
		
	
		
		
		
		

	}

}
