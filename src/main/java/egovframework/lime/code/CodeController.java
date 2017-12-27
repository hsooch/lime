package egovframework.lime.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.common.controller.DefaultController;
import egovframework.lime.code.service.CodeService;

/**
 * 코드성 관련 컨트롤러
 * @author Administrator
 *
 */
@Controller
public class CodeController extends DefaultController{
	
	@Resource(name = "CodeService")
    private CodeService codeService;
	
	
	/**
	 * 코드 리스트 (Ajax용)  
	 * sqlNo : 값 필수 sql쿼리 순번
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lime/code/listAjax")
	@ResponseBody
	public Map<String, Object> getDiteInfoAjax() throws Exception {
		List<Map<String,Object>> list = codeService.getCodeList(commandMap);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		return result;
	}
	/**
	 * sqlNo
	 * 1:자료분류 선택
	 * 2:수질영향권역 선택 - 대권역
	 * 3:수질영향권역 선택 - 중권역(대권역 한강(1), 낙동강(2), 금강(3), 영산강(4))
	 * 4:수계 선택(대권역 한강(1), 낙동강(2), 금강(3), 영산강(4))
	 * 5:자료구분 선택 - 구분 선택
	 * 6:자료구분 선택 - 조사분야 선택(구분 하천(1), 하구(2), 보(3))
	 * 7:자료구분 선택 - 조사자료 선택(조사분야 저서(11), 어류(12), 부착(13), 식생(14), 서식(15), 수질(16))
	 * 8:입력상태 선택
	 */
	
}
