package com.biz.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	
	private static SqlSessionFactory sqlSessionFactory;	// SqlSession을 필요에 따라 생성, 삭제 등을 관리할 클래스
	
	
	static {
		String configFile = "com/biz/mybatis/config/mybats-config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(configFile);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			
			if(sqlSessionFactory == null) {
				sqlSessionFactory = builder.build(inputStream);
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
