package com.biz.iolist.service.iolist.view;

import java.util.List;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.IolistViewDao;
import com.biz.iolist.persistence.IolistVO;

public class IolistViewServiceV1 {
	
	

	IolistViewDao ioViewDao;

	public IolistViewServiceV1() {
		// TODO Auto-generated constructor stub

		ioViewDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(IolistViewDao.class);

	}

	protected void viewList(List<IolistVO> iolist) {

		System.out.println("=======================================");
		System.out.println("매입매출정보");
		System.out.println("=======================================");
		System.out.println("SEQ\t거래일자\t구분\\t거래처\\t상품\\t수량\\t단가\\t합계");
		System.out.println("-----------------------------------------------------");

		for (IolistVO vo : iolist) {

			this.viewItem(vo);

		}

		System.out.println("--------------------------------------------------------");

	}

	protected void viewItem(IolistVO vo) {
		System.out.print(vo.getIo_seq() + "\t");
		System.out.print(vo.getIo_date() + "\t");
		System.out.print(vo.getIo_inout() + "\t");
		System.out.printf("(%s)$s\t", vo.getIo_dcode(), vo.getIo_dname() + "\t");
		System.out.printf("(%s)$s\t", vo.getIo_pcode(), vo.getIo_pname() + "\t");
		System.out.print(vo.getIo_qty() + "\t");
		System.out.print(vo.getIo_price() + "\t");
		System.out.print(vo.getIo_total() + "\n");

	}

	public void viewAllList() {

		List<IolistVO> iolist = ioViewDao.selectAll();
		if (iolist != null && iolist.size() > 0) {

			this.viewList(iolist);
		}

	}

	public void viewListByPCode(String io_pcode) {

		List<IolistVO> iolist = ioViewDao.findByPCode(io_pcode);
		if (iolist != null && iolist.size() > 0) {

			this.viewList(iolist);
		}

	}

	public void viewListByPName(String io_pname) {

		List<IolistVO> iolist = ioViewDao.findByPName(io_pname);
		if (iolist != null && iolist.size() > 0) {

			this.viewList(iolist);
		}

	}
	
	public void viewListByDCode(String io_dcode) {

		List<IolistVO> iolist = ioViewDao.findByDCode(io_dcode);
		if (iolist != null && iolist.size() > 0) {

			this.viewList(iolist);
		}

	}
	
	public void viewListByDName(String io_dname) {

		List<IolistVO> iolist = ioViewDao.findByDName(io_dname);
		if (iolist != null && iolist.size() > 0) {

			this.viewList(iolist);
		}

	}
}
