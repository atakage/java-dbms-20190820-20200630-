package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV3 extends DeptServiceV2 {

	/*
	 * 거래처코드 자동생성, 기존 코드 추가금지, 상호같아도 대표자명 다르면 입력가능
	 */

	@Override
	public void deptInsert() {
		// TODO Auto-generated method stub

		System.out.println("?????????????????????????????");
		while (true) {

			List<DeptDTO> deptList = deptDao.selectAll();
			String codeval = deptDao.getMaxPCode();
			String codeval2 = codeval.substring(1, 5);
			
			int intcode = Integer.valueOf(codeval2);
			
			
			String strd_code = String.format("D%04d", intcode + 1);

			System.out.println(strd_code);

			System.out.println("입력 시작(" + strd_code + ")");

			try {

				System.out.println("상호명>> ");
				String strd_name = scan.nextLine();
				if (strd_name.length() < 1) {
					System.out.println("상호명 반드시 입력!");
					continue;
				}

				System.out.println("대표자>> ");
				String strd_ceo = scan.nextLine();
				if (strd_ceo.length() < 1) {
					System.out.println("대표자명 반드시 입력!");
					continue;
				}

				deptList = deptDao.findByName(strd_name);
				 
				for(DeptDTO dto : deptList) {
					List<DeptDTO> deptListF= deptDao.findByCEO(dto.getD_ceo()) ;
					if (deptListF != null) {
						System.out.println("상호명, 경영진이 이미 존재함");
						continue;
					}

				}

				
				System.out.println("번호>> ");
				String strd_tel = scan.nextLine();

				System.out.println("주소>> ");
				String strd_addr = scan.nextLine();

				DeptDTO dto = new DeptDTO();

				dto = DeptDTO.builder().d_addr(strd_addr).d_ceo(strd_ceo).d_code(strd_code).d_name(strd_name)
						.d_tel(strd_tel).build();

				int ret = deptDao.insert(dto);
				if (ret > 0) {
					System.out.println("입력 성공");
					break;
				} else {
					System.out.println("입력 실패");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

}
