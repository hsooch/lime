package egovframework.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.common.helper.EgovBasicLogger;
import egovframework.common.util.StringUtil;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;


/**
 * 공용 Mapper 클래스
 * @author Administrator
 *
 */
@Repository("CommonMapper")
public class CommonMapper extends EgovAbstractMapper {
	/**
	 *  리스트 조회
	 * @param Map
	 * @exception Exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> getList(Map<String, Object> param) throws Exception {
    	return selectList(mapperId(param), param);
    }
    /**
	 * 카운트
	 * @param Map
	 * @exception Exception Exception
	 */
    public int getCount(Map<String,Object> param) throws Exception {
    	return selectOne(mapperId(param), param);
    }

    /**
	 *  상세 조회
	 * @param Map
	 * @exception Exception Exception
	 */
    public Map<String, Object> get(Map<String,Object> param) throws Exception {
    	return selectOne(mapperId(param), param); 
    }
    /**
     *  회원 정보 조회용 
     * @param Map
     * @exception Exception Exception
     */
    public Object getMember(Map<String,Object> param) throws Exception {
    	return selectOne(mapperId(param), param); 
    }
    /**
	 *  등록
	 * @param Map
	 * @exception Exception Exception
	 */
    public Object insert(Map<String,Object> param) throws Exception {
    	return insert(mapperId(param), param);
    }
    /**
	 *  수정
	 * @param Map
	 * @exception Exception Exception
	 */
    public int update(Map<String,Object> param) throws Exception {
    	return update(mapperId(param), param);
    }
    /**
	 *  삭제
	 * @param Map
	 * @exception Exception Exception
	 */
    public int delete(Map<String,Object> param) throws Exception {
    	return delete(mapperId(param), param);
    }
    
    /**
     * mapperId 등록
     * @param param
     * @return
     */
    public String mapperId(Map<String,Object> param) {
    	String mId = StringUtil.noNull(param.get("mId"));
    	EgovBasicLogger.info("QueryID : "+mId);
    	return mId;
    }
    

}
