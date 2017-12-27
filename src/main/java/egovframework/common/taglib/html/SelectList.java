package egovframework.common.taglib.html;

import egovframework.common.util.StringUtil;

/**
 * 셀렉트 박스 리스트  생성 태그
 * @author seominho
 *
 */
public class SelectList implements HtmlConstants {
	
	private String name;
	private String title;
	private String script;
	private String[] optionValues;
	private String[] optionNames;
	
	private String selectedValue;
	
	private String defaultValue;
	
	//private Logger logger = Logger.getLogger(this);

	
	public SelectList(String name, String script, String[] optionValues, String[] optionNames, String selectedValue, String defaultValue, String title) {
		this.name = name;
		this.title = title;
		this.script = script;
        this.optionValues = optionValues;
        this.optionNames = optionNames;
		this.selectedValue = (selectedValue != null) ? selectedValue.trim() : selectedValue;
		this.defaultValue = (defaultValue != null) ? defaultValue.trim() : defaultValue;
	}


	public String buildHtml() {
		StringBuffer html = new StringBuffer();
		try {
			html.append("<select name=\"").append(name).append("\"").append(" id=\"").append(name).append("\"").append(" title=\"").append(title).append("\"");
			if (script != null) {
				html.append(" ");
				html.append(script);	
			}
			html.append(">\n");
            if (optionValues != null) {
                int size = (optionValues.length == optionNames.length) ? optionValues.length : 0;
                for (int i = 0; i < size ; i++) {
                    html.append("    <option value=\"").append(optionValues[i]).append("\"");
                    
                    if(!StringUtil.isEmpty(selectedValue)){
	                    if (optionValues[i].equalsIgnoreCase(selectedValue) || optionNames[i].trim().equalsIgnoreCase(selectedValue)) {
	                        html.append(" selected='selected'");
	                    }
                    }else{
	                    if (optionValues[i].equalsIgnoreCase(defaultValue) || optionNames[i].trim().equalsIgnoreCase(defaultValue)) {
	                        html.append(" selected='selected'");
	                    }
                    }
                    html.append(">");
                    html.append(optionNames[i]).append("</option>\n");
                }                
            }
			html.append("</select>");
		} catch (Exception e) {
			//logger.println(Logger.ERROR, e, this);
		}
		
		
		return html.toString();
	}
}
