package com.biz.grade.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SubjectDTO {

	
	private String SB_CODE;//	VARCHAR2(4 BYTE)
	private String SB_NAME;//	NVARCHAR2(20 CHAR)
	private String SB_PRO;//	NVARCHAR2(20 CHAR)
}
