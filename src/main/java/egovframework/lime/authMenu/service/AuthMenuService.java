package egovframework.lime.authMenu.service;

import java.util.List;
import java.util.Map;

public interface AuthMenuService {
	
	/**
	 * 권한 목록
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getAuthList(Map<String, Object> param) throws Exception;
	/**
	 * 권한 메뉴 목록
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getAuthMenuList(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 권한 삭제
	 * @param Map
	 * @exception Exception Exception
	 */
	public int deleteAuthMenu(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 권한 등록
	 * @param Map
	 * @exception Exception Exception
	 */
	public void insertAuthMenu(Map<String, Object> param) throws Exception;
	/**
	 * 권한 처리
	 * @param Map
	 * @exception Exception Exception
	 */
	public void menuAUthCUD(Map<String, Object> param) throws Exception;
	/**
	 * 권한 체크용 URL 목록
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getAuthUrlList(Map<String, Object> param) throws Exception;

}
