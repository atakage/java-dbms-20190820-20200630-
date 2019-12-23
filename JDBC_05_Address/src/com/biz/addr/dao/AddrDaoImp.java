package com.biz.addr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.addr.persistence.AddrDTO;

public class AddrDaoImp extends AddrDao {

	@Override
	public List<AddrDTO> selectAll() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			
			ps = dbConn.prepareStatement(DBContract.SQL.SELECT_ADDR);
			ResultSet rs = ps.executeQuery();
			
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			
			while(rs.next()) {
				
				addrList.add(this.rsToDTO(rs));
				
			}
			
			rs.close();
			ps.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}

	@Override
	public AddrDTO findById(long id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_ADDR + " WHERE id = ? ";
		
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setLong(1, id);
			
			ResultSet rs = ps.executeQuery();
			AddrDTO dto = null;
			if(rs.next()) {
				
				dto = this.rsToDTO(rs);
				
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
	public List<AddrDTO> findByName(String name) {
		PreparedStatement ps = null;
		
		String sql = DBContract.SQL.SELECT_ADDR + " WHERE name = ? ";
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			while(rs.next()) {
				
				addrList.add( this.rsToDTO(rs));
				
			}
			rs.close();
			ps.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddrDTO findByTel(String tel) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_ADDR + " WHERE tel = ? ";
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, tel);
			ResultSet rs = ps.executeQuery();
			
			AddrDTO dto = null;
			if(rs.next()) {
				dto = this.rsToDTO(rs);
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
	public List<AddrDTO> findByChain(String chain) {
		PreparedStatement ps = null;
		String sql  = DBContract.SQL.SELECT_ADDR + " WHERE chain = ? ";
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setString(1, chain);
			ResultSet rs = ps.executeQuery();
		
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			while(rs.next()) {
				
				addrList.add(this.rsToDTO(rs));
			}
			
			rs.close();
			ps.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	public AddrDTO rsToDTO(ResultSet rs) throws SQLException {
		
		return AddrDTO.builder().addr(rs.getString("addr")).chain(rs.getString("chain")).id(rs.getInt("id")).name(rs.getString("name")).tel(rs.getString("tel")).build();
	}

	@Override
	public int insert(AddrDTO dto) {
		// TODO Auto-generated method stub
		
		PreparedStatement ps = null;
		String sql = "INSERT INTO tbl_addr(id, name, tel, addr, chain) VALUES(?,?,?,?,?)";
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getTel());
			ps.setString(4, dto.getAddr());
			ps.setString(5, dto.getChain());
			
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
	public int update(AddrDTO dto) {
		// TODO Auto-generated method stub
		
		PreparedStatement ps = null;
		String sql = "UPDATE tbl_addr SET  name = ?, tel = ?, addr = ?, chain = ? WHERE id = ?";
		try {
			ps = dbConn.prepareStatement(sql);
		//	ps.setInt(1, dto.getId());
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTel());
			ps.setString(3, dto.getAddr());
			ps.setString(4, dto.getChain());
			ps.setInt(5, dto.getId());
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
	public int delete(int id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = "DELETE FROM tbl_addr WHERE id = ?";
		try {
			ps = dbConn.prepareStatement(sql);
			ps.setInt(1, id);
			int ret = ps.executeUpdate();
			
			ps.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
