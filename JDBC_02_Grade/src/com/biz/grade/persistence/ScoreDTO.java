package com.biz.grade.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 * 
 * VO(Value Object) , DTO(Data Transfer Object)
 * - 공통 기능
 * 			Table과 연관되어 CRUD를 수행할 때 데이터를 담아 method간 이동할 때 사용
 * 
 * - DTO
 * 		물리적 Table과 연관(매핑)돼 완전한 CRUD 수행할 때
 * 
 * - VO
 * 		View Table, Join된 SQL과 연관돼 주로 READ , Retrieve용으로 사용
 * 
 * 
 * 
 * 
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ScoreDTO {
	
	private long s_id;
	private String s_std;
	private String s_subject;
	private int s_score;
	private String s_remark;

}
