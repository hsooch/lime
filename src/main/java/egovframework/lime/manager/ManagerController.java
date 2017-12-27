package egovframework.lime.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.common.controller.DefaultController;

@Controller
public class ManagerController extends DefaultController{
	
	@RequestMapping(value = "/manager/manageMember")
	public ModelAndView adminlogOutProc() throws Exception {
		
		/************** ModelView 로직 **************/
		ModelAndView mav = new ModelAndView();
		String resultURL = "manager/manageMember";
		mav.setViewName(resultURL);
		/************** ModelView 로직 **************/

		return mav;
	}

}
