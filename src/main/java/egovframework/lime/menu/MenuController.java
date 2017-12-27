package egovframework.lime.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.common.controller.DefaultController;
import egovframework.common.util.StringUtil;
import egovframework.lime.code.service.CodeService;
import egovframework.lime.menu.service.MenuService;

@Controller
public class MenuController extends DefaultController{
	
	// 검색 파라미터- 
	private List<String> searchParam	= Arrays.asList("ONE_DEPTH_ID", "pMENU_ID");
	
	@Resource(name = "MenuService")
	private MenuService menuService;
	
	
	@Resource(name = "CodeService")
	private CodeService codeService;
	
	/**
	 * 메뉴 관리 조회
	 * @return ModelAndView
	 * @param commandMap : 모든 파라미터 정보, method = GET
	 * @exception Exception Exception
	 */
	 @RequestMapping(value="/lime/menu" , method=RequestMethod.GET)
	 public ModelAndView menuList() throws Exception {
		 List<Map<String, Object>> list  = null;
		 List<Map<String, Object>> oneAllList  = null;
		 /**************** 프로그램 로직 ****************/
		 //디폴트값 설정
		 commandMap.put("MENU_LEVEL", "1");
		 commandMap.put("DEPTH_ID", commandMap.get("ONE_DEPTH_ID")); 	
		 list = menuService.getAllTreeList(commandMap) ;
		 //1뎁스 메뉴 정보		 
		 commandMap.put("MENU_LEVEL", "1"); 				//1뎁스 메뉴를 가져온다.
		 commandMap.remove("ONE_DEPTH_ID"); 			//전체 조회
		 oneAllList = menuService.getOneDptList(commandMap) ;
		 //파라미터 정보
		 String parameters = getRequestToQueryString( searchParam );
		 /**************** 프로그램 로직 ****************/ 
		 
		 /************** ModelView 로직 **************/
		 ModelAndView mav = new ModelAndView();
		 String resultURL = "lime/menu/menuList";
		 //검색조건
		 mav.addObject("oneAllList", oneAllList);
		 mav.addObject("parameters",parameters);
		 //뷰단
		 mav.addObject("dataList",list);
		 mav.setViewName( resultURL );
		 /************** ModelView 로직 **************/
		 return mav;
	 } 
	 /**
	 * 메뉴 관리 등록 및 수점 폼 
	 * @return ModelAndView
	 * @param commandMap : 모든 파라미터 정보, method = GET
	 * @exception Exception Exception
	 */
	 @RequestMapping(value="/lime/menu/form" , method=RequestMethod.GET)
	 public ModelAndView menuForm()  throws Exception {
		 /**************** 프로그램 로직 ****************/
		 Map<String, Object> menuView = menuService.getMenu(commandMap);
		 
		 String mode = StringUtil.isBlank(menuView)?MODE_WRITE:MODE_MODIFY; //쓰기/수정 모드 선택
		 
		 //파라미터 정보
		 String parameters = getRequestToQueryString( searchParam );
		 /**************** 프로그램 로직 ****************/
		 
		 /************** ModelView 로직 **************/
		 ModelAndView mav = new ModelAndView();
		 String resultURL = "lime/menu/menuForm"; 
		 mav.addObject( "menuView", menuView);
		 mav.addObject( "parameters", parameters);
		 mav.addObject( "mode", mode);
		 mav.setViewName( resultURL );
		 /************** ModelView 로직 **************/
		 return mav;
	 }
	 /**
	 * 메뉴 등록 
	 * @return ModelAndView
	 * @param commandMap : 모든 파라미터 정보, method = POST
	 * @exception Exception Exception
	 */
	 @RequestMapping(value="/lime/menu/cud" , method=RequestMethod.POST)
	 public ModelAndView menuCUD() throws Exception {
		 
		 /**************** 프로그램 로직 ****************/
		 String mode = StringUtil.noNull(commandMap.get("mode"));
		 commandMap.putAll(getAuthUserInfo());
		 //정보 저장
		 if(mode.equals(MODE_WRITE)){
			 menuService.insertMenu(commandMap);
		 //정보 수정
		 }else if(mode.equals(MODE_MODIFY)){
			 menuService.updateMenu(commandMap); 
	     //정보 삭제	 
		 }else if(mode.equals(MODE_DELETE)){
			 menuService.deleteMenu(commandMap);
		 //정렬 수정
		 }else if(mode.equals(SORT_SAVE)){
			 String [] menuArr = StringUtil.toStringArray(commandMap.get("MENU_ID_REF").toString(),"\\|");  
			 String [] sortArr = StringUtil.toStringArray(commandMap.get("SORT_REF").toString(),"\\|");  
			 for(int i=0; i < menuArr.length ; i++){
				 if(!StringUtil.isBlank(menuArr[i])){
					 commandMap.put("MENU_ID",menuArr[i]);
					 commandMap.put("MENU_ORD",sortArr[i]);
					 menuService.updateOrder(commandMap);
				 }
			 }
		 }
		 //파리미터 정보
		 String parameters = getRequestToQueryString( searchParam );
		 
		 /**************** 프로그램 로직 ****************/
		 
		 /************** ModelView 로직 **************/
		 ModelAndView mav = new ModelAndView();
		 String resultURL = "redirect:/lime/menu/"+parameters;
		 mav.setViewName( resultURL );
		 /************** ModelView 로직 **************/
		 return mav;
	 } 
	 
}
