package com.biz.iolist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.persistence.ProductDTO;

public interface ProductDao {

	public String getMaxPCode();
	
	public List<ProductDTO> selectAll();
	
	public ProductDTO findById(String p_code);			//매개변수가 1개일 경우 mapper로 값을 전달할 때 마치 1개짜리
													//배열인 것처럼 전달됨 
	
	public List<ProductDTO> findByName(String p_name);
	public ProductDTO findBySName(String p_name);			// 상품정보 입력할 때 완전일치 이름 가진 상품 검사
	
	public int insert(ProductDTO productDTO);		// 매개변수가 2개 이상일 경우 매개변수에
												// @Param() Annotation을 반드시 명시
	public int update(ProductDTO productDTO);
	public int delete(String p_code);
	
	
	public List<ProductDTO> findByIPrice(@Param("sprice") int sprice, @Param("eprice") int eprice);
}
