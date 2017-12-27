package egovframework.lime.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.common.controller.DefaultController;
import egovframework.common.util.StringUtil;
import egovframework.lime.client.service.ClientService;

/**
 * 클라이언트 메인 화면
 * 
 * @author Administrator
 * 
 */
@Controller
public class ClientMainController extends DefaultController {
	
	@Resource(name = "ClientService")
	private ClientService clientService;
	
	/**
	 * 클라이언트 메인 화면
	 * 
	 * @return ModelAndView
	 * @param commandMap
	 *            : 모든 파라미터 정보, method = GET
	 * @exception Exception
	 */
	@RequestMapping(value = "/client/mainpage", method = RequestMethod.GET)
	public ModelAndView getChargerGroupList() throws Exception {

		ModelAndView mav = new ModelAndView();
		String resultURL = "client/main/main";
		mav.setViewName(resultURL);

		return mav;
	}
	
	@RequestMapping(value = "/client/restaurant")
	public ModelAndView getRestaurant() throws Exception {
		ModelAndView mav = new ModelAndView();
		String resultURL = "client/restaurant/restaurant";
		mav.setViewName(resultURL);
		
		return mav;
	}
	
	@RequestMapping(value = "/restaurant/search/ajax")
	@ResponseBody
	public Map<String, Object> searchRestaurant() throws Exception {
		List<Map<String, Object>> list = clientService.getRestaurant(commandMap);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		
		return result;
	}
	
	@RequestMapping(value ="/restaurant/popup")
	public ModelAndView restForm() throws Exception {
		/**************** 프로그램 로직 ****************/		
		String id = StringUtil.noNull(commandMap.get("RS_ID"));
		Map<String, Object> result = new HashMap<>();
		
		if (!id.isEmpty()) {
			result = clientService.selectRestaurant(commandMap);
			
			System.out.println("수정하기");
			System.out.println("restVo: " + result);
		}
		else {
			System.out.println("새로 쓰기");
		}
		
		String resultURL = "/client/restaurant/popup/restPop";
		/**************** 프로그램 로직 ****************/		
		
		/************** ModelView 로직 **************/
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(resultURL);
		/************** ModelView 로직 **************/
		return mav;
	}
	
	@RequestMapping(value = "/restaurant/cu")
	@ResponseBody
	public Map<String, Object> insertRest() throws Exception {
		clientService.insertRest(commandMap);
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", "ok");
		
		return result;
	}
	
	@RequestMapping(value = "/restaurant/delete")
	@ResponseBody
	public ModelAndView deleteRestaurant() throws Exception {
		String rsId = StringUtil.noNull(commandMap.get("RS_ID"));
		System.out.println("RS_ID = " + rsId);
		List<String> list = Arrays.asList(((String) commandMap.get("RS_ID")).split(","));
		commandMap.put("RS_ID", Arrays.asList(((String) commandMap.get("RS_ID")).split(",")));
		System.out.println("=================== tr value ================");
		System.out.println(commandMap.get("RS_ID"));
		System.out.println(list);
		
		
		clientService.deleteRestaurant(commandMap);
		
		ModelAndView mav = new ModelAndView();
		String resultURL = "redirect:search/ajax";
		mav.setViewName(resultURL);
//		System.out.println("restaurant : " + restaurant);
//		/************** ModelView 로직 **************/
		return mav;
	}
	
	
	/**
	 * 검색
	 * 
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/lime/test/listAjax")
//	@ResponseBody
//	public Map<String, Object> testListAjax() throws Exception {		
//		String tabIndex = StringUtil.noNull(commandMap.get("TAB_INDEX"));
//		
//		List<Map<String, Object>> list = testService.getTestList(commandMap);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("TestList" + tabIndex, list);
//		return result;
//	}

}
