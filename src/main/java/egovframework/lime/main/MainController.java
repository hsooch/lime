//package egovframework.lime.main;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import egovframework.common.controller.DefaultController;
///**
// * 메인페이지 관리
// * @author Administrator
// *
// */
//@Controller
//public class MainController extends DefaultController {
//
//	protected Logger log = LoggerFactory.getLogger(this.getClass());
//	
//	/**
//	 * 메인화면페이지가 없어서 특정화면이동으로 처리
//	 * @return ModelAndView
//	 * @param commandMap : 모든 파라미터 정보, method = GET
//	 * @exception Exception
//	 */
//	@RequestMapping(value = "/client/main", method = RequestMethod.GET)
//	public ModelAndView getChargerGroupList() throws Exception {
//		/************** ModelView 로직 **************/
//		ModelAndView mav = new ModelAndView();
//		String resultURL = "client/front/login?pMENU_ID=20";
//		
////		"redirect:/hr/menu?pMENU_ID=20";
//		
//		mav.setViewName(resultURL);
//		/************** ModelView 로직 **************/
//		return mav;
//	}	
//	
//	
//
//}
