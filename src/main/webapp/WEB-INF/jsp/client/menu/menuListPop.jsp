<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@include file="/common/tagCommon.jsp" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>CMS : 메뉴 권한 선택</title>
<link rel="stylesheet" type="text/css" href="/lime/_css/reset.css" />
<link rel="stylesheet" type="text/css" href="/lime/_css/common.css" />
<link rel="stylesheet" type="text/css" href="/lime/_css/sub.css" />
<link rel="stylesheet" type="text/css" href="/lime/_css/popup.css" />
<script src="/js/jquery.min.1.10.2.js"></script>
<script src="/js/common.js" charset="utf-8"></script>
<script defer="defer">
//<![CDATA[
var msg = "${resultMessage}";
if(msg != null && msg != ""){
    alert(msg);
}
//]]>
</script>
<script>
//<![CDATA[
    function adminAuthAction() {
        var MENU_ID_REF  = getMultiCheckedString("checkAuth","|");
        var DMNMST_ID = document.searchForm.DMNMST_ID.value;
        var GRP_ID = document.authForm.GRP_ID.value;
        if(MENU_ID_REF == ""){
            alert("체크 된 항목이 없습니다.");
            return;
        }
        if(DMNMST_ID == ""){
            alert("선택된 도메인 항목이 없습니다.");
            return;
        }
        if(GRP_ID == ""){
            alert("그룹 아이디 값이 없습니다.");
            return;
        }
        
        document.authForm.DMNMST_ID.value = DMNMST_ID;
        document.authForm.MENU_ID_REF.value = MENU_ID_REF;
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
</head>
<body style="text-align: center;padding-left: 5px;padding-top: 5px;">
<!-- content -->    
    <div style="width: 99%">
	    <form name="authForm" method="post" action="/lime/group/authSave/">
	        <input type="hidden" name="MENU_ID_REF"/>
	        <input type="hidden" name="DMNMST_ID"/>
	        <input type="hidden" name="GRP_ID" value="${param.GRP_ID}"/>
	        <input type="hidden" name="pMENUMST_ID" value="<c:out value="${param.pMENUMST_ID}"/>"/>
	    </form>
        <form name="searchForm" action="/lime/popMenu/" method="get">
        <input type="hidden" name="GRP_ID" value="${param.GRP_ID}"/>
        <div class="board_search">
        <fieldset class="srch">
            <html:selectList name='DMNMST_ID' list='domainList'  listValue='DMNMST_ID' listName='TRADE_NM' selectedValue='${param.DMNMST_ID}' defaultValue="1" script="onchange='this.form.submit()' class=\"selectSrch\""/>
            <input type="submit" value="검색" class="btn05" />
        </fieldset>
        </div>
        </form>
        
        <table summary="메뉴명,권한등록" class="table_01">
            <colgroup>
                <col style="width:*;" />
                <col style="width:30%;" />
            </colgroup>
            <thead>
            <tr>
                <th scope="col">메뉴명</th>
                <th scope="col">권한등록</th>
            </tr>
            </thead>
        </table>
        <div id ="mList" align="center">
            <table class="table_01">
                <colgroup>
                    <col style="width:*;" />
                    <col style="width:25%;" />
                </colgroup>
                <tbody>
                <c:forEach items="${dataList}" var="EO">
                    <c:forEach var='EEO' items='${EO.subList}'>
                    <tr style="background: none">
                        <td class="t_left"
                            <c:if test="${EEO.LEVEL=='1'}"> style="font-weight:bold" </c:if>>
                            <c:forEach begin="1" end="${EEO.LEVEL}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </c:forEach>
                            <c:if test="${EEO.LEVEL !='1'}"> ┗</c:if>
                            
                        <c:set var="idVar" value=""/>
                        <c:if test="${EEO.LEVEL=='1'}">
                            <c:set var="idVar1" value=">>${EEO.MENUMST_ID}>>"/>
                            <c:set var="idVar" value="${idVar1 }"/>
                        </c:if>
                        
                        <c:if test="${EEO.LEVEL=='2'}">
                            <c:set var="idVar2" value="${idVar1}${EEO.MENUMST_ID}>>"/>
                            <c:set var="idVar" value="${idVar2 }"/>
                        </c:if>
                        
                        <c:if test="${EEO.LEVEL=='3'}">
                            <c:set var="idVar3" value="${idVar2}${EEO.MENUMST_ID}>>"/>
                            <c:set var="idVar" value="${idVar3 }"/>
                        </c:if>
                        <c:if test="${EEO.LEVEL=='4'}">
                            <c:set var="idVar4" value="${idVar3}${EEO.MENUMST_ID}>>"/>
                            <c:set var="idVar" value="${idVar4 }"/>
                        </c:if>
                            <label for="${idVar }">
                            ${EEO.MENU_NM}
                            </label>
                        </td>
                        <c:set var="ckVal" value="" />
                        <c:forEach items="${menuAuthList}" var="ML">
                            <c:if test="${EEO.MENUMST_ID == ML.MENUMST_ID}">
                                <c:set var="ckVal" value="checked" />
                            </c:if>
                        </c:forEach>
                        <td class="txt_ct">
                            <input type="checkbox" name="checkAuth" id="${idVar }" ${ckVal }    value="${EEO.MENUMST_ID}"  style="border:0"  } onclick="javascript:checkUpDown(this);"/>
                        </td>
                    </tr>
                    </c:forEach>
                </c:forEach>
                <c:if test='${empty dataList}'>
                    <tr>
                        <td colspan="2" class="txt_ct">검색결과가 없습니다.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            </div>
        <div class="txt_ct mgT20">
            <input type="button" value="적용" onclick="adminAuthAction();return false;" class="btn01"/>
            <input type="button" value="닫기" onclick="top.close();return false;" class="btn01"/>
        </div>
    </div>
<!--// content -->          
</body>
</html>



