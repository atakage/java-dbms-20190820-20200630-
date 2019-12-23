package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV1;
import com.biz.grade.service.StudentService;
import com.biz.grade.service.StudentServiceV1;

public class ScoreEx_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StudentService sts = new StudentServiceV1();
		ScoreService ss = new ScoreServiceV1();

		Scanner scan = new Scanner(System.in);

		while (true) {

			System.out.println("===================");
			System.out.println("내 맘대로 성적 처리");
			System.out.println("===================");
			System.out.print("학생이름 >> ");
			String strName = scan.nextLine();

			List<StudentDTO> studentList = sts.findByName(strName);

			if (studentList == null || studentList.size() < 1) { /// 학생을 못찾으면 stdList 가 null 값이 되어야 하는데
				// = new ArrayList() 생성을 하면
				// null 아닌 size()가 0인 값이 return 되기 때문에
				// null 이거나 size() < 1 인 경우를 검사

				System.out.println("찾는 학생 없음");
				continue;

			}

			for (StudentDTO dto : studentList) {

				List<ScoreDTO> scoreList = ss.findByName(dto.getSt_num());

				System.out.println(dto.getSt_name());
				System.out.println(dto.getSt_num());

				if (scoreList != null) {
					for (ScoreDTO dto2 : scoreList) {
						System.out.println(dto2.toString());

					}

				}

			}

		}

	}

}
