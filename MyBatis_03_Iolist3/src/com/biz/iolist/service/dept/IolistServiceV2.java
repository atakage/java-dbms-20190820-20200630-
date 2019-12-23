package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.IolistDTO;

public class IolistServiceV2 extends IolistServiceV1 {

	/*
	 * 거래처 코드 입력 -> 거래처 코드 검증 -> 상품 검색 -> 상품 코드 입력하고 입력한 코드 검증 매입 매출 구분이볅 매입 매출따라 단가
	 * 가져오기 매입합계, 매출합계 계산
	 * 
	 * insert
	 * 
	 * 추가된 데이터 보여주기
	 * 
	 * 
	 * 
	 * 상품코드는 값 존재하면 X
	 */
	public void iolistInsert() {

		// 시퀀스 번호 자동생성 안함

		while (true) {
			System.out.print("거래처 코드 입력>> ");
			String strio_dcode = scan.nextLine();

			List<IolistDTO> ioList = iolistDao.findByDcode(strio_dcode);

			if (ioList == null) {
				System.out.println("해당 거래처 코드 존재X");
				continue;
			}

			String strio_pcode = scan.nextLine();
			System.out.print("상품 코드 입력>> ");
			ioList = iolistDao.findByPcode(strio_pcode);

			System.out.println("a.매입 b.매출");
			String strio_inout = scan.nextLine();

			if (strio_inout.equalsIgnoreCase("a")) {
				IolistDTO dto = iolistDao.findByIn(strio_pcode);
				
				if(dto != null) {
					System.out.println("상품에 매입 단가 이미 존재 업데이트로 가세요");
					continue;
				}
				
			}
			
			if (strio_inout.equalsIgnoreCase("b")) {
				IolistDTO dto = iolistDao.findByOut(strio_pcode);
				
				if(dto != null) {
					System.out.println("상품에 매출 단가 이미 존재 업데이트로 가세요");
					continue;
				}
				
			}
			
			
			
			

		}

	}

}
