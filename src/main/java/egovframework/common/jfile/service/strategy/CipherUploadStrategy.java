package egovframework.common.jfile.service.strategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import egovframework.common.jfile.GlovalVariables;
import egovframework.common.jfile.exception.JFileException;
import egovframework.common.jfile.security.service.CipherService;
import egovframework.common.jfile.utils.SpringUtils;

public class CipherUploadStrategy implements UploadStrategy {
	
	public void handle(InputStream in,	OutputStream out) {		
		try {
			CipherService service = (CipherService)SpringUtils.getBean(GlovalVariables.CIPHER_SERVICE_BEAN_NAME);
			service.encrypt(in, out);
		} catch (Exception e) {
			throw new JFileException(e);
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}