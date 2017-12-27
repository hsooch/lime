package egovframework.lime.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.common.mapper.CommonMapper;
import egovframework.lime.member.MemberVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MemberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService  {
	
	@Resource(name = "CommonMapper")
	private CommonMapper commonMapper;
	
	private String namespace = "memberMapper.";
	
	/**
	 * 회원 로그인 정보
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public MemberVo getLoginMemberInfo(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"getLoginMemberInfo");
		MemberVo memberVo = (MemberVo) commonMapper.getMember(param);
		return memberVo;
	}
	
	/**
	 * 회원 로그인 정보
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public List<Map<String,Object>> getMemberInfo(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"getMemberinfo");
		List<Map<String,Object>> reslutList = commonMapper.getList(param);
		return reslutList;
	}
	
	/**
	 * 회원 등록
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public int insertMember(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"insertMember");
		int resultInt = (Integer)commonMapper.insert(param);
		return resultInt;
	}
	/**
	 * 회원 수정
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public int updateMember(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"updateMember");
		int resultInt = commonMapper.update(param);
		return resultInt;
	}
	/**
	 * 회원 삭제
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public int deleteMember(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"deleteMember");
		int resultInt = commonMapper.delete(param);
		return resultInt;
	}
	
	/**
	 * 아이디 중복체크
	 * */
	@Override
	public int idDuplChk(Map<String, Object> param) throws Exception {
		
		param.put("mId", namespace+"idDuplChk");
		
		int result = commonMapper.getCount(param);
		System.out.println(param);
		
		return result;
	}
	
	/**
	 * 회원 가입
	 * */
	@Override
	public void signUp(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"signUp");
		commonMapper.insert(param);
	}
	
}


