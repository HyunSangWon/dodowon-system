package com.dodo.system;


import com.dodo.system.mapper.DocsMapper;
import com.dodo.system.vo.HolidayVO;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemApplicationTests {

	@Autowired
	private DocsMapper docsMapper;
	
	@Test
	public void contextLoads() throws Exception{
		String startDay = "2019-07-25";
        String endDay = "2019-07-25";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = formatter.parse(startDay);
        Date endDate = formatter.parse(endDay);

        // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
        long diff = endDate.getTime() - beginDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.println(diffDays+1);
	}
	
	public void DoApprovalDocs(String docsType,int docsNo,String decision){	
		/* 0. m.approval 이 전결할지 말지 체크 
		 * 1. 승인을 하기전에 문서올린 직원이 f_approval가 null 여부 체크 
		 * 2. null 이면 상태값 i -> a, 아니면 i -> y
		 */
		if(docsType.equals("holiday")) {		
			if(!decision.equals("n")) {
				
				HolidayVO holidayVO = docsMapper.findByHolidayNo(docsNo);
		
				if(holidayVO.getF_approver() != null) { //최종승인자가 존재한다면
					 decision = "a"; // 1차 승인 			
					 
				 }			
			 }
			System.out.println("docsType ------> :"+docsType);
			System.out.println("docsNo ------> :"+docsNo);
			System.out.println("decision ------> :"+decision);
			 docsMapper.updateDocsStatus(decision,docsNo,docsType);
		}
	}
}
