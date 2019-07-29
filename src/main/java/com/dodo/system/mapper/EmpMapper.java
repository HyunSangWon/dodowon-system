package com.dodo.system.mapper;

import com.dodo.system.vo.EmpVO;
import com.dodo.system.vo.RoleVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Author Sangwon Hyun on 2019-07-07
 */
@Repository
public interface EmpMapper {

    public EmpVO findByEmpId (@Param("id") String id);
    public int setEmp (EmpVO empVO);
    
	public int totalCntEmp ();
	public List<EmpVO> EmpList( @Param("limitcount") int limitcount,
			 					@Param("contentnum") int contentnum);
	
	
	public EmpVO getImgName(@Param("no") int no);
	
	public int updateMyInfo(EmpVO empVO);
	public int updateMyPassword(EmpVO empVO);
	public int updateMyImage(@Param("no") int no,
							 @Param("sign_img_name") String sign_img_name);
	
	
	public int updateHoliday(@Param("no") int no,
							 @Param("holiday") int holiday);
	
	public EmpVO getHoliday(@Param("empNo") int empNo);

	public List<EmpVO> empFindAll();
	
	public List<EmpVO> deptFindAll(@Param("dept_name") String dept_name);

	public List<EmpVO> getApprovalName(@Param("m_approval") String m_approval,
									   @Param("f_approval") String f_approval);

	public int updateApprovalLine(EmpVO empVO);
	
	public EmpVO getApproverInfo(@Param("approverNo") String approverNo);
}
