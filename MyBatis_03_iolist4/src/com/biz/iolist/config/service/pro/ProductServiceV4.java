package com.biz.iolist.config.service.pro;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV4 extends ProductServiceV3 {

	public void menuProduct() {
		System.out.println("==============================");
		System.out.print("대한쇼핑몰 상품관리 시스템 v3");
		System.out.println("==============================");
		System.out.println("1.등록  2.수정  3.삭제  4.검색 0.끝");
		System.out.print("업무선택 >> ");
		String strMenu = scanner.nextLine();

		int intMenu = Integer.valueOf(strMenu);
		if (intMenu == 1) {
			this.insertProduct();
		} else if (intMenu == 2) {

			this.searchPName();
			this.proUpdate();
		} else if (intMenu == 3) {

			this.searchPName();
			this.deleteProduct();
		} else if (intMenu == 4) {
			this.searchPName();
		} else if (intMenu == 0) {

		}

	}

	protected void viewPDetail(ProductDTO dto) {

		System.out.println("상품 이름이 이미 존재");
		System.out.println("-------------------------");
		System.out.println("상품코드:" + dto.getP_code());
		System.out.println("상품이름:" + dto.getP_name());
		System.out.println("품목:");
		System.out.println("주매입처:");
		System.out.println("매입단가:" + dto.getP_iprice());
		System.out.println("매출단가:" + dto.getP_oprice());
		System.out.println("-------------------------------");

	}

	/*
	 * 상품정보들을 입력받아서 insert 수행 상품코르를 입력받아서 있으면 다시 입력하도록 없으면 다음으로 진행
	 * 
	 */
	public void insertProduct() {

		System.out.println("===================================");

		String strPCode;
		while (true) {

			System.out.print("상품코드(enter: 자동생성, -Q:quit)");
			strPCode = scanner.nextLine();

			if (strPCode.equalsIgnoreCase("-q"))
				break;
			if (strPCode.trim().isEmpty()) {

				String strTMPCode = proDao.getMaxPCode();
				int intPCode = Integer.valueOf(strTMPCode.substring(1));

				intPCode++;

				strPCode = strTMPCode.substring(0, 1);

				strPCode = strPCode + String.format("%04d", intPCode);

				System.out.println("생성된 코드:" + strPCode);
				System.out.println("사용하시겠습니까? (Enter: 예)");
				String strYesNo = scanner.nextLine();

				if (strYesNo.trim().isEmpty())
					break;
				else
					continue;

			}
			

			if (strPCode.length() != 5) {
				System.out.println("상품코드 길이 규칙에 맞지 않음");
				continue;

			}

			if (proDao.findById(strPCode) != null) {
				System.out.println("이미 사용 중인 코드");
				continue;
			}

			strPCode = strPCode.toUpperCase();
			if (!strPCode.substring(0, 1).equalsIgnoreCase("P")) {
				System.out.println("상품코드는 첫글자가 P로 시작되어야 함");
				continue;
			}

			try {
				Integer.valueOf(strPCode.substring(1));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("상품코드의 두 번째 이후는 숫자만 올 수 있음");
				continue;
			}

			break;

		}

		if (strPCode.equalsIgnoreCase("-q"))
			return;

		String strPName;
		while (true) {

			System.out.print("상품이름 (-q:quit)>> ");
			strPName = scanner.nextLine();
			if (strPName.equalsIgnoreCase("-q"))
				break;
			if (strPName.trim().isEmpty()) {
				System.out.println("상품 이름을 반드시 입력해야 함");
				continue;
			}
			

			ProductDTO dto = proDao.findBySName(strPName);

			if (dto != null) {

				this.viewPDetail(dto);
				System.out.println("사용하시겠습니까?(enter 사용, No 다시 입력");
				String yesNo = scanner.nextLine();
				if (yesNo.trim().isEmpty())
					break;
				continue;
			}
			break;
		}

		// 상품 매입 단가 입력은 과세이면 입력가격 / 1.1, 면세 시 그대로

		String strVAT = "1";

		int intIPrice = 0;
		while (true) {

			System.out.print("과세구분(1:과세, 0:면세, -q:quit)>> ");
			strVAT = scanner.nextLine();
			if (strVAT.equalsIgnoreCase("-q"))
				break;
			int intVAT = 1;
			try {
				intVAT = Integer.valueOf(strVAT);
				if (intVAT < 1 || intVAT > 1) {
					System.out.println("과세구분 값은 0 또는 1만");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("0 또는 1만 가능");
			}

			System.out.print("매입단가>> ");
			String strIPrice = scanner.nextLine();

			try {
				intIPrice = (int) (intVAT == 1 ? Integer.valueOf(strIPrice) / 1.1 : Integer.valueOf(strIPrice));
			} catch (Exception e) {
				// TODO: handle exception

				System.out.println("매입단가는 숫자만 가능");
				continue;
			}
			break;
		}

		if (strVAT.equalsIgnoreCase("-q"))
			return;
		String strOPrice;
		int intOPrice = 0;
		while (true) {
			System.out.print("매출단가(-q:quit) >> ");
			strOPrice = scanner.nextLine();
			if (strOPrice.equalsIgnoreCase("-q"))
				break;

			try {
				intOPrice = Integer.valueOf(strOPrice);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("매출단가는 숫자만 가능");
				continue;
			}

			break;
		}

		if (strOPrice.equalsIgnoreCase("-q"))
			return;

		ProductDTO dto = ProductDTO.builder().p_code(strPCode).p_name(strPName).p_vat(strVAT).p_iprice(intIPrice)
				.p_oprice(intOPrice).build();
		
		int ret =proDao.insert(dto);

		if(ret > 0) {
			System.out.println("상품등록 성공");
		}else {
			System.out.println("상품등록 실패");
		}
	}

	/*
	 * 상품코드를 입력받아서 delete 수행
	 */
	public void deleteProduct() {

		System.out.print("삭제할 상품코드 >>");
		String strPcode = scanner.nextLine();

		ProductDTO proDTO = this.viewPDetail(strPcode);

		if (proDTO == null) {
			System.out.println("상품코드가 없습니다");
			return;
		}

		int ret = proDao.delete(strPcode);

		if (ret > 0) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}

	}

}
