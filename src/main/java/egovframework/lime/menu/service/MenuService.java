package egovframework.lime.menu.service;

import java.util.List;
import java.util.Map;

public interface MenuService {

	/**
	 * OneDepth 메뉴 조회
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getOneDptList(Map<String, Object> param) throws Exception;
	/**
	 * 전체 트리 구조 조회
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getAllTreeList(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 트리 구조 조회
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getTreeList(Map<String, Object> param) throws Exception;
	/**
	 * Depth별 메뉴 조회
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getList(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 위치 정보
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getThisLocationList(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 상세 조회
	 * @param Map
	 * @exception Exception Exception
	 */
	public Map<String, Object> getMenu(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 등록
	 * @param Map
	 * @exception Exception Exception
	 */
	public Integer insertMenu(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 수정
	 * @param Map
	 * @exception Exception Exception
	 */
	public int updateMenu(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 삭제
	 * @param Map
	 * @exception Exception Exception
	 */
	public int deleteMenu(Map<String, Object> param) throws Exception;
	/**
	 * 메뉴 정렬 수정
	 * @param Map
	 * @exception Exception Exception
	 */
	public int updateOrder(Map<String, Object> param) throws Exception;
	/**
	 * top메뉴 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTopMenuList(Map<String, Object> param) throws Exception;
	/**
	 * 하위 메뉴 조회
	 * @param Map
	 * @exception Exception Exception
	 */
	public Map<String, Object> getChildMenu(Map<String, Object> param) throws Exception;
	
}
