package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreInput_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScoreServiceV2 sc = new ScoreServiceV2Ext();

		Scanner scan = new Scanner(System.in);

		String strStNum = null;
		String strSbNum = null;
		String strScore = null;

		while (true) {

			System.out.print("학번 (-Q:quit) >> ");
			strStNum = scan.nextLine();

			if (strStNum.equalsIgnoreCase("-q")) {

				break;

			}

			List<ScoreVO> scoreList = sc.findByStNum(strStNum);

			if (scoreList == null || scoreList.size() < 1) {
				System.out.println("학생 정보에 학번이 없음");
				continue;
			}

			for (ScoreVO vo : scoreList) {

				System.out.println(vo.toString());
			}
			
			System.out.println("성적 입력 종료");
			break;

		}
		
		if(strStNum.equalsIgnoreCase("-q")) return ;

		while (true) {

			System.out.print("과목코드 (-Q:quit) >> ");
			strSbNum = scan.nextLine();

			if (strSbNum.equalsIgnoreCase("-q")) {
				System.out.println("성적 입력 종료");
				break;

			}
			
			List<ScoreVO> scoreList = sc.findBySubject(strSbNum);
			
			
			if(scoreList == null || scoreList.size() < 1) {
				System.out.println("과목코드가 없음");
				continue;
			}

			for(ScoreVO vo : scoreList) {
				
				System.out.println(vo.toString());
			}
			break;
		}
		
		if(strSbNum.equalsIgnoreCase("-q")) return;

		while (true) {

			System.out.print("점수 (-Q:quit) >> ");
			strScore = scan.nextLine();

			if (strScore.equalsIgnoreCase("-q")) {
				
				System.out.println("성적 입력 종료");

				break;

			}
			
			
			try {
				
				int intScore = Integer.valueOf(strScore);
				if(intScore < 0 || intScore > 100) {
					System.out.println("점수는 0이상 100이하");
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("성적은 숫자로만 입력");
				continue;
			}
			
			break;

		}
		
		if(strScore.equalsIgnoreCase("-q")) return;

		ScoreDTO dto = ScoreDTO.builder().s_id(0).s_std(strStNum).s_subject(strSbNum).s_score(Integer.valueOf(strScore)).build();

		
		int ret = sc.insert(dto);
		if(ret > 0) {
			System.out.println("데이터 추가 성공");
		}else {
			System.out.println("데이터 추가 실패");
		}
		//sc.insert(scoreDTO);

	}

}
