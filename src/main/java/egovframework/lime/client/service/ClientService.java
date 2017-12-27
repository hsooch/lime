package egovframework.lime.client.service;

import java.util.List;
import java.util.Map;


public interface ClientService {

	public List<Map<String, Object>> getRestaurant(Map<String, Object> param) throws Exception;
	
	public int deleteRestaurant(Map<String, Object> param) throws Exception;

	public void insertRest(Map<String, Object> param) throws Exception;

	public void updateRest(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectRestaurant(Map<String, Object> param) throws Exception;
}
