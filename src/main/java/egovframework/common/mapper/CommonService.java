package egovframework.common.mapper;

import java.util.List;
import java.util.Map;

public interface CommonService {

	/**
	 *  조회
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String, Object>> getList(Map<String, Object> param) throws Exception;
	/**
	 *  상세 조회
	 * @param Map
	 * @exception Exception Exception
	 */
	public Map<String, Object> get(Map<String, Object> param) throws Exception;
	/**
	 *  등록
	 * @param Map
	 * @exception Exception Exception
	 */
	public Integer insert(Map<String, Object> param) throws Exception;
	/**
	 *  수정
	 * @param Map
	 * @exception Exception Exception
	 */
	public int update(Map<String, Object> param) throws Exception;
	/**
	 *  삭제
	 * @param Map
	 * @exception Exception Exception
	 */
	public int delete(Map<String, Object> param) throws Exception;
	/**
	 * 도메인 카운트
	 * @param Map
	 * @exception Exception Exception
	 */
	public int getCount(Map<String, Object> param) throws Exception;
	
}
