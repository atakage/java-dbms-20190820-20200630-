package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV3 {

	SqlSession sqlSession = null;
	Scanner scanner = null;

	public BBsServiceV3() {
		// TODO Auto-generated constructor stub
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);

		scanner = new Scanner(System.in);
	}

	public void bbsMenu() {

		while (true) {

			System.out.println("내용보기(SQ선택) W.작성 U.수정 D.삭제 Q.종료");
			String strMenu = scanner.nextLine();

			if (strMenu.equalsIgnoreCase("q")) {
				return;
			} else if (strMenu.equalsIgnoreCase("w")) {
				this.writeBBS();
				this.viewBBsList();
			} else if (strMenu.equalsIgnoreCase("u")) {
				
			} else if (strMenu.equalsIgnoreCase("d")) {
					this.deleteBBS();
					this.viewBBsList();
			} else {
				try {
					long intSEQ = Integer.valueOf(strMenu);

					this.viewText(intSEQ);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
	}

	public void viewText(long s_id) {

		BBsDao dao = sqlSession.getMapper(BBsDao.class);

		BBsDTO dto = dao.findById(s_id);

		if (dto == null) {
			System.out.println("내용이 없음");
		} else {
			System.out.println("제목: " + dto.getBs_subject());
			System.out.println("작성: " + dto.getBs_writer());
			System.out.println("작성일: " + dto.getBs_date());
			System.out.println("작성시각: " + dto.getBs_time());
			System.out.println(dto.getBs_text());
		}
	}
	
	public void deleteBBS() {
		
		
		
		System.out.println("=======================================");
		System.out.print("삭제할 ID(-q:quit)>> ");
		String strID = scanner.nextLine();
		
		try {
			
			int intID = Integer.valueOf(strID);
			this.viewText(intID);
			System.out.println("-----------------------");
			System.out.print("삭제할까요(Y/N)?");
			String yn = scanner.nextLine();
			if(yn.equalsIgnoreCase("YES")) {
				

				BBsDao dao = sqlSession.getMapper(BBsDao.class);
				int ret = dao.delete(intID);
				
				if(ret > 0) {
					System.out.println("삭제 완료");
				}else {
					System.out.println("삭제 실패");
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void writeBBS() {
		// TODO Auto-generated method stub

		while (true) {

			System.out.print("작성자(-q:작성중단) >> ");
			String strWriter = scanner.nextLine();
			if (strWriter.equalsIgnoreCase("-q"))
				break;

			if (strWriter.trim().length() < 1) {
				System.out.println("작성자는 반드시 입력해야 함");
				continue;
			}

			System.out.print("제목(-q:작성중단) >> ");
			String strSubject = scanner.nextLine();
			if (strSubject.equalsIgnoreCase("-q"))
				break;
			if (strSubject.trim().length() < 1) {
				System.out.println("제목은 반드시 입력해야 함");
				continue;
			}

			System.out.print("내용(-q:작성중단) >> ");
			String strText = scanner.nextLine();
			if (strText.equalsIgnoreCase("-q"))
				break;
			if (strText.trim().length() < 1) {
				System.out.println("내용은 반드시 입력해야 함");
				continue;
			}

			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			SimpleDateFormat tf = new SimpleDateFormat("HH:mm:SS");

			String curDate = sdf.format(date);
			String curTime = tf.format(date);

			BBsDTO dto = BBsDTO.builder().bs_date(curDate).bs_subject(strSubject).bs_text(strText).bs_time(curTime)
					.bs_writer(strWriter).build();

			BBsDao dao = sqlSession.getMapper(BBsDao.class);

			int ret = dao.insert(dto);

			if (ret > 0) {
				System.out.println("작성 완료");
			} else {
				System.out.println("작성 실패");
			}

			break;

		}

	}

	public void viewBBsList() {
		// TODO Auto-generated method stub

		BBsDao dao = sqlSession.getMapper(BBsDao.class);
		List<BBsDTO> bbsList = dao.selectAll();

		System.out.println("============================================");
		System.out.println("슈퍼ㅓ BBs v1");
		System.out.println("============================================");
		System.out.println("SQ\t작성일\t\t시각\t\t작성자\t제목");
		for (BBsDTO dto : bbsList) {
			System.out.print(dto.getBs_idpk() + "\t");
			System.out.print(dto.getBs_date() + "\t");
			System.out.print(dto.getBs_time() + "\t");
			System.out.print(dto.getBs_writer() + "\t");
			System.out.print(dto.getBs_subject() + "\n");
		}
		System.out.println("=============================================");

	}

}
