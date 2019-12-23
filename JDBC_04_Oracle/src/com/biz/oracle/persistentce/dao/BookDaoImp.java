package com.biz.oracle.persistentce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.oracle.config.DBContract;
import com.biz.oracle.persistentce.BookDTO;

public class BookDaoImp extends BookDao {

	@Override
	public List<BookDTO> selectAll() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

		try {
			String sql = DBContract.SQL.SELECT_BOOKS;
			ps = dbConn.prepareCall(sql); // SQL 문자열을 JDBC 드라이버를 통해 DBMS로 전송하기 위한 데이터형으로 변환하는 절차
			ResultSet rs = ps.executeQuery(); // DBMS에 보낸 SQL을 실행하여 얻어진 결과를 ResultSet에 받기
			// rs가 받은 데이터가 최소 1개 이상의 record이면 rs.next() 는 true가 되고
			// 내부에서 record를 추출할 수 있도록 준비 상태가 됨

			List<BookDTO> bookList = new ArrayList<BookDTO>();
			while (rs.next()) {

				bookList.add(this.rs_2_DTO(rs));

			}

			rs.close();
			ps.close();
			return bookList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private BookDTO rs_2_DTO(ResultSet rs) throws SQLException {

		BookDTO dto = BookDTO.builder().b_code(rs.getString("B_CODE")).b_name(rs.getString("B_NAME"))
				.b_comp(rs.getString("B_COMP")).b_writer(rs.getString("B_WRITER")).b_price(rs.getInt("B_PRICE"))
				.build();

		return dto;
	}

	@Override
	public BookDTO findById(String b_code) {
		// TODO Auto-generated method stub

		PreparedStatement ps = null;

		String sql = DBContract.SQL.SELECT_BOOKS + " WHERE b_code = ? ";
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, b_code);
			ResultSet rs = ps.executeQuery();

			BookDTO dto = null;
			if (rs.next()) {
				dto = this.rs_2_DTO(rs);
			}

			rs.close();
			ps.close();
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<BookDTO> findByName(String b_name) {
		// TODO Auto-generated method stub

		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_BOOKS + " WHERE b_name LIKE '%' || ? || '%' "; // '%?%' 는 문법 오류

		try {
			ps = dbConn.prepareStatement(sql); // sql 문자열을 DBMS에 전달할 수 있는 데이터로 변환
			ps.setString(1, b_name);
			ResultSet rs = ps.executeQuery();

			List<BookDTO> bookList = new ArrayList<BookDTO>();
			while (rs.next()) {

				BookDTO dto = this.rs_2_DTO(rs);
				bookList.add(dto);

			}

			rs.close();
			ps.close();
			return bookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<BookDTO> findByComp(String b_comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByWriter(String b_writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int sprice, int eprice) {
		PreparedStatement ps = null;

		String sql = DBContract.SQL.SELECT_BOOKS + " WHERE b_price BETWEEN ? AND ?";

		List<BookDTO> bookList = new ArrayList<BookDTO>();
		try {
			ps = dbConn.prepareCall(sql);
			ps.setInt(1, sprice);
			ps.setInt(2, eprice);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				bookList.add(this.rs_2_DTO(rs));
			}

			rs.close();
			ps.close();
			return bookList;
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BookDTO bookDTO) {
		// TODO Auto-generated method stub

		PreparedStatement ps = null;

		String sql = "INSERT INTO tbl_books(b_code, b_name, b_comp, b_writer, b_price) VALUES('B' || LPAD(SEQ_BOOKS.NEXTVAL,4,'0'),?,?,?,?)";
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, bookDTO.getB_name());
			ps.setString(2, bookDTO.getB_comp());
			ps.setString(3, bookDTO.getB_writer());
			ps.setInt(4, bookDTO.getB_price());
			int ret = ps.executeUpdate();

			ps.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String b_code) {
		
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM tbl_books WHERE b_code = ? ";
		try {
			ps = dbConn.prepareCall(sql);
			ps.setString(1, b_code);
			int ret = ps.executeUpdate();
			
			ps.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

}
