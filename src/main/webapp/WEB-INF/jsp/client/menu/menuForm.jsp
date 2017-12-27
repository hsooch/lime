<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagCommon.jsp" %>
<script>
//<![CDATA[ 
$(document).ready(function() {
	$("#link").hide();
	if($(':radio[name="MENU_TYP"]:checked').val()=="L"){
		$("#link").show();
	}
});
function deleteChk(sf){
    if(confirm('삭제하시겠습니까?')){
        sf.mode.value = "delete";
    }
    sf.submit();
}
function setContentType(val){
	$("#link").hide();
    if(val=="L"){
    	$("#link").show();
    }else{
        $("#memo").html("");
    }
}
//]]>
</script>
	<div class="con_st02">
		<ul>
		    <li><span class="fw_bold">＊<c:out value='${empty menuView?param.MENU_LEVEL:menuView.MENU_LEVEL}'/>단계 메뉴 ${mode == 'write'?'저장':'수정'}</span>
			    <ul>
			        <li>- 메인 화면 변경이 필수이며 상위 메뉴 가이드 전체에 영향을 미칠 수 있으므로</li>
			        <li>- 전체 사이트 이미지를 업데이트 하셔야 합니다.</li>
			    </ul>
		    </li>
		</ul>
	</div>
	<form name="editForm" action="/lime/menu/cud" method="post" onsubmit="return validate(this)">
	    <input type="hidden" name="mode" value="<c:out value='${mode}'/>"/>
	    <input type="hidden" name="MENU_ID" value="<c:out value='${param.MENU_ID}'/>"/>
	    <input type="hidden" name="MENU_LEVEL" value="<c:out value='${param.MENU_LEVEL}'/>"/>
	    <input type="hidden" name="MENU_SPIR_ID" value="<c:out value='${param.MENU_SPIR_ID}'/>"/>
	    <!-- 검색용 -->    
	    <input type="hidden" name="pMENU_ID" value="<c:out value="${param.pMENU_ID}"/>"/>
	    <input type="hidden" name="ONE_DEPTH_ID" value="<c:out value='${param.ONE_DEPTH_ID}'/>"/>
	    
		<table class="table_02"  summary="메뉴명,메뉴설명,노출여부,만족도 사용여부,이미지추가,컨텐츠형식">
		    <colgroup>
		        <col width="18%" />
		        <col width="32%" />
		        <col width="18%" />
		        <col width="32%" />
		    </colgroup>
		    <tbody>
		    <tr>
		        <th scope="row">메뉴명</th>
		        <td colspan="3" class="mnwidth1"><input type="text" name="MENU_NM" size="30" value="<c:out value="${menuView.MENU_NM}"/>" class="inputText" title="메뉴명" checkNull/>
		            <c:if test="${menuView.MENU_LEVEL==1 or param.MENU_LEVEL == 1 }"> 
	                    <input type="checkbox"  value="Y" name="TOP_YN" id="TOP_YN"  <c:if test="${menuView.TOP_YN == 'Y'}">checked="checked"</c:if>><label for="TOP_YN">탑메뉴 사용</label>      
	                </c:if>
		        </td>
		    </tr>
		    <tr>
		        <th scope="row">사용여부</th>
		        <td class="mnwidth1" colspan="3">
		            <html:selectList name='USE_YN' optionValues='Y|N' optionNames='사용|미사용' selectedValue='${menuView.USE_YN}' script=" class=\"selectForm\""/>
		        </td>
		    </tr>
		    <tr>
		        <th scope="row">메뉴타입</th>
		        <td colspan="3">
		        <label><input type="radio" name="MENU_TYP" value="D" onclick='setContentType(this.value)' <c:if test="${menuView.MENU_TYP =='D'}">checked="checked"</c:if>/>디렉토리</label> <label><input type="radio" name="MENU_TYP" value="L" onclick='setContentType(this.value)' <c:if test="${menuView.MENU_TYP =='L'}">checked="checked"</c:if>/>링크</label>
		            <div id="link">
		                링크URL :<input type="text" size="50" name="MENU_URL" value="<c:out value='${menuView.MENU_URL}'/>"/>
		            </div>
		        </td>
		    </tr>
		    </tbody>
		</table>
		<div class="t_right" style="padding-top: 20px;">
	        <input type="submit" value="${mode == 'write'?'저장':'수정'}" class="btn01" />
	        <c:if test="${mode == 'modify'}">
	        <input type="button" value="삭제" class="btn01" onclick="deleteChk(this.form)"/>
	        </c:if>
	        <input type="button" value="목록" class="btn01" onclick="location.href='/lime/menu${parameters}'"/>
	    </div>
    </form>