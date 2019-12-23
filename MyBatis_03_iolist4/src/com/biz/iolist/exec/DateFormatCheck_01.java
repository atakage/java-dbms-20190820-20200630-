package com.biz.iolist.exec;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatCheck_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		// 기준
		
		
		try {
			sdf.parse("20190101");		//ㅇ지정된 날짜 형식대로 입력하지 않으면 메시지를 보여주고 다시 입력받음
		} catch (ParseException e) {
			System.out.println("날짜 형식이 잘못되었음");
		}

	}

}
