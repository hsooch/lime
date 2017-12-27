package egovframework.common.taglib.html;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import egovframework.common.util.StringUtil;

public class MenuAuthCheckTag extends BodyTagSupport {

	private static final long serialVersionUID = 8394487198607589023L;

	private String menuId;

	Logger logger = Logger.getLogger(MenuAuthCheckTag.class);

	public int doAfterBody() {
		HttpSession session = pageContext.getSession();
		boolean showChk = false;

		try {
			
			
			 List list1 =  (List)session.getAttribute("AUTH_URLS");
			 for (int i = 0; i < list1.size(); i++) {
				 Map map = (Map)list1.get(i);
				 if(StringUtil.noNull(map.get("MENU_ID")).equals(menuId)){
					 showChk = true;
				 }
			}

			if (showChk) {
				BodyContent body = getBodyContent();
				if (body != null) {
					try {
						getPreviousOut().print(body.getString());
					} catch (IOException e) {
						logger.info(e);
					}
				}
			}

		} catch (Exception e) {
			logger.info(e);
		}

		return SKIP_BODY;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
