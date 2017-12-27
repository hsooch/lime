package egovframework.lime.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.common.controller.DefaultController;
import egovframework.common.helper.EgovUserDetailsHelper;
import egovframework.common.jfile.JProperties;
import egovframework.common.util.EgovFileScrty;
import egovframework.common.util.StringUtil;
import egovframework.lime.authMenu.service.AuthMenuService;
import egovframework.lime.count.service.CounterService;
import egovframework.lime.member.MemberVo;
import egovframework.lime.member.service.MemberService;

@Controller
public class LoginController extends DefaultController{
	
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	@Resource(name = "CounterService")
	private CounterService counterService;
    
    @Resource(name = "AuthMenuService")
	private AuthMenuService authMenuService;
        
	/**
	* 클라이언트 메인 페이지
	* @return
	* @param request, method = GET
	* @exception Exception Exception
	*/
	@RequestMapping(value="/client/main" , method=RequestMethod.GET)
	public ModelAndView adminLogin() throws Exception {
		
		/************** ModelView 로직 **************/
		//return Model 정보 생성
		ModelAndView mav = new ModelAndView();
		String resultURL = "client/front/login";
		if(isAuthUserChecked()){
			resultURL = "redirect:/client/mainpage?pMENU_ID=6";
		}
		mav.setViewName( resultURL );
		/************** ModelView 로직 **************/
		return mav;
	}
	/**
	* 관리자 로그인 처리
	* @return ModelAndView
	* @param commandMap : 모든 파라미터 정보, method = POST
	* @exception Exception Exception
	*/
	@RequestMapping(value="/lime/front" , method=RequestMethod.POST)
	public ModelAndView adminloginProc() throws Exception {
		/************** 프로그램 로직 **************/
		HttpSession session = request.getSession(true);
		session.removeAttribute(SESSION_KEY_USER);
		
		//세션 타임아웃 시간
		int sessionTime =  StringUtil.toInt(JProperties.getString("session_time"));
		/*비밀번호 암호화*/
		String mbrId = StringUtil.noNull(commandMap.get("mbrId"));
		String passwd = StringUtil.noNull(commandMap.get("passwd"));
		String encPasswd = EgovFileScrty.encryptPassword(passwd,mbrId);//비밀번호 암호화(패스워드,아이디)
		
		String testPassword = EgovFileScrty.encryptPassword("1", "lime");
		System.out.println("encPasswd =" + testPassword );
//		System.out.println(mbrId + "/" +  passwd + "/" + encPasswd);
		
		commandMap.put("passwd", encPasswd);
		//사용자 정보
		MemberVo memberVo = memberService.getLoginMemberInfo(commandMap);
		
		if(!StringUtil.isBlank(memberVo)){
			//관리자
			if(memberVo.getAuth_cd().equals("A0")){
				memberVo.setAdmin(true);
				session.setAttribute(SESSION_KEY_USER, memberVo);
			}else{ 
				session.setAttribute(SESSION_KEY_USER, memberVo);
				
				//메뉴권한정보
				Map<String, Object> userMap =new HashMap<String, Object>();
				userMap.put("AUTH_CD", memberVo.getAuth_cd());
				session.setAttribute("AUTH_URLS", authMenuService.getAuthUrlList(userMap));
			}
			session.setMaxInactiveInterval(sessionTime);
			//접속 정보 저장
			accessInfoInsert(request, memberVo);
		}else{
			return getErrorModel(ERROR_ALERT_AND_REDIRECT, egovMessageSource.getMessage("user.login.fail"),"/lime");
		}
		/************** 프로그램 로직 **************/
		
		/************** ModelView 로직 **************/
		ModelAndView mav = new ModelAndView();
		String resultURL = "redirect:/client/main";
		mav.setViewName( resultURL );
		/************** ModelView 로직 **************/
		return mav;
	}

	/**
	* 관리자 로그아웃 처리
	* @return ModelAndView
	* @param commandMap : 모든 파라미터 정보, method = POST
	* @exception Exception
	* Exception
	*/
	@RequestMapping(value = "/lime/logOut")
	public ModelAndView adminlogOutProc() throws Exception {

		/************** ModelView 로직 **************/
		HttpSession session = request.getSession(true);
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		String resultURL = "redirect:/client/main";
		mav.setViewName(resultURL);
		/************** ModelView 로직 **************/

		return mav;
	}
	
