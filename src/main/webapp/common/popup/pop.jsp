<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/common/tagCommon.jsp" %>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>충전인프라 정보시스템 : <c:out value="${vo.POPUP_NM}" /></title>
<!--[if lt ie 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->
<link href="/web/_css/BasicSet.css" rel="stylesheet" type="text/css" />
<link href="/web/_css/Hp_Common.css" rel="stylesheet" type="text/css" />
<link href="/web/_css/Hp_Popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    function SetCookie(str) { 
        var expires = new Date();
        expires.setDate(1+expires.getDate());
        expires.setHours(0,0,0,0);
        document.cookie = str+"=" + escape ("on") + "; expires=" + expires.toGMTString()+";";
        window.close();
    } 
    
    function closeWin(){
        SetCookie('pop${vo.POPUP_ID}');
    }
    
    function golink(link){
        if(link != undefined && link !=""){
            var openNewWindow = window.open("about:blank");
            openNewWindow.location.href=link;
        }
        return false;
    }
</script>
<style>
#M_Pop { width: 100%; height: 100%; letter-spacing: -1px; }
#M_Pop .M_info { height: 100%}
#M_Pop .M_info h2 { width: 100%; height: 30px; padding-top: 6px; text-indent: 23px; color: #FFF; font-family: 'NanumGothicBlod'; background: #14336a; font-size: 17px;text-align: center; }
#M_Pop .M_info div.NumDesc { background: #f7f7f7; border: 1px solid #d2d2d2; margin: 8px; padding: 15px; height: ${vo.P_HEIGHT-110}px; overflow-y: scroll;}
#M_Pop .popFoot { text-align: right;vertical-align: middle; }
#M_Pop .labelPop { vertical-align: middle;cursor: pointer;font-size: 14px;padding-right: 5px;}
#M_Pop .popContent {
    width: 100%;
    padding: 2px;
    text-align: left;
    height: auto;
    font-size: 14px;
    line-height: 26px;
    color: #000;
}
.M_info .Close {
    float: right;
    width: 12px;
    height: 12px;
    cursor: pointer;
    background: url(/mobile/images/sub/LayerPopClose.png) no-repeat;
    background-size: 12px 12px;
    margin-top: -25px;
    margin-right: 15px;
}
</style>
</head>
<body>
<div id="M_Pop">
    <div class="M_info"> 
        <h2>${vo.POPUP_NM}</h2>
        <div class="Close" onclick="self.close();"></div>
        <div class="NumDesc">
            <div>
            <c:if test="${!empty vo.IMG_NM}">
                <a href="" onclick="golink('${vo.LINK}');return false;"><img id="popimg" src="/file/viewImage/?atch_id=${vo.IMG_NM}" alt="<c:out value="${vo.POPUP_NM}" />"/></a></h1>
            </c:if>
            </div>
            <c:if test="${not empty vo.POPUP_CNTNS}">
            <div class="popContent">
                <html:xss><c:out value="${vo.POPUP_CNTNS}" escapeXml="false" /></html:xss>
            </div>
            </c:if>
        </div>
    </div>
    <div class="popFoot" id="foot">
        <input type="checkbox" onclick="closeWin()" id="pop" style="vertical-align: middle;"/>
        <label for="pop" class="labelPop">오늘하루 보이지 않기&nbsp;</label> 
    </div>
</div>
</body>
</html>
