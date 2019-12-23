package com.biz.grade.exec;

import java.util.Random;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV1;
import com.biz.grade.service.StudentService;
import com.biz.grade.service.StudentServiceV1;

import oracle.net.aso.s;

public class ScoreEx_04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StudentService st = new StudentServiceV1();
		
		ScoreService sc = new ScoreServiceV1();
		
		Scanner scan = new Scanner(System.in);
		
		
		
		
		while(true) {
			
			System.out.println("=========================");
			System.out.println("성적 입력");
			System.out.println("========================");
			System.out.println("학번 >> ");
			String strNum = scan.nextLine();
			
			
			StudentDTO dto = st.findById(strNum);
			
			if(dto == null) {
				
				System.out.println("학생 정보에 학번 없음");
				System.out.println("학생 정보를 먼저 등록해야 성적 입력 가능");
				continue;
			}
			
			System.out.println("과목코드 >> ");
			String strSubject = scan.nextLine();
			System.out.println("점수 >>");
			String strScore = scan.nextLine();
			int intScore = Integer.valueOf(strScore);
			
			
			long s_id = (long)(Math.random() * 10000);
			ScoreDTO scdto = ScoreDTO.builder().s_id(s_id).s_std(strNum).s_score(intScore).s_subject(strSubject).build();
			
			 int ret = sc.insert(scdto);
			 
			 if(ret > 0) {
				 System.out.println("데이터 추가 완료");
			 }else {
				 System.out.println("데이터 추가 실패");
			 }
			 
			 
			 
			/*
			 * 
			 * 
			 * 
			 */
			
		}

	}

}