	/**
	* 접속정보 저장
	*/
	public void accessInfoInsert (HttpServletRequest request, MemberVo memberVo) throws Exception{
		String INS_IP = request.getRemoteAddr();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("MBR_IP", INS_IP);
		param.put("MBR_ID", memberVo.getMbr_id());
		counterService.insertCounter(param);
	}
	
	/**
	* ajax 호출시 세션정보가 없을 경우 사용 
	* @return
	* @throws Exception
	*/
	@RequestMapping(value = "/lime/sessionCheck/ajax")
	@ResponseBody
	public Map<String, Object> sessionCheck() throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("message", egovMessageSource.getMessage("session.fail.msg"));
		result.put("errorCode", "401");
		return result;
	}
	
	/**
	* 회원 저장 폼
	* @return ModelAndView
	* @param commandMap : 모든 파라미터 정보, method = GET
	* @exception Exception Exception
	*/
	@RequestMapping(value="/lime/front/popup" , method=RequestMethod.GET)
	public ModelAndView menuForm()  throws Exception {
		/**************** 프로그램 로직 ****************/				
		MemberVo memberVo = (MemberVo)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> member = new HashMap<String, Object>();
		String mode = memberVo==null || (memberVo!=null && !memberVo.isAdmin()) ? MODE_WRITE : MODE_MODIFY; //쓰기/수정 모드 선택
		String resultURL = null;
		
		//파라미터 정보		
		if(mode.equals(MODE_MODIFY))
		{
			param.put("MBR_ID", memberVo.getMbr_id());
			member = memberService.getMemberInfo(param).get(0);
			resultURL = "/lime/admin/popUp/userMngPop";
		}		
		else
		{
//			resultURL = "/lime/front/popUp/userRegPop";
			resultURL = "/client/front/popup/userRegPop";	// 경로 맞춤
		}
		
		List<Map<String, Object>> code99 = getCodeList(99);
		
		
		/**************** 프로그램 로직 ****************/
		
		/************** ModelView 로직 **************/
		ModelAndView mav = new ModelAndView();		
		mav.addObject( "code99", code99);  //소속기관 
		mav.addObject( "mode", mode);
		mav.addObject( "member", member);
		mav.setViewName( resultURL );
		/************** ModelView 로직 **************/
		return mav;
	}
	
	/**
	* 회원 등록 
	* @return ModelAndView
	* @param commandMap : 모든 파라미터 정보, method = POST
	* @exception Exception Exception
	*/
	@RequestMapping(value = "/lime/front/memberSave", method = RequestMethod.POST)
	public ModelAndView menuCUD() throws Exception {

		/**************** 프로그램 로직 ****************/
		String mode = StringUtil.noNull(commandMap.get("mode"));
		commandMap.putAll(getAuthUserInfo());
		// 정보 저장
		if (mode.equals(MODE_WRITE)) {
			memberService.insertMember(commandMap);
			// 정보 수정
		} else if (mode.equals(MODE_MODIFY)) {
			memberService.updateMember(commandMap);
			// 정보 삭제
		} else if (mode.equals(MODE_DELETE)) {
			memberService.deleteMember(commandMap);		
		} 				

		/**************** 프로그램 로직 ****************/

		/************** ModelView 로직 **************/
		ModelAndView mav = new ModelAndView();
		String resultURL = "redirect:/lime";
		mav.setViewName(resultURL);
		/************** ModelView 로직 **************/
		return mav;
	}
	
	/**
	 * ID 중복체크
	 * */
	@RequestMapping(value = "/idDuplChk")
	@ResponseBody
	public Map<String, Object> idDuplChk() throws Exception {
		int resultNo = memberService.idDuplChk(commandMap);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", resultNo);
//		System.out.println(result);
		
		return result;
	}
	
	/**
	 *  회원 가입
	 * */
	@RequestMapping(value = "/signUp")
	@ResponseBody
	public Map<String, Object> registMember() throws Exception {
		String mbrId = StringUtil.noNull(commandMap.get("MBR_ID"));
		String passwd = StringUtil.noNull(commandMap.get("PASSWD"));
		String encPasswd = EgovFileScrty.encryptPassword(passwd,mbrId);//비밀번호 암호화(패스워드,아이디)
		commandMap.put("PASSWD", encPasswd);
		
		memberService.signUp(commandMap);
//		System.out.println("signUp: " + commandMap);
		Map<String, Object> result = new HashMap<>();
		result.put("status", "ok");
		
		return result;
	}

}


