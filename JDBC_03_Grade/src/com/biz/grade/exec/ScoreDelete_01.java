package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

// 학생이름 입력 받음 - > 성적리스트 보여주고, 리스트를 보고 ID 입력 받아서 해당 ID의 score 값 삭제 
// 
public class ScoreDelete_01 {

	public static void main(String[] args) {

		ScoreServiceV2 sc = new ScoreServiceV2Ext();

		Scanner scan = new Scanner(System.in);

		String strStNum = null;

		List<ScoreVO> scoreList = null;

		while (true) {

			System.out.print("학번 (-Q:quit) >> ");
			strStNum = scan.nextLine();

			if (strStNum.equalsIgnoreCase("-q")) {

				break;

			}

			scoreList = sc.findByStNum(strStNum);

			if (scoreList == null || scoreList.size() < 1) {
				System.out.println("학생 정보에 학번이 없음");
				continue;
			}

			break;

		}

		if (strStNum.equalsIgnoreCase("-q"))
			return;

		while (true) {

			System.out.println("====================");
			System.out.println("ID\t학생이름\t과목\t점수");

			System.out.println("=====================");

			for (ScoreVO vo : scoreList) {

				System.out.print(vo.getS_id() + "\t");
				System.out.print(vo.getSt_name() + "\t");
				System.out.print(vo.getSb_name() + "\t");
				System.out.print(vo.getS_score() + "\n");
			}

			System.out.println("============================");

			System.out.println("삭제할 ID(-Q:query)>> ");
			String strID = scan.nextLine();
			if (strID.equalsIgnoreCase("-q"))
				break;

			try {

				long longID = Integer.valueOf(strID);
				int ret = sc.delete(longID);

				if (ret > 0) {
					System.out.println("삭제 완료");
					scoreList = sc.findByStNum(strStNum);
				} else {
					System.out.println("삭제 실패");
				}

				continue;
			} catch (Exception e) {
				System.out.println("id는 숫자만 가능");
				continue;
			}

		}

	}

}