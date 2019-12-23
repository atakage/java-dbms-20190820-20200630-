package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;

/*
 * Service 클래스
 * main() 호출하여 다양한 연산을 수행하는 용도(file 읽기, 쓰기, 연산)
 * 
 * 
 * Dao(Data Access Object) 클래스
 * Service 클래스의 연산 기능 중 순수하게 JDBC와 연동하여 직접 DB를 SELECT, UPDATE(INSERT, UPDATE, DELETE)하는ㄱ ㅣ능을 
 * Service로부터 분리
 * 
 * main() 입력된 데이터 -> Service에서 가공, 검증 -> Dao에서 UPDATE 수행 -> Dao에서 SELECT한 Data를 Service에서 가공 View 수행
 * 
 * 
 * 
 * 
 * 
 */
public abstract class ScoreDao {

	protected Connection dbConn = null;
	
	
	public ScoreDao() {
		// TODO Auto-generated constructor stub
		
		this.dbConn = DBConnection.getDBConnection();
		
		
	}

	public abstract List<ScoreVO> selectAll();

	public abstract ScoreVO findById(long id);
	
	public abstract List<ScoreVO> findByStName(String stName);
	

	public abstract int insert(ScoreDTO scoreDTO);

	public abstract int update(ScoreDTO scoreDTO);

	public abstract int delete(long id);

	public abstract List<ScoreVO> findByStNum(String strStNum);

	public abstract List<ScoreVO> findBySubject(String strSbNum);

	
}
