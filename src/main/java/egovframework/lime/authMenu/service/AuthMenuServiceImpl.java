package egovframework.lime.authMenu.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.common.mapper.CommonMapper;
import egovframework.common.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("AuthMenuService")
public class AuthMenuServiceImpl extends EgovAbstractServiceImpl implements AuthMenuService{
	
	
	@Resource(name = "CommonMapper")
	private CommonMapper commonMapper;
	
	private String namespace = "authMenuMapper.";

	/**
	 * 권한 목록
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public List<Map<String, Object>> getAuthList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"getAuthList");
		List<Map<String, Object>>  reslutList = commonMapper.getList(param);
		return reslutList;
	}
	/**
	 * 권한 메뉴 목록
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public List<Map<String, Object>> getAuthMenuList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"getAuthMenuList");
		List<Map<String, Object>>  reslutList = commonMapper.getList(param);
		return reslutList;
	}
	/**
	 * 메뉴 권한 삭제
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public int deleteAuthMenu(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"deleteAuthMenu");
		int resultInt = (Integer)commonMapper.delete(param);
		return resultInt;
	}
	/**
	 * 메뉴 권한 등록
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public void insertAuthMenu(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"insertAuthMenu");
		commonMapper.insert(param);
	}
	/**
	 * 권한 처리
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public void menuAUthCUD(Map<String, Object> param) throws Exception {
		String [] menuArr = StringUtil.toStringArray(param.get("MENU_ID_REF").toString(),"\\|");
		//등록된 권한 초기화
		deleteAuthMenu(param);
		//권한 등록
		for (int i = 0; i < menuArr.length; i++) {
			param.put("MENU_ID", menuArr[i]);
			insertAuthMenu(param);
		}
	}
	/**
	 * 권한 체크용 URL 목록
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public List<Map<String, Object>> getAuthUrlList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"getAuthUrlList");
		List<Map<String, Object>>  reslutList = commonMapper.getList(param);
		return reslutList;
	}
	
}
