package com.biz.grade.utils;

public class DBContract {
	
	public static class TABLE {
		
		
		public static final String SCORE = " tbl_score ";
		public static final String STUDENT = " tbl_student ";
		public static final String DEPT = " tbl_dept ";
		
	
		
	}
	
	
	public static class SQL{
		
		
		public static final String SCORE_SELECT = " SELECT s_id, s_std, s_subject, s_score, s_remark FROM tbl_score ";
		
		public static final String STUDENT_SELECT = " SELECT st_num, st_name, st_tel, st_addr, st_grade, st_dept FROM tbl_student ";
		
		
		
		
	
		
	}
	
	public static class VIEW{
		
		
		public static final String SCORE = " view_score ";
		public static final String SCORE_PV = " view_score_pv ";
		
	}
	
	public static class DBConn{
		
		public static final String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		public static final String userName = "grade";
		public static final String password = "grade";
	}

}
