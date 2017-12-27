<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagCommon.jsp" %>
<script>
//<![CDATA[
    function adminAuthAction() {
        var MENU_ID_REF  = getMultiCheckedString("checkAuth","|");
        var AUTH_CD  = $("#pAuthCd").val();
        if(MENU_ID_REF == ""){
            alert("체크 된 항목이 없습니다.");
            return;
        }
        if(AUTH_CD == ""){
            alert("그룹을 선택하세요");
            $("#pAuthCd").foucs();    
            return;
        }
        $("#MENU_ID_REF").val(MENU_ID_REF);
        $("#AUTH_CD").val(AUTH_CD);
        document.authForm.submit();
    }
 
    function checkUpDown(cur) {
        var checks = document.getElementsByName('checkAuth');
 
        var checked = cur.checked;
        var lencheck = checks.length;
 
        for ( var c = 0; c < lencheck; c++) {
            if (checked) {
                //alert(checks[c].id);
                //* 내 아래
                if (checks[c].id.indexOf('>>' + cur.value + '>>') != -1) {
                    checks[c].checked = true;
                }
                //* 내 위
                if (cur.id.indexOf('>>' + checks[c].value + '>>') != -1) {
                    checks[c].checked = true;
                    ;
                }
            } else {
                //* 내 아래
                if (checks[c].id.indexOf('>>' + cur.value + '>>') != -1) {
                    checks[c].checked = false;
                }
            }
        }
    }
//]]>    
</script>
    <form name="searchForm" action="/lime/authMenu/" method="get">
    <input type="hidden" name="pMENU_ID" value="<c:out value="${param.pMENU_ID}"/>"/>
    <div class="board_search">
    <fieldset class="srch">
        <html:selectList name='pAuthCd' list='authList'  listValue='AUTH_CD' listName='AUTH_NM' selectedValue='${param.pAuthCd}'  title="권한코드" script="onchange='this.form.submit()'"/>
    </fieldset>
    </div>
    </form>
    <form name="authForm" method="post" action="/lime/authMenu/cud/">
        <input type="hidden" name="AUTH_CD" id="AUTH_CD"/>
        <input type="hidden" name="MENU_ID_REF" id="MENU_ID_REF"/>
        <input type="hidden" name="pAuthCd" value="<c:out value="${param.pAuthCd}"/>"/>
        <input type="hidden" name="pMENU_ID" id="pMENU_ID" value="<c:out value="${param.pMENU_ID}"/>"/>
    </form>
    <div>
    <table summary="권한매뉴 리스트" class="table_01 mt10">
    <colgroup>
        <col width="80%" />
        <col width="20%" />
    </colgroup>
    <thead>
    <tr>
        <th scope="col">메뉴명</th>
        <th scope="col" class="last">권한체크</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var='EEO' items='${dataList}'>
        <c:if test="${EEO.LEVEL==1}"><c:set var="trColor" value="#A4C6EB" /></c:if>
        <c:if test="${EEO.LEVEL==2}"><c:set var="trColor" value="#C0D8F1" /></c:if>
        <c:if test="${EEO.LEVEL==3}"><c:set var="trColor" value="#DDEAF8" /></c:if>
        <c:if test="${EEO.LEVEL==4}"><c:set var="trColor" value="#F2F7FC" /></c:if>
        <tr bgcolor="${trColor }">
            <td class="t_left">
                <c:forEach begin="1" end="${EEO.LEVEL}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
                <c:if test="${EEO.LEVEL==1}">①</c:if>
                <c:if test="${EEO.LEVEL==2}">②</c:if>
                <c:if test="${EEO.LEVEL==3}">③</c:if>
                <c:if test="${EEO.LEVEL==4}">④</c:if>
                <c:if test="${EEO.LEVEL==5}">⑤</c:if>
                <c:if test="${EEO.LEVEL==6}">⑥</c:if>
                
                <c:set var="idVar" value=""/>
                <c:if test="${EEO.LEVEL=='1'}">
                    <c:set var="idVar1" value=">>${EEO.MENU_ID}>>"/>
                    <c:set var="idVar" value="${idVar1 }"/>
                </c:if>
                
                <c:if test="${EEO.LEVEL=='2'}">
                    <c:set var="idVar2" value="${idVar1}${EEO.MENU_ID}>>"/>
                    <c:set var="idVar" value="${idVar2 }"/>
                </c:if>
                
                <c:if test="${EEO.LEVEL=='3'}">
                    <c:set var="idVar3" value="${idVar2}${EEO.MENU_ID}>>"/>
                    <c:set var="idVar" value="${idVar3 }"/>
                </c:if>
                <c:if test="${EEO.LEVEL=='4'}">
                    <c:set var="idVar4" value="${idVar3}${EEO.MENU_ID}>>"/>
                    <c:set var="idVar" value="${idVar4 }"/>
                </c:if>
                ${EEO.MENU_NM}
            </td>
            <td>
            <input type="checkbox" name="checkAuth" id="${idVar }" <c:if test="${EEO.AUTH_YN == 'Y'}">checked='checked'</c:if>  value="${EEO.MENU_ID}" onclick="javascript:checkUpDown(this);"/></td>
        </tr>
        </c:forEach>
    <c:if test="${empty dataList}">
        <tr>
            <td class="center" colspan="2">자료가 없습니다.</td>
        </tr>
    </c:if>
    </tbody>
    </table>
    </div>
    <div class="t_right mt10 mb15">
        <a href="" class="btn01" onclick="adminAuthAction();return false;">저장</a>
    </div>

