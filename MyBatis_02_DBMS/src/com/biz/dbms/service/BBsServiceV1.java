package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV1 {

	SqlSession sqlSession = null;
	Scanner scanner = null;

	public BBsServiceV1() {
		// TODO Auto-generated constructor stub
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);

		scanner = new Scanner(System.in);
	}
	
	public void bbsMenu() {
		
		while(true) {
			

			System.out.println("1.내용보기 2.작성 3.수정 4.삭제 0.종료");
			String strMenu = scanner.nextLine();
			int intMenu = 0;
		try {
			intMenu = Integer.valueOf(strMenu);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("0 또는 1~4까지 선택하세요");
			continue;
		}
		
		if(intMenu == 0) {
			return;
		}else if(intMenu == 1) {
			this.viewBBsList();
		}else if(intMenu == 2) {
			
		}else if(intMenu == 3) {
			
		}else if(intMenu == 4) {
			
		}
		
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
			
			BBsDTO dto = BBsDTO.builder().bs_date(curDate).bs_subject(strSubject).bs_text(strText).bs_time(curTime).bs_writer(strWriter).build();
			
			BBsDao dao = sqlSession.getMapper(BBsDao.class);
			
			int ret = dao.insert(dto);

			if(ret > 0 ) {
				System.out.println("작성 완료");
			}else {
				System.out.println("작성 실패");
			}
			
			this.viewBBsList();
			
			System.out.println("계속 작성? Y/N");
			String yn = scanner.nextLine();
			if(yn.equalsIgnoreCase("n")||yn.equalsIgnoreCase("no")) break;
			
			
		}

	}

	public void viewBBsList() {
		// TODO Auto-generated method stub
		
		BBsDao dao = sqlSession.getMapper(BBsDao.class);
		List<BBsDTO> bbsList = dao.selectAll();
		
		System.out.println("============================================");
		System.out.println("슈퍼ㅓ BBs v1");
		System.out.println("============================================");
		System.out.println("작성일\t시각\t작성자\t제목");
		for(BBsDTO dto : bbsList) {
			System.out.print(dto.getBs_date()+"\t");
			System.out.print(dto.getBs_time()+"\t");
			System.out.print(dto.getBs_writer()+"\t");
			System.out.print(dto.getBs_subject()+"\n");
		}
		System.out.println("=============================================");
		
	}

}
