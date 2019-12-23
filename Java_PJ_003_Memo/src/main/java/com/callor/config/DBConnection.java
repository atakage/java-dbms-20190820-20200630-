package com.callor.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {

	private static SqlSessionFactory sqlSessionFactory = null;

	static {

		String configPath = "com/callor/config/mybatis-config.xml";
		InputStream is = null;

		try {
			is = Resources.getResourceAsStream(configPath);

			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

			if (sqlSessionFactory == null) {
				sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
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
