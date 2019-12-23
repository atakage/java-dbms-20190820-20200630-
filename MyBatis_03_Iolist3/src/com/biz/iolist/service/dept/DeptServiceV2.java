package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV2 extends DeptServiceV1 {

	public void deptMenu() {

		System.out.println("==============================");
		System.out.println("거래처 정보 관리");
		System.out.println("==============================");
		System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
		System.out.print("업무 선택 >> ");
		String strMenu = scan.nextLine();

		try {

			int intMenu = Integer.valueOf(strMenu);
			if (intMenu == 0)
				return;

			else if (intMenu == 1) { // else를 사용하지 않으면 모든 if문을 다 검사
				
				this.deptInsert();
				

			} else if (intMenu == 2) {

				this.deptUpdate();

			} else if (intMenu == 3) {
				this.viewNameList();
				this.deptDelete();
			} else if (intMenu == 4) {
				this.viewNameAndCEO();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void deptDelete() {

		while (true) {

			System.out.print("삭제할 거래처 코드(-q:quit) >> ");
			String strDCode = scan.nextLine();
			if (strDCode.equalsIgnoreCase("-q"))
				break;

			DeptDTO dto = deptDao.findById(strDCode);

			if (dto == null) {

				System.out.println("삭제할 거래처 코드가 없음");
				continue;
			}
			this.viewDetail(dto);

			System.out.println("정말 삭제?? enter:실행");
			String strYesNo = scan.nextLine();

			if (strYesNo.trim().isEmpty()) {
				int ret = deptDao.delete(strDCode);

				if (ret > 0) {
					System.out.println("삭제완료");
					break;
				} else {
					System.out.println("삭제 실패");
				}
			}

		}

	}

	public void deptUpdate() {

		while (true) {
			System.out.println("업데이트할 거래처 코드>> ");
			String strC = scan.nextLine();

			try {

				if (strC.length() != 5) {
					System.out.println("거래처 코드는 다섯자리");
					continue;
				}

				DeptDTO dto = deptDao.findById(strC);

				if (dto == null) {
					System.out.println("거래처 정보 존재X");
					continue;
				}

				this.viewDetail(dto);

				System.out.println("=================================");

				System.out.println("(기존: " + dto.getD_name() + ")");
				System.out.print("거래처명>> ");
				String strd_name = scan.nextLine();
				if(strd_name.length() < 1) strd_name = dto.getD_name();

				System.out.println("(기존: " + dto.getD_ceo() + ")");
				System.out.print("거래처경영진>> ");
				String strd_ceo = scan.nextLine();
				if(strd_ceo.length() < 1) strd_ceo = dto.getD_ceo();

				System.out.println("(기존: " + dto.getD_tel() + ")");
				System.out.print("거래처번호>> ");
				String strd_tel = scan.nextLine();
				if(strd_tel.length() < 1) strd_tel = dto.getD_tel();

				System.out.println("(기존: " + dto.getD_addr() + ")");
				System.out.print("거래처주소>> ");
				String strd_addr = scan.nextLine();
				if(strd_addr.length() < 1) strd_addr = dto.getD_addr();

				dto.setD_addr(strd_addr);
				dto.setD_ceo(strd_ceo);
				dto.setD_name(strd_name);
				dto.setD_tel(strd_tel);
				

				int ret = deptDao.update(dto);

				if (ret > 0) {
					System.out.println("업뎃 완료");
					break;
				} else {
					System.out.println("업뎃 실패");
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

	public void deptInsert() {

		
		System.out.println("??");
	}

}
