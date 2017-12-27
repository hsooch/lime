package egovframework.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.common.util.StringUtil;
import egovframework.lime.member.MemberVo;


@Controller
public class LinkController extends DefaultController{
	
	/**
	 * 메뉴 연결 관리
	 * @return ModelAndView
	 * @param commandMap : 모든 파라미터 정보, method = GET
	 * @exception Exception Exception
	 */
	 @RequestMapping(value="/lime/link")
	 public ModelAndView linkCon(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap) throws Exception {
		 
		 /************** 프로그램 로직 **************/
		 String resultURL = "";
		 String menuId = StringUtil.noNull(request.getParameter("pMENU_ID"));
		 commandMap.put("MENU_ID", menuId);
		 Map<String, Object> menuMap = (Map<String, Object>)request.getAttribute("menuMap");
		 String menuParameter = "pMENU_ID";
		 String cntnsTyp = StringUtil.noNull(menuMap.get("MENU_TYP"));
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		 if("D".equals(cntnsTyp)){ //디렉토리 일경우 (한단계 하위 메뉴로 이동)
				
				paramMap.put("MENU_ID", menuId);
				paramMap.put("DMNMST_ID", menuMap.get("DMNMST_ID"));
				MemberVo userVo = (MemberVo)getAuthenticatedUser();
				if(!userVo.isAdmin()){//관리자가 아니라면
					paramMap.put("MENU_AUTH_YN", "Y");
					paramMap.put("AUTH_CD", userVo.getAuth_cd());
				}
				Map<String, Object> map = clientService.getChildMenu(paramMap);
				resultURL = "redirect:/lime/link/?"+menuParameter+"=" + map.get("MENU_ID");
			}else if("L".equals(cntnsTyp)){ //링크일 경우
				resultURL = "redirect:"+menuMap.get("MENU_URL")+"?"+menuParameter+"=" + menuId;
			}
		 /************** 프로그램 로직 **************/
		 
		 /************** ModelView 로직 **************/
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName( resultURL );
		 /************** ModelView 로직 **************/
		 return mav;
	 }
	
}
