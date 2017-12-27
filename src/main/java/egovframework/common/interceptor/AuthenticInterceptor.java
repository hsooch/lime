package egovframework.common.interceptor;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.common.egovCmmn.EgovMessageSource;
import egovframework.common.helper.EgovUserDetailsHelper;
import egovframework.common.util.StringUtil;
import egovframework.lime.member.MemberVo;

/**
 * 인증여부 체크 인터셉터
 * 전자정부 공통 서비스 참조 
 * 수정자 : 방지환
 */
public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	private Logger log  = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/**
	 * 세션에 계정정보(adminVO)가 있는지 여부로 인증 여부를 체크한다.
	 * 계정정보(adminVO)가 없다면, 로그인 페이지로 이동한다.
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {	
		//application/x-www-form-urlencoded; charset=UTF-8
		String contentType = StringUtil.noNull(request.getContentType()); //ajax여부 확인용
		
		String fromUri = StringUtil.noNull(request.getRequestURI());
		String MBR_ID = StringUtil.noNull(request.getParameter("MBR_ID"));
				
		HttpSession session = request.getSession(true);
		MemberVo memberVo = (MemberVo)EgovUserDetailsHelper.getAuthenticatedUser();
		log.debug("fromUri : " + fromUri);
		log.debug("MBR_ID : " + MBR_ID);
		if (memberVo == null && !"/lime/front/popUp/".equals(fromUri) && !"".equals(MBR_ID)) { // 로그인을 하지 않은 상태 ,
			session.invalidate();
			if (log.isInfoEnabled()) {
				log.info("session is null");
			}
			String viewUrl = "cmmn/errorMessage";
			if(!contentType.equals("")){
				viewUrl = "redirect:/lime/sessionCheck/ajax";
			}
			
			ModelAndView model = new ModelAndView(viewUrl);
			model.addObject("ERROR_TYPE", "AlertAndRedirect");
			model.addObject("REDIRECT_URL", "/lime");
			model.addObject("ERROR_MESSAGE", egovMessageSource.getMessage("session.fail.msg"));
			throw new ModelAndViewDefiningException(model);

		} else { // 로그인을 한 상태 , 회원구분 :
			if(memberVo != null){
				// step-2 : 회원구분 
				if (log.isInfoEnabled()) {
					log.info("user session is OK!!");
				}				
			}			
		}
		return true;
	}
}
