package egovframework.lime.code.service;

import java.util.List;
import java.util.Map;

public interface CodeService {
	
	/**
	 * 코드 리스트
	 * @param Map
	 * @exception Exception Exception
	 */
	public List<Map<String,Object>> getCodeList(Map<String, Object> param) throws Exception;
	
}
