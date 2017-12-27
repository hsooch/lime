package egovframework.lime.count.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.common.mapper.CommonMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("CounterService")
public class CountServiceImpl extends EgovAbstractServiceImpl implements CounterService {

	
	@Resource(name = "CommonMapper")
	private CommonMapper commonMapper;
	
	private String namespace = "counterMapper.";
	
	/**
	 * 접속 정보 저장
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public Integer insertCounter(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"insertCounter");
		Integer resultInt = (Integer) commonMapper.insert(param);
		return resultInt;
	}

}
