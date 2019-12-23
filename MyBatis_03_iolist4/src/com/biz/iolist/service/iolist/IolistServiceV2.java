package com.biz.iolist.service.iolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.biz.iolist.config.service.pro.ProductServiceV4;
import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.ProductDTO;
import com.biz.iolist.service.dept.DeptServiceV3;

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

public class IolistServiceV2 extends IolistServiceV1 {

	protected DeptServiceV3 deptService = new DeptServiceV3();
	protected ProductServiceV4 proService = new ProductServiceV4();

	@Override
	protected void search() {
		// TODO Auto-generated method stub
		super.search();
	}

	@Override
	protected void delete() {
		// TODO Auto-generated method stub
		super.delete();
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	@Override
	protected void insert() {

		System.out.println("===============");
		System.out.println("매입매출등록");
		System.out.println("=================");

		IolistDTO dto = new IolistDTO();

		while (true) {
			System.out.print("거래구분 입력(1:매입, 2:매출, -1:종료 >> ");
			String strInout = scan.nextLine();
			
		

			try {
				
			

				int intInout = Integer.valueOf(strInout);

			
				if (intInout < 0)
					break;

			
				strInout = intInout == 1 ? "매입" : "매출";
				if (intInout == 1) {
					dto.setIo_inout("매입");
				} else if (intInout == 2) {
					dto.setIo_inout("매출");
				} else {
					System.out.println("매입 매출 구분 다시 입력");
					continue;
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("매입 매출 구분 다시 입력");
				continue;
			}
			
			break;
		}

		if (dto.getIo_inout().isEmpty())
			return;

		while (true) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			String curDate = sdf.format(date);

			System.out.printf("거래일자(%s)", curDate);
			String strDate = scan.nextLine();

			if (strDate.trim().isEmpty()) {

				dto.setIo_date(curDate);
			} else {
				
				try {
					
					sdf.parse(strDate);

				} catch (ParseException e) {
					// TODO: handle exception
					System.out.println("날짜 형식이 잘못됨");
					continue;
				}

				dto.setIo_date(strDate);
			}
			break;
		}

		while (true) {
			System.out.print("거래처명 입력(-q:quit) >> ");
			String strDName = scan.nextLine();
			if (strDName.equalsIgnoreCase("-q"))
				break;
			List<DeptDTO> deptList = deptDao.findByName(strDName);

			
			
			for (DeptDTO deptDTO : deptList) {
				System.out.println(deptDTO.toString());
			}

			
			System.out.println("진입2");
			if (deptList != null && deptList.size() > 0) {
				System.out.print("거래처코드 입력>> ");
				String strDCode = scan.nextLine();

				DeptDTO deptDTO = deptDao.findById(strDCode);
				if (deptDTO == null) {
					System.out.print("거래코드 없음>> ");
					continue;
				} else {
					dto.setIo_dcode(strDCode);
				}
			} else {
				continue;
			}
			break;
		}

		if (dto.getIo_dcode().isEmpty())
			return;

		while (true) {
			System.out.print("상품명 입력(-q:quit) >> ");
			String strPName = scan.nextLine();

			if (strPName.equals("-q"))
				break;

			List<ProductDTO> proList = proDao.findByName(strPName);
			if (proList == null && proList.size() < 1) {
				System.out.println("찾는 상품 없음");
				continue;
			} else {

				for (ProductDTO proDTO : proList) {
					System.out.println(proDTO.toString());
				}
				System.out.print("상품코드>> ");
				String strPCode = scan.nextLine();

				ProductDTO proDTO = proDao.findById(strPCode);

				if (proDTO == null) {
					System.out.println("상품 코드를 확인하시오");
					continue;
				} else {
					dto.setIo_pcode(strPCode);
					int intPrice = dto.getIo_inout().equals("매입") ? proDTO.getP_iprice() : proDTO.getP_oprice();

					dto.setIo_price(intPrice);

				}
			}
			break;
		}

		if (dto.getIo_pcode().isEmpty())
			return;

		while (true) {
			System.out.printf("단가 입력(%d)>> ", dto.getIo_price());
			String strPrice = scan.nextLine();

			try {
				int price = Integer.valueOf(strPrice);
				dto.setIo_price(price);

			} catch (Exception e) {
				// TODO: handle exception
			}

			break;
		}

		while (true) {
			System.out.print("수량 입력>> ");
			String strQty = scan.nextLine();

			try {
				int intQty = Integer.valueOf(strQty);
				dto.setIo_qty(intQty);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("수량은 숫자로만 입력");
				continue;
			}
			break;
		}

		int intTotal = dto.getIo_price() * dto.getIo_qty();
		dto.setIo_total(intTotal);

		int ret = iolistDao.insert(dto);

		if (ret > 0)
			System.out.println("입력 완료");
		else
			System.out.println("입력 실패");
	}

}

/*
 * public void iolistInsert() {
 * 
 * // 시퀀스 번호 자동생성 안함 // 공백 입력 continue 처리 안함
 * 
 * 
 * 
 * 
 * while (true) {
 * 
 * try {
 * 
 * System.out.print("거래처 코드 입력>> "); String strio_dcode = scan.nextLine();
 * 
 * List<IolistDTO> ioList = iolistDao.findByDcode(strio_dcode);
 * 
 * if (ioList == null) { System.out.println("해당 거래처 코드 존재X"); continue; }
 * 
 * String strio_pcode = scan.nextLine(); System.out.print("상품 코드 입력>> "); ioList
 * = iolistDao.findByPcode(strio_pcode);
 * 
 * System.out.println("a.매입 b.매출"); String strio_inout = scan.nextLine();
 * 
 * if (strio_inout.equalsIgnoreCase("a")) { strio_inout = "매입"; IolistDTO dto =
 * iolistDao.findByIn(strio_pcode);
 * 
 * if (dto != null) { System.out.println("상품에 매입 단가 이미 존재 업데이트로 가세요"); continue;
 * }
 * 
 * } else if (strio_inout.equalsIgnoreCase("b")) { strio_inout = "매출"; IolistDTO
 * dto = iolistDao.findByOut(strio_pcode);
 * 
 * if (dto != null) { System.out.println("상품에 매출 단가 이미 존재 업데이트로 가세요"); continue;
 * }
 * 
 * }
 * 
 * if (strio_inout.equalsIgnoreCase("매입")) { System.out.print("매입 단가 입력>> ");
 * String strio_price = scan.nextLine(); int intio_price =
 * Integer.valueOf(strio_price);
 * 
 * } else if (strio_inout.equalsIgnoreCase("매출")) {
 * System.out.print("매출 단가 입력>> "); String strio_price = scan.nextLine(); int
 * intio_price = Integer.valueOf(strio_price);
 * 
 * }
 * 
 * System.out.print("수량 입력>> "); String strio_qty = scan.nextLine(); int
 * intio_qty = Integer.valueOf(strio_qty);
 * 
 * 
 * if(strio_inout.equalsIgnoreCase("매입")) { //매입 계산
 * 
 * }else if(strio_inout.equalsIgnoreCase("매출")) { //매출 계산
 * 
 * }
 * 
 * 
 * Date date = new Date(); SimpleDateFormat sdf = new
 * SimpleDateFormat("yyyy-MM-dd"); String curDate = sdf.format(date);
 * 
 * IolistDTO.builder().io_date(curDate).io_dcode(strio_dcode).io_inout(
 * strio_inout).io_pcode(strio_pcode).io_price(intio_price)
 * 
 * 
 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
 * 
 * }
 * 
 * }
 */
