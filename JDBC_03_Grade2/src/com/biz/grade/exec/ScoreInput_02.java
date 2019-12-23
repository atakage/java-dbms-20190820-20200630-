package com.biz.grade.exec;

import java.util.Random;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV3;

public class ScoreInput_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScoreServiceV3 ss = new ScoreServiceV3();

		String strStNum = ss.inputStudent();

		/*
		 * if (strStNum != null) {
		 * 
		 * String strSubject = ss.inputSubejct();
		 * 
		 * if (strSubject != null) {
		 * 
		 * String strScore = ss.inputScore(); if (strScore != null) { // input처리 } } }
		 */

		if (strStNum == null) {
			System.out.println("성적 입력 종료");
			return; // 프로젝트 종료
		}

		String strSubject = ss.inputSubejct();

		if (strSubject == null) {
			System.out.println("성적 입력 종료");
			return; // 프로젝트 종료
		}

		Integer intScore = ss.inputScore();

		if (intScore == null) { // method에서 숫자값을 return할 때 만약 값을 입력안하는 경우 int형이라면 보통 0을 return할 것
			// 그러면 값을 입력하지 않아서 0인지 다른 이유로 0인지 판단이 어려워짐
			// 이럴 때 Integer형 사용하면 정말 0을 입력했으면 값이 0인 경우로 처리하고 입력하지 않았으면 null return하여 입력하지
			// 않음으로 처리
			System.out.println("성적 입력 종료");
			return;
		}

		Random rd = new Random();

		ScoreDTO scDTO = ScoreDTO.builder().s_id(rd.nextLong()).s_std(strStNum).s_subject(strSubject).s_score(intScore)
				.build();

		int ret = ss.insert(scDTO);

		if (ret > 0) {
			System.out.println("데이터 추가 성공");
		} else {
			System.out.println("데이터 추가 실패");
		}

	}

}
