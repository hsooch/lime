package egovframework.lime.member.service;

import java.util.List;
import java.util.Map;

import egovframework.lime.member.MemberVo;

public interface MemberService {
	
	/**
	 * 회원 로그인 정보
	 * @param Map
	 * @exception Exception Exception
	 */
	public MemberVo getLoginMemberInfo(Map<String, Object> param) throws Exception;
	
	public List<Map<String,Object>> getMemberInfo(Map<String, Object> param) throws Exception;
	
	public int insertMember(Map<String, Object> param) throws Exception;
	
	public int updateMember(Map<String, Object> param) throws Exception;
	
	public int deleteMember(Map<String, Object> param) throws Exception;

	public int idDuplChk(Map<String, Object> param) throws Exception;
	
	public void signUp(Map<String, Object> param) throws Exception;
}
