package egovframework.lime.member;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import egovframework.common.controller.DefaultController;
import egovframework.lime.member.service.MemberService;

/**
 * 회원 컨트롤러 클래스를 정의한다.
 * 
 * @author ICT융합사업부 개발팀
 * @since 2015.01.26
 * @version 1.0
 * @see <pre>
 * 
 *  == 개정이력(Modification Information) ==
 *  수정일           수정자          수정내용
 *  ---------    --------    ---------------------------
 *  2015.01.26               최초 생성
 * </pre>
 */
@Controller
public class MemberController extends DefaultController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "MemberService")
	private MemberService memberService;

	// 검색 파라미터
	private List<String> searchParam = Arrays.asList("pMENU_ID");
}
