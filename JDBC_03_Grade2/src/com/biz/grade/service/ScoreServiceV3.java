package com.biz.grade.service;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.config.DBContract.DBConn;
import com.biz.grade.persistence.dao.ScoreDao;
import com.biz.grade.persistence.dao.StudentDao;
import com.biz.grade.persistence.dao.SubjectDao;
import com.biz.grade.persistence.dao.impl.ScoreDaoImp;
import com.biz.grade.persistence.dao.impl.StudentDaoImp;
import com.biz.grade.persistence.dao.impl.SubjectDaoImp;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.persistence.domain.StudentDTO;
import com.biz.grade.persistence.domain.SubjectDTO;

public class ScoreServiceV3 {

	StudentDao stdDao = null;
	SubjectDao sbjDao = null;
	ScoreDao scDao = null;
	Scanner scan = null;

	public ScoreServiceV3() {
		// TODO Auto-generated constructor stub

		scan = new Scanner(System.in);
		stdDao = new StudentDaoImp();
		sbjDao = new SubjectDaoImp();
		scDao = new ScoreDaoImp();
	}

	public int insert(ScoreDTO scDTO) {

		return scDao.insert(scDTO);
	}

	public String inputStudent() {

		String strStNum = null;

		while (true) {

			strStNum = null;

			System.out.print("학번 (-Q:quit) >> ");
			strStNum = scan.nextLine();

			if (strStNum.equalsIgnoreCase("-q")) {

				break;

			}

			StudentDTO stdDTO = stdDao.findById(strStNum);

			if (stdDTO == null) {
				System.out.println("학생 정보에 학번이 없음");
				continue;
			}

			System.out.println(stdDTO.toString());

			break;

		}

		if (strStNum.equalsIgnoreCase("-q"))
			return null;

		return strStNum;

	}

	public String inputSubejct() {

		String strSbNum = null;

		while (true) {

			System.out.print("과목코드 (-Q:quit) >> ");
			strSbNum = scan.nextLine();

			if (strSbNum.equalsIgnoreCase("-q")) {

				break;

			}

			SubjectDTO sbjDTO = sbjDao.findById(strSbNum);

			if (sbjDTO == null) {
				System.out.println("과목코드가 없음");
				continue;
			}
			System.out.println(sbjDTO.toString());
			break;
		}

		if (strSbNum.equalsIgnoreCase("-q"))
			return null;
		return strSbNum;

	}

	public Integer inputScore() { // int형 return은 0~9까지의 숫자형만 사용 가능, Integer형 클래스는 0~9까지의 숫자형과 null 사용 가능

		String strScore = null;
		Integer intScore = null;

		while (true) {

			System.out.print("점수 (-Q:quit) >> ");
			strScore = scan.nextLine();

			if (strScore.equalsIgnoreCase("-q")) {

				break;

			}

			try {

				intScore = Integer.valueOf(strScore);
				if (intScore < 0 || intScore > 100) {
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

		if (strScore.equalsIgnoreCase("-q"))
			return null;
		return intScore;

	}

	public List<ScoreVO> updateStudent() {

		List<ScoreVO> scoreList = null;

		String strStNum = null;

		while (true) {
			System.out.print("학번 >> ");
			strStNum = scan.nextLine();

			if (strStNum.equalsIgnoreCase("-q"))
				break;

			scoreList = scDao.findByStNum(strStNum);

			if (scoreList == null || scoreList.size() < 1) {
				System.out.println("성적 데이터 없음");
				continue;
			}

			break;

		}

		if (strStNum.equalsIgnoreCase("-q"))
			return null;

		return scoreList;

	}

	public ScoreVO selectScore() {

		ScoreVO vo = null;
		String strScore = null;
		while (true) {

			System.out.println("===============================");
			System.out.println("수정할 성적 ID(-Q) >>");
			 strScore = scan.nextLine();

			if (strScore.equalsIgnoreCase("-q"))
				break;

			try {

				vo = scDao.findById(Integer.valueOf(strScore));

				if (vo == null) {
					System.out.println("ID값이 없음");
					continue;
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("아이디는 숫자만 가능");
				continue;
			}
			break;

		}
		
		if(strScore.equalsIgnoreCase("-q")) return null;
		return vo;

	}

	public int updateScore(ScoreVO vo) {
		// TODO Auto-generated method stub
		
		System.out.printf("변경 학번 (원본:%s)>>", vo.getS_std());
		String strStNum = scan.nextLine();
		if(strStNum.length() > 0) {
			vo.setS_std(strStNum);
		}
		System.out.printf("변경 과목코드 (원본:%s)>>", vo.getS_subject());
		String strSubject = scan.nextLine();
		if(strSubject.length() > 0) {
			vo.setS_subject(strSubject);
		}
		System.out.printf("변경 점수 (원본:%d)>>", vo.getS_score());
		String strScore = scan.nextLine();
		
		try {
			int intScore = Integer.valueOf(strScore);
			vo.setS_score(intScore);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return scDao.update(ScoreDTO.builder().s_id(vo.getS_id()).s_std(vo.getS_std()).s_subject(vo.getS_subject()).s_score(vo.getS_score()).build());
		
		
		
	}

}
