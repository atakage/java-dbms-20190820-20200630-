package com.biz.addr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.addr.dao.AddrDaoImp;
import com.biz.addr.persistence.AddrDTO;

public class AddrServiceV1 {

	AddrDaoImp dao = null;
	Scanner scan = null;
	List<AddrDTO> addrList = null;
	AddrDTO dto = null;

	public AddrServiceV1() {
		// TODO Auto-generated constructor stub
		scan = new Scanner(System.in);
		dao = new AddrDaoImp();
		addrList = new ArrayList<AddrDTO>();
		dto = new AddrDTO();
	}

	public void execute() {

		while (true) {
			System.out.println("==============");
			System.out.println("주소록 검색");
			System.out.println("==============");
			System.out.println("1. 전체 리스트 2. 아이디 3. 이름  4. 전화번호  5. 관계 Q. 종료");
			System.out.println("----------------------------------------------------");
			System.out.print(">> ");
			String strC = scan.nextLine();

			if (strC.equalsIgnoreCase("q"))
				break;
			
			int intC = 0;

			try {
				
				intC = Integer.valueOf(strC);
				
				
				if (intC == 1) {

					addrList = dao.selectAll();

					this.listPrint(addrList);

				}

				else if (intC == 2) {
					System.out.print("아이디 입력>> ");
					String strId = scan.nextLine();
					int id = Integer.valueOf(strId);
					dto = dao.findById(id);

					if (dto == null) {
						System.out.println("데이터 X");
					} else {
						this.dtoPrint(dto);
					}
				}

				else if (intC == 3) {
					System.out.print("이름 입력>> ");
					String strName = scan.nextLine();
					addrList = dao.findByName(strName);
					if (addrList == null || addrList.size() > 1) {
						System.out.println("데이터 X");
					} else {
						this.listPrint(addrList);
					}
					// List<AddrDTO> = dao.findByName(strName);
				}

				else if (intC == 4) {
					System.out.println("전화번호 입력>> ");
					String strTel = scan.nextLine();

					dto = dao.findByTel(strTel);

					if (dto == null) {
						System.out.println("데이터 X");
					} else {

						this.dtoPrint(dto);

					}
				}

				else if (intC == 5) {
					System.out.println("관계? >> ");
					String strChain = scan.nextLine();
					addrList = dao.findByChain(strChain);
					
					if(addrList == null || addrList.size() < 1) {
						System.out.println("데이터 X");
					}else {
						this.listPrint(addrList);
					}
				
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("커맨드 잘못 입력");
				continue;
			}

		}

	}

	public void dtoPrint(AddrDTO dto) {

		System.out.println("====================");
		System.out.println("검색 결과");
		System.out.println("====================");
		System.out.println("ID\t이름\t전화번호\t주소\t관계");
		System.out.println("----------------------------------");
		System.out.print(dto.getId() + "\t");
		System.out.print(dto.getName() + "\t");
		System.out.print(dto.getTel() + "\t");
		System.out.print(dto.getAddr() + "\t");
		System.out.print(dto.getChain() + "\n");
		System.out.println("-------------------------------------");
	}

	public void listPrint(List<AddrDTO> addrList) {

		System.out.println("====================");
		System.out.println("검색 결과");
		System.out.println("====================");
		System.out.println("ID\t이름\t전화번호\t주소\t관계");
		System.out.println("----------------------------------");
		for (AddrDTO dto : addrList) {
			System.out.print(dto.getId() + "\t");
			System.out.print(dto.getName() + "\t");
			System.out.print(dto.getTel() + "\t");
			System.out.print(dto.getAddr() + "\t");
			System.out.print(dto.getChain() + "\n");
		}
		System.out.println("-------------------------------------");

	}
}
