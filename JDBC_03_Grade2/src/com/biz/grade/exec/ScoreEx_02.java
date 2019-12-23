package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreEx_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ScoreServiceV2 ss = new ScoreServiceV2Ext();
		
		
		Scanner scan = new Scanner(System.in);
		
		
		
		
		while(true) {
			
			
			System.out.println("=================");
			System.out.println("이름으로 성적 검색");
			System.out.println("================");
			System.out.println("(-Q:quit) >> ");
			String str = scan.nextLine();
			
			
			if(str.equalsIgnoreCase("-q")) {
				System.out.println("종료");
				break;
			}
			
			List<ScoreVO> scoreList	= ss.findByStName(str);
			
			
			if(scoreList == null || scoreList.size() < 1) {
				System.out.println("데이터가 없음");
				continue;
			}
			
			
			for(ScoreVO vo : scoreList) {
				
				System.out.println(vo.toString());
				
			}
			
			
		}
		

	}

}
