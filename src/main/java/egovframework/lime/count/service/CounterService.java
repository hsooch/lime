package egovframework.lime.count.service;

import java.util.Map;

public interface CounterService {
	
	/**
	 * 접속 정보 저장
	 * @param Map
	 * @exception Exception Exception
	 */
	public Integer insertCounter(Map<String, Object> param) throws Exception;

}
