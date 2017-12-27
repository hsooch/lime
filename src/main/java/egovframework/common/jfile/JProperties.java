package egovframework.common.jfile;

import egovframework.common.util.EgovProperties;

/**
 *  클래스
 * @author 정호열
 * @since 2010.10.17
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일        수정자       수정내용
 *  -------       --------    ---------------------------
 *   2010.10.17   정호열       최초 생성
 *
 * </pre>
 */
public class JProperties {

	/** 사이트에서 properties 파일로 빠질 부분
	 *  아래 클래스는 임시로 사용하기 위한 목적임.
	 *  사이트에서는 이것을 properties 파일로 빼서 관리 하도록 유도
	 * */
	/*private static final Map<String, String> GLOVAL_VARIABLES_MAP = new HashMap<String, String>() {{
		put(GlovalVariables.DEFAULT_FILE_UPLOAD_PATH_KEY, EgovProperties.getProperty(GlovalVariables.DEFAULT_FILE_UPLOAD_PATH_KEY));
		put(GlovalVariables.DEFAULT_NO_IMAGE_APP_PATH_KEY, EgovProperties.getProperty(GlovalVariables.DEFAULT_NO_IMAGE_APP_PATH_KEY));
		put(GlovalVariables.FTP_SERVER_IP, "192.168.11.55");
		put(GlovalVariables.FTP_USER_ID, "test1");
		put(GlovalVariables.FTP_USER_PASSWORD, "test1");
	}}; 
	
	public static final String getString(String key) {
		return GLOVAL_VARIABLES_MAP.get(key);
	}*/
	
	public static final String getString(String key) {
		return EgovProperties.getProperty(key);
	}
}
