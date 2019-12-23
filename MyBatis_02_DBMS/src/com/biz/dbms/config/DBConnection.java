package com.biz.dbms.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		
		String configFile = "com/biz/dbms/config/mybatis-config.xml"; 			// *-config.xml 파일을 읽어서 mybatis 초기 설정값 가져오기
		InputStream is = null;
		
		try {
			
			is = Resources.getResourceAsStream(configFile);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); 		// sqlSession을 싱글톤으로 생성하기 위한 절차
			
			if(sqlSessionFactory == null) {
				sqlSessionFactory = builder.build(is);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
