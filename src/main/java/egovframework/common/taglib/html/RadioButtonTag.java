package egovframework.common.taglib.html;



import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.common.taglib.html.bean.BeanUtil;
import egovframework.common.util.StringUtil;

/**
 * radio버튼
 * @author admin
 *
 */
public class RadioButtonTag extends DefaultTagSupport {
	
	/**
     * 
     */
    private static final long serialVersionUID = -5583430586891096492L;
    private String name;
	private String script;
	private String value;
	private String text;
	private String checkedValue;
	private String defaultValue;
	private String style;
	private String space;
    private String list;
    private String listValue;
    private String listName;
    
	private String id;

	
	private Logger logger  = LoggerFactory.getLogger(this.getClass());
	
	
	public int doStartTag() throws JspException {
		return (SKIP_BODY);
	}


	public int doEndTag() throws JspException {
		try {
		    checkedValue = getParsedValue(checkedValue, name);
		    
            String[] values = null;
            String[] texts = null;
            String[] imgs = null;
            String[] bigimgs = null;
            String[] ids = null;
            String[] labels = null;
            
            if (list != null) {
                Object obj = getParsedObject(list);
//                if (obj == null) {
//                    obj = pageContext.getServletContext().getAttribute(list);
//                }
                if (obj != null) {
                    List list = (List)obj;
                    
                    
                    String[] tValues = null;
                    String[] tTexts = null;

                    if (value != null) {
                        tValues = (value != null) ? StringUtil.toStringArray(value, DELIMITER) : null;
                        tTexts = (text != null) ? StringUtil.toStringArray(text, DELIMITER) : tValues;
                    }
                    int fSize = tValues != null ? tValues.length : 0;
                    int size = list.size() + fSize;
                    values = new String[size];
                    ids = new String[size];
                    texts = new String[size];
                    if (fSize > 0) {
                        for (int i = 0; i < fSize; i++) {
                            values[i] = tValues[i];
                            texts[i] = tTexts[i];
                        }
                    }
                    
                    if (listValue != null && listName != null) {
                        Object data = null;
                        Object valueObj = null;
                        Object nameObj = null;
                        for (int i = 0; i < list.size(); i++) {
                            data = (Object)list.get(i);
                        	if(data.getClass().getName().indexOf("Map")>=0){
                        		valueObj = ((Map)data).get(listValue);
                        		nameObj = ((Map)data).get(listName);
                        	}else{
                                valueObj = BeanUtil.getMethodValue(data, listValue);
                                nameObj = BeanUtil.getMethodValue(data, listName);
                        	}
                            values[i+fSize] = (valueObj != null) ? String.valueOf(valueObj) : null;
                            ids[i+fSize] = (valueObj != null) ? String.valueOf(valueObj) : null;
                            texts[i+fSize] = (nameObj != null) ? String.valueOf(nameObj) : null;
                        }                        
                    }else{
                    	String[] data = null;
                        for (int i = 0; i < list.size(); i++) {
                            data = (String[])list.get(i);
                            values[i+fSize] = data[0];
                            texts[i+fSize] = data[1];
                        }
                    }
                    

                } else {
                    values = (value != null) ? StringUtil.toStringArray(value, DELIMITER) : null;
                    texts = (text != null) ? StringUtil.toStringArray(text, DELIMITER) : values;
                }
            } else {
                values = (value != null) ? StringUtil.toStringArray(value, DELIMITER) : null;
                texts = (text != null) ? StringUtil.toStringArray(getParsedValue(text), DELIMITER) : values;
            	ids  = (id != null) ? StringUtil.toStringArray(getParsedValue(id), DELIMITER) : ids;
            }
            
			JspWriter out = super.pageContext.getOut();
			RadioButton radoButton = null;
			
			if("IMG".equals(style)){
				radoButton = new RadioButton(name, values, script,  checkedValue, defaultValue, texts, space, imgs, ids, bigimgs );
				out.println(radoButton.buildHtml2());
			}else{
				radoButton = new RadioButton(name, values, script,  checkedValue, defaultValue, texts, space , ids );
				out.println(radoButton.buildHtml());
			}
		} catch (Exception e) {
			logger.info("에러발생{}.",e);
		}
		return (SKIP_BODY);
	}


    /**
     * @param checkedValue The checkedValue to set.
     */
    public void setCheckedValue(String checkedValue) {
        this.checkedValue = checkedValue;
    }


    /**
     * @param defaultValue The defaultValue to set.
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }


    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @param script The script to set.
     */
    public void setScript(String script) {
        this.script = script;
    }


    /**
     * @param space The space to set.
     */
    public void setSpace(String space) {
        this.space = space;
    }


    /**
     * @param text The text to set.
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param list The list to set.
     */
    public void setList(String list) {
        this.list = list;
    }


	public void setStyle(String style) {
		this.style = style;
	}

    /**
     * @param id The list to set.
     */
	public void setId(String id) {
		this.id = id;
	}


	public String getListValue() {
		return listValue;
	}


	public void setListValue(String listValue) {
		this.listValue = listValue;
	}


	public String getListName() {
		return listName;
	}


	public void setListName(String listName) {
		this.listName = listName;
	}

    
}
