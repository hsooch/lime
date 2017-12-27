<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagCommon.jsp" %>
<script>
//<![CDATA[
function sortChk() {
	var sf = document.frm;
    if (sf.MENU_ORD == undefined) {
        alert("조회 내역이 없습니다.");
        return;
    }
    if (confirm("수정하시겠습니까?")) {
        sf.SORT_REF.value = objToString(sf.MENU_ORD);
        sf.MENU_ID_REF.value = objToString(sf.MENU_ID);
        sf.submit();
    }
}
//]]>
</script>
	<form name="searchForm" action="/lime/menu/" method="get">
	<input type="hidden" name="pMENU_ID" value="<c:out value="${param.pMENU_ID}"/>"/>
	<div class="board_search">
	<fieldset class="srch">
        <!-- 1단계메뉴 -->
        <html:selectList name='ONE_DEPTH_ID' list='oneAllList'  optionNames='전체' optionValues='' listValue='MENU_ID' listName='MENU_NM' selectedValue='${param.ONE_DEPTH_ID}' defaultValue="1" title="1단계 메뉴" script="class=\"selectSrch\" onchange='this.form.submit()'"/>
	</fieldset>
	</div>
	</form>
	
    <form name="frm" action="/lime/menu/cud" method="post">
    <input type="hidden" name="mode" value="sortSave"/>
    <input type="hidden" name="SORT_REF" value=""/>
    <input type="hidden" name="MENU_ID_REF" value=""/>
    <input type="hidden" name="ONE_DEPTH_ID" value="<c:out value='${param.ONE_DEPTH_ID}'/>"/>
    <input type="hidden" name="pMENU_ID" value="<c:out value="${param.pMENU_ID}"/>"/>
	<div class="t_right mt10 mb15">
        <a href="/lime/menu/form?pMENU_ID=<c:out value='${param.pMENU_ID}'/>&amp;MENU_LEVEL=1" class="btn01">1차 메뉴등록</a>
        <a href="#" onclick="sortChk();return false;" class="btn01">정렬수정</a> 
    </div>
    
    <table summary="게시판의 글제목 리스트" class="table_01 mt10">
    <caption>게시판 리스트</caption>
    <colgroup>
        <col width="10%" />
        <col width="*" />
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <col width="20%" />
    </colgroup>
    <thead>
    <tr>
        <th scope="col" class="first">메뉴코드</th>
        <th scope="col">메뉴명</th>
        <th scope="col">타입</th>
        <th scope="col">순서</th>
        <th scope="col">사용여부</th>
        <th scope="col" class="last">추가</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var='EEO' items='${dataList}'>
        <c:if test="${EEO.LEVEL==1}"><c:set var="trColor" value="#A4C6EB" /></c:if>
        <c:if test="${EEO.LEVEL==2}"><c:set var="trColor" value="#C0D8F1" /></c:if>
        <c:if test="${EEO.LEVEL==3}"><c:set var="trColor" value="#DDEAF8" /></c:if>
        <c:if test="${EEO.LEVEL==4}"><c:set var="trColor" value="#F2F7FC" /></c:if>
        <tr bgcolor="${trColor }">
            <td class="frm">${EEO.MENU_ID}</td>
            <td class="t_left">
                <c:forEach begin="1" end="${EEO.LEVEL}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
                <c:if test="${EEO.LEVEL==1}">①</c:if>
                <c:if test="${EEO.LEVEL==2}">②</c:if>
                <c:if test="${EEO.LEVEL==3}">③</c:if>
                <c:if test="${EEO.LEVEL==4}">④</c:if>
                <c:if test="${EEO.LEVEL==5}">⑤</c:if>
                <c:if test="${EEO.LEVEL==6}">⑥</c:if>
                <c:choose>
                    <c:when test="${EEO.USE_YN == 'Y'}"><a href="/lime/menu/form?pMENU_ID=<c:out value='${param.pMENU_ID}'/>&amp;MENU_ID=<c:out value='${EEO.MENU_ID}'/>&amp;ONE_DEPTH_ID=<c:out value='${param.ONE_DEPTH_ID}'/>">${EEO.MENU_NM}</a></c:when>
                    <c:otherwise><del><a href="/lime/menu/form?pMENU_ID=<c:out value='${param.pMENU_ID}'/>&amp;MENU_ID=<c:out value='${EEO.MENU_ID}'/>&amp;ONE_DEPTH_ID=<c:out value='${param.ONE_DEPTH_ID}'/>">${EEO.MENU_NM}</a></del></c:otherwise>
                </c:choose>
            </td>
            <td>${EEO.MENU_TYP}</td>
            <td>
                <input type="hidden" name="MENU_ID" value="<c:out value='${EEO.MENU_ID}'/>"/>
                <select name="MENU_ORD" class="selectForm">
                    <html:selectInt begin="1" end="20" step="1" selected="EEO.MENU_ORD"/>
                </select>
            </td>
            <td>${EEO.USE_YN=='Y'?'O':'X'}</td>
            <td>
            <a href="/lime/menu/form?pMENU_ID=<c:out value='${param.pMENU_ID}'/>&amp;MENU_ID=<c:out value='${EEO.MENU_ID}'/>&amp;ONE_DEPTH_ID=<c:out value='${param.ONE_DEPTH_ID}'/>" class="btn03">수정</a>
            <a href="/lime/menu/form?pMENU_ID=<c:out value='${param.pMENU_ID}'/>&amp;MENU_LEVEL=<c:out value='${EEO.MENU_LEVEL+1}'/>&amp;MENU_SPIR_ID=<c:out value='${EEO.MENU_ID}'/>&amp;ONE_DEPTH_ID=<c:out value='${param.ONE_DEPTH_ID}'/>" class="btn03">하위메뉴등록</a></td>
        </tr>
        </c:forEach>
    <c:if test="${empty dataList}">
        <tr>
            <td class="center" colspan="6">자료가 없습니다.</td>
        </tr>
    </c:if>
    </tbody>
    </table>
    </form>

