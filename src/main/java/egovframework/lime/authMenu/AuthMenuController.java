package egovframework.lime.authMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.common.controller.DefaultController;
import egovframework.lime.authMenu.service.AuthMenuService;

@Controller
public class AuthMenuController extends DefaultController{
	
	@Resource(name = "AuthMenuService")
	private AuthMenuService authMenuService;
	
	// 검색 파라미터- 
	private List<String> searchParam	= Arrays.asList("pAuthCd", "pMENU_ID");
	
	/**
	 * 메뉴권한 조회
	 * @return ModelAndView
	 * @param commandMap : 모든 파라미터 정보, method = GET
	 * @exception Exception Exception
	 */
	 @RequestMapping(value="/lime/authMenu" , method=RequestMethod.GET)
	 public ModelAndView getAuthMenuList() throws Exception {
		 //**************** 프로그램 로직 ****************//*
		 //디폴트값 설정
		 commandMap.put("MENU_LEVEL", "1");
		 commandMap.put("DEPTH_ID", commandMap.get("ONE_DEPTH_ID")); 	
		 List<Map<String, Object>> list  =authMenuService.getAuthMenuList(commandMap) ;
		 //권한목록 검색조건		 
		 List<Map<String, Object>> authList = authMenuService.getAuthList(commandMap) ;
		 //파라미터 정보
		 String parameters = getRequestToQueryString( searchParam );
		 /**************** 프로그램 로직 ****************/ 
		 
		 /************** ModelView 로직 **************/
		 ModelAndView mav = new ModelAndView();
		 String resultURL = "lime/authMenu/authMenuList";
		 //검색조건
		 mav.addObject("authList", authList);
		 mav.addObject("parameters",parameters);
		 //뷰단
		 mav.addObject("dataList",list);
		 mav.setViewName( resultURL );
		 /************** ModelView 로직 **************/
		 return mav;
	 }
	 
	 /**
	 * 권한 처리 
	 * @return ModelAndView
	 * @param commandMap : 모든 파라미터 정보, method = POST
	 * @exception Exception Exception
	 */
	 @RequestMapping(value="/lime/authMenu/cud/" , method=RequestMethod.POST)
	 public ModelAndView menuCUD() throws Exception {
		 
		 /**************** 프로그램 로직 ****************/
		 authMenuService.menuAUthCUD(commandMap);
		 //파리미터 정보
		 String parameters = getRequestToQueryString( searchParam );
		 /**************** 프로그램 로직 ****************/
		 
		 /************** ModelView 로직 **************/
		 ModelAndView mav = new ModelAndView();
		 String resultURL = "redirect:/lime/authMenu/"+parameters;
		 mav.setViewName( resultURL );
		 /************** ModelView 로직 **************/
		 return mav;
	 } 
}
