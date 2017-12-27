<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@include file="/common/tagCommon.jsp" %>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>라임컴퍼니 : <c:out value="${menuMap.MENU_NM}"/></title>
<!--[if lt ie 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->
<link href="/css/BasicSet.css" rel="stylesheet" type="text/css" />
<link href="/css/main.css" rel="stylesheet" type="text/css" />
<link href="/css/btnSt.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/js/ui/TopMenu.js"></script>
<script type="text/javascript" src="/js/ui/ui.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/frm.js"></script>

</head>
<body>
<!--상단-->
<div id="header" class="fullFrame">
    <div id="top" class="contentFrame">
        <h1><a href="/" title="홈 바로가기"></a></h1>
        <ul>
            <li><a href="/" title="처음으로">처음으로</a></li>
            <li>l</li>
            <c:if test="${empty memberVo.mbr_nm}">
            	<li>로그인</li>
            </c:if>
            <c:if test="${!empty memberVo.mbr_nm}">
            	<li><a href="/lime/logOut" title="로그아웃">로그아웃</a></li>
            </c:if>
            <li>l</li>
            <li><a href="#" title="정보수정">정보수정</a></li>
            <li>l</li>
            <li><a href="#" title="사이트맵">사이트맵</a></li>
            <c:if test="${memberVo.admin}">
            <li>l</li>
            <li class="admin"><a href="" onclick="return false;">관리자</a></li>
            </c:if>
        </ul>
        <c:if test="${!empty memberVo.mbr_nm}">
        	<p>${curDay}(${curWeek}) <b><c:out value="${memberVo.dept_nm}"/> <c:out value="${memberVo.mbr_nm}"/></b>님이 로그인하셨습니다.</p>
        </c:if>
    </div>
</div>
<!--대메뉴-->
<div id="mainArea" class="fullFrame">
    <ul id="mainGnb" class="contentFrame">
        <c:forEach items="${topMenuList}" var="eo" varStatus="vs">
        <c:set var="linkUrl" value="${eo.MENU_URL}?pMENU_ID=${eo.MENU_ID}"/>
        <c:if test='${eo.MENU_TYP == "D"}'>
            <c:set var="linkUrl" value="/lime/link/?pMENU_ID=${eo.MENU_ID}"/>
        </c:if>
        <c:if test='${eo.MENU_TYP == "L"}'>
            <c:set var="linkUrl" value="${eo.MENU_URL}?pMENU_ID=${eo.MENU_ID}"/>
        </c:if>
        <c:set var="classOn" value=""/>
        <c:if test='${menuLocationList[0].MENU_ID==eo.MENU_ID}'>
        <c:set var="classOn" value=" class='on'"/>
        </c:if>
        <li><a href="<c:out value="${linkUrl}"/>" ${classOn}><c:out value="${eo.MENU_NM}"/></a></li>
        </c:forEach>
    </ul>
</div>
<!--네비+중메뉴-->
<div id="subArea" class="fullFrame">
    <ul id="subGnb" class="contentFrame">
        <li class="PdL0"><img src="/images/sub/icon_home.png" /></li>
        <li><c:out value="${menuLocationList[0].MENU_NM}"/></li>
        <li class="local Pd0 BgNone">
            <a href="#"><c:out value="${menuMap.MENU_NM}"/></a>
            <div class="subTree">
            <c:forEach items="${leftMenuList}" var="eo" varStatus="vs"> 
                <c:if test="${eo.MENU_LEVEL=='2'}">
                <c:if test='${eo.MENU_TYP == "D"}'>
		            <c:set var="linkUrl" value="/lime/link/?pMENU_ID=${eo.MENU_ID}"/>
		        </c:if>
		        <c:if test='${eo.MENU_TYP == "L"}'>
		            <c:set var="linkUrl" value="${eo.MENU_URL}?pMENU_ID=${eo.MENU_ID}"/>
		        </c:if>
                <c:set var="classOn" value=""/>
                <c:if test='${menuMap.MENU_ID==eo.MENU_ID}'>
                <c:set var="classOn" value=" class='on'"/>
                </c:if>
                <a href="<c:out value="${linkUrl}"/>" ${classOn}><c:out value="${eo.MENU_NM}"/></a>
                </c:if>
            </c:forEach>
            </div>
        </li>
    </ul>     
</div>
<div id="moddleWrap">
    <!--컨텐츠 영역-->
    <tiles:insertAttribute name="body" />
    <!--//컨텐츠 영역-->                 
</div>
<!--하단-->
<div id="footerArea" class="fullFrame MgT50">
    <div id="footerUtil" class="contentFrame">
        <a href="#">홈페이지이용안내</a>
        <a> l </a>
        <a href="#">개인정보취급방침</a>
        <a> l </a>
        <a href="#">개인정보처리방침</a>
        <a> l </a>
        <a href="#">저작권표준정책안</a>
    </div>
</div>
<div id="copyArea" class="fullFrame">
    <div id="address" class="contentFrame">
        (우)22689 인천광역시 서구 환경로 42(종합환경연구단지) 대표전화 : 000-000-0000 홈페이지안내 : 000-000-0000
        <p>Copyright ⓒ <b>National Institute of Envirmental Research</b>. All Rights Reserved.</p>
    </div>
</div>
</body>
</html>