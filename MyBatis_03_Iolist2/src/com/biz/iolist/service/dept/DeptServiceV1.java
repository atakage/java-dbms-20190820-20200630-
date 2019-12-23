package com.biz.iolist.service.dept;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.DeptDao;
import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV1 {

	protected DeptDao deptDao;
	Scanner scan;

	public DeptServiceV1() {
		// TODO Auto-generated constructor stub
		deptDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(DeptDao.class);

		scan = new Scanner(System.in);

	}
	
	
	public void viewAllList() {
		
		List<DeptDTO> deptList = deptDao.selectAll();
		if(deptList == null || deptList.size() < 1) {
			System.out.println("리스트 없음");
		}else {
		this.viewList(deptList);
		
		}
		
		
	}
	
	public void viewNameList() {
		System.out.print("거래처명으로 검색: ");
		String d_name = scan.nextLine();
		List<DeptDTO> deptList = null ;
		if(d_name.trim().isEmpty()) {
			deptList = deptDao.selectAll();
			
		}
		else {
			deptList = deptDao.findByName(d_name);
			
		}
		
		this.viewList(deptList);
		
	}
	
	public void viewCEOList() {
		System.out.print("CEO명으로 검색: ");
		String d_ceo = scan.nextLine();
		List<DeptDTO> deptList = null ;
		if(d_ceo.trim().isEmpty()) {
			deptList = deptDao.selectAll();
			
		}
		else {
			deptList = deptDao.findByCEO(d_ceo);
			
		}
		
		this.viewList(deptList);
		
	}
	
	public void viewNameAndCEO() {
		System.out.print("거래처명>> ");
		String d_name = scan.nextLine();
		
		System.out.print("대표명>> ");
		String d_ceo = scan.nextLine();
		
		
		List<DeptDTO> deptList = null;
		
		if(d_name.trim().isEmpty() && d_ceo.trim().isEmpty()) {
			deptList = deptDao.selectAll();
		}else if(d_name.trim().isEmpty()) {
			
		}else if(d_ceo.trim().isEmpty()) {
			deptList = deptDao.findByName(d_name);
		}else {
			deptList = deptDao.findByNameAndCEO(d_name, d_ceo);
		}
		
		this.viewList(deptList);
	}
	
	protected void viewList(DeptDTO dto) {		// 각 view에서 list를 출력할 때 사용
		
		
		System.out.print(dto.getD_code()+"\t");
		System.out.print(dto.getD_name()+"\t");
		System.out.print(dto.getD_ceo()+"\t");
		System.out.print(dto.getD_tel()+"\t");
		System.out.print(dto.getD_addr()+"\n");
		
	}
	
	protected void viewList(List<DeptDTO> deptList) {		// 각 view에서 list를 출력할 때 사용
		
		System.out.println("거래처 리스트");
		System.out.println("=============================");
		System.out.println("코드\t거래처명\t경영진\t번호\t주소");
		System.out.println("=============================");
		for(DeptDTO dto : deptList) {
			
			this.viewList(dto);
		}
		
	}
	}


