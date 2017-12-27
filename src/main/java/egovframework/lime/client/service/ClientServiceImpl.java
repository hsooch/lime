package egovframework.lime.client.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.common.mapper.CommonMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ClientService")
public class ClientServiceImpl extends EgovAbstractServiceImpl implements ClientService {

	@Resource(name = "CommonMapper")
	private CommonMapper commonMapper;

	private String namespace = "restaurantMapper.";

	public List<Map<String, Object>> getRestaurant(Map<String, Object> param) throws Exception {
//		if (param.get("CATEGORY_ID") == null) {
//			param.put("mId", namespace + "getRestaurants");
//		}
//		else {
		param.put("mId", namespace + "getRestaurant");
//		}
		List<Map<String,Object>> resultList = commonMapper.getList(param);
		return resultList;
	}
	
	@Override
	public Map<String, Object> selectRestaurant(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "selectRestaurant");
		Map<String, Object> result = commonMapper.get(param);
		return result;
	}
	
	@Override
	public int deleteRestaurant(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"deleteRestaurant");
		int resultInt = commonMapper.delete(param);
		return resultInt;
	}
	
	@Override
	public void insertRest(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"insertRestaurant");
		commonMapper.insert(param);
	}
	
	@Override
	public void updateRest(Map<String, Object> param) throws Exception {
		param.put("mId", namespace+"updateRestaurant");
		commonMapper.update(param);	
	}

}
