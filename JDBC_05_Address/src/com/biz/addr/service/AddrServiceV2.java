package com.biz.addr.service;

public class AddrServiceV2 extends AddrServiceV1 {

	public void execute2() {

		while (true) {
			System.out.println("==============");
			System.out.println("insert(i), delete(d), update(u), quit(q)");
			System.out.println("----------------------------------------------------");
			String strC = scan.nextLine();

			if (strC.equalsIgnoreCase("q"))
				break;

			try {

				if (strC.equalsIgnoreCase("i")) {
					System.out.print("아이디 입력>> ");
					String strId = scan.nextLine();
					System.out.print("이름 입력>> ");
					String strName = scan.nextLine();
					System.out.print("전화번호 입력>> ");
					String strTel = scan.nextLine();
					System.out.print("주소 입력>> ");
					String strAddr = scan.nextLine();
					System.out.print("관계 입력>> ");
					String strChain = scan.nextLine();

					dto.setAddr(strAddr);
					dto.setChain(strChain);
					dto.setId(Integer.valueOf(strId));
					dto.setName(strName);
					dto.setTel(strTel);

					int ret = dao.insert(dto);

					if (ret > 0) {
						System.out.println("정보 입력 완료");
					} else {
						System.out.println("정보 입력 실패");
					}
				}

				if (strC.equalsIgnoreCase("d")) {
					System.out.print("삭제할 주소록의 아이디 입력>> ");
					String strId = scan.nextLine();
					int ret = dao.delete(Integer.valueOf(strId));

					if (ret > 0) {
						System.out.println("삭제 완료");
					} else {
						System.out.println("삭제 실패");
					}

				}
				
				if(strC.equalsIgnoreCase("u")) {
					
					System.out.print("변경할 주소 아이디 입력>> ");
					String strCId = scan.nextLine();
					int id = Integer.valueOf(strCId);
					dto = dao.findById(id);
					
					this.dtoPrint(dto);
					
					
					
					
					System.out.printf("이름 입력(기존 %s)>> ", dto.getName());
					String strName = scan.nextLine();
					

					System.out.printf("전화번호 입력(기존 %s)>> ", dto.getTel());
					String strTel = scan.nextLine();
					

					System.out.printf("주소 입력(기존 %s)>> ", dto.getAddr());
					String strAddr = scan.nextLine();
					

					System.out.printf("관계 입력(기존 %s)>> ", dto.getChain());
					String strChain = scan.nextLine();
					
					dto.setAddr(strAddr);
					dto.setChain(strChain);
					dto.setName(strName);
					dto.setTel(strTel);
					
					int ret = dao.update(dto);
					
					if(ret > 0) {
						System.out.println("수정 완료");
					}else {
						System.out.println("수정 실패");
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("커맨드 잘못 입력");
			}

		}

	}
}
