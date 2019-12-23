package com.biz.grade.exec;

import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.service.ScoreServiceV1;

public class ScoreEx_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		ScoreServiceV1 ss = new ScoreServiceV1();
		
		
		while (true) {

			System.out.println("==================================");
			System.out.println("=============성적처리v1================");
			System.out.println("========================================");
			System.out.print("찾을 데이터 >> ");
			String strId = scanner.nextLine();
			
			
			
			long s_id = Long.valueOf(strId);
			ScoreDTO dto = ss.findById(s_id);
			if(dto == null) {
				System.out.println("찾는 데이터 없음");
			}else {
				System.out.println(dto.toString());
			}
		}
	}

}
