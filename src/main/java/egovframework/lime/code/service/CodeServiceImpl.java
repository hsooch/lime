package egovframework.lime.code.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.common.mapper.CommonMapper;
import egovframework.common.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("CodeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService{
	
	@Resource(name = "CommonMapper")
	private CommonMapper commonMapper;
	
	private String namespace = "codeMapper.";
	/**
	 * 코드 리스트
	 * @param Map
	 * @exception Exception Exception
	 */
	@Override
	public List<Map<String,Object>> getCodeList(Map<String, Object> param) throws Exception {
		String sqlNo = StringUtil.noNull(param.get("sqlNo"));
		param.put("mId", namespace+"getCodeList"+sqlNo);
		List<Map<String,Object>> reslutList = commonMapper.getList(param);
		return reslutList;
	}
	
}
