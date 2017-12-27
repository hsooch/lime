package egovframework.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import egovframework.common.egovCmmn.EgovMessageSource;
import egovframework.common.util.EgovDateUtil;
import egovframework.common.util.StringUtil;
import egovframework.lime.member.MemberVo;
import egovframework.lime.menu.service.MenuService;


@Controller
public class DefaultController extends CommonController{
	
	static Logger logger = Logger.getLogger(DefaultController.class);
	
	@Resource(name ="MenuService")
	protected MenuService clientService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;

	
	
	/**
	 * 초기값 세팅
	 * @param request
	 * @param response
	 * @param commandMap
	 * @throws ModelAndViewDefiningException 
	 * @throws Exception
	 */
	@ModelAttribute("init")
	public void init(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap) throws Exception  {
		super.request = request;
		super.response = response;
		super.commandMap = commandMap;
		
		//관리자 메뉴 권한 체크
		menuAuthCheck();
		menuInfo();
	}
	
	/**
	 * 현재메뉴 정보, Left 메뉴, 현제 메뉴 Path
	 * @throws Exception
	 */
	public void menuInfo() throws Exception{
		if (!StringUtil.noNull(commandMap.get("pMENU_ID")).equals("")){
			//현재 메뉴 정보
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("MENU_ID", commandMap.get("pMENU_ID"));
			Map<String, Object> menuMap = clientService.getMenu(paramMap);
			paramMap.clear();
			paramMap.put("MENU_SPIR_ID", menuMap.get("MENU_ID"));
			List<Map<String, Object>> menuLocationList = clientService.getThisLocationList(paramMap);
			paramMap.clear();
			paramMap.put("MENU_ID", ((Map<String, Object>)menuLocationList.get(0)).get("MENU_ID"));
			//관리자 정보
			MemberVo memberVo = (MemberVo)getAuthenticatedUser();			
			List<Map<String, Object>> leftMenuList = null;
			
			if(memberVo!= null){ 
				if(!memberVo.isAdmin()){
					paramMap.put("MBR_ID", memberVo.getMbr_id());
					paramMap.put("MENU_AUTH_YN", "Y");
					paramMap.put("AUTH_CD", memberVo.getAuth_cd());
				}
				leftMenuList = clientService.getTreeList(paramMap);
			}
			
			String curDay = EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-");
			String curWeek = StringUtil.getDay(curDay);
			
			String curYY = curDay.substring(0, 4);
			String curMM   = curDay.substring(5, 7);
			String curDD   = curDay.substring(8, 10);
			
			request.setAttribute("curDay", curDay);
			request.setAttribute("curWeek", curWeek);
			request.setAttribute("curYY", curYY);
			request.setAttribute("curMM", curMM);
			request.setAttribute("curDD", curDD);
			request.setAttribute("menuMap", menuMap);
			request.setAttribute("leftMenuList", leftMenuList);
			request.setAttribute("menuLocationList", menuLocationList);
			
		}
	}
	
	/**
	 * 관리자 권한 체크 
	 * @throws Exception
	 */
	public void menuAuthCheck() throws Exception {

		String requestURI = request.getRequestURI(); // 요청 URI
		MemberVo memberVo = (MemberVo) getAuthenticatedUser();

		if (memberVo != null && !memberVo.isAdmin()) {
			HttpSession session = request.getSession(true);
			/* 예외 항목들 이것들을 어디다 따로 빼지 음 .... */
			List<String> list = new ArrayList<String>();
			list.add("/lime/");
			list.add("/lime/login/");
			list.add("/lime/logOut");
			list.add("/lime/loginOut");
			list.add("/lime/loginOut/");
			list.add("/lime/link/");
			list.add("/lime/main/"); 
			list.add("/editor/upload.*");
			list.add("/.*file/viewImag.*");
			list.add("/.*file/download.*");
			list.add("/jfile/.*");
			list.add("/code/.*");
			
			Boolean isPermittedURL = false;
			Boolean isPermittedURL2 = false;
			/* 공통허용 권한 체크 */
			for (int i = 0; i < list.size(); i++) {
				String urlPattern = request.getContextPath()
						+ (String) list.get(i);
				if (Pattern.matches(urlPattern, requestURI)) {// 정규표현식을 이용해서 요청
					isPermittedURL = true; // URI가 허용된 URL에 맞는지 점검함.
				}
			}

			/* URL 권한 체크 */
			List<Map<String,Object>> list2 = (List<Map<String,Object>>) session.getAttribute("AUTH_URLS");
			
			for (int i = 0; i < list2.size(); i++) {
				Map<String,Object> map = (Map<String,Object>) list2.get(i);
				String urlPattern = request.getContextPath() + map.get("URL");
				if (Pattern.matches(urlPattern, requestURI)) {// 정규표현식을 이용해서 요청
					isPermittedURL2 = true;// URI가 허용된 URL에 맞는지 점검함.
				}
			}

			if (!isPermittedURL && !isPermittedURL2) {
				throw new ModelAndViewDefiningException(getErrorModel(
						ERROR_ALERT_AND_BACK, "접근 권한이 없습니다."));
			}

		}

	}
		
	/**
	 * TOP 메뉴 정보 세팅
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("limeTopMenuList")
	public List<Map<String, Object>> getMenu() throws Exception {
		HttpSession session = request.getSession(true);
		List<Map<String, Object>> topMenuList = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//관리자 정보
		MemberVo memberVo = (MemberVo)getAuthenticatedUser();
		if(memberVo!= null){
			if(!memberVo.isAdmin()){
				paramMap.put("MBR_ID", memberVo.getMbr_id());
				paramMap.put("MENU_AUTH_YN", "Y");
				paramMap.put("AUTH_CD", memberVo.getAuth_cd());
			}
			paramMap.put("TOP_YN", "Y");
			if(session.getAttribute("topMenuList") != null){
				topMenuList = (List<Map<String, Object>>)session.getAttribute("topMenuList") ;
			}else{
				topMenuList = clientService.getTopMenuList(paramMap);	
				session.setAttribute("topMenuList", topMenuList);
			}
			
		}
		
		return topMenuList;
	}

}
