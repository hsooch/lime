<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- This page "W3C" Validator Passed(XHTML 1.0 Strict & CSS 2.1) :: PostCoreaNet -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>라임컴퍼니</title>

<script type="text/javascript">

function fn_submit()
{
	var form1 = document.getElementById("form01");
	form1.submit();
}

function popupOpen() {
	var popUrl = "/lime/front/popup";
// 	var popOption = "width=370, height=340, resizable=yes, scrollbars=no, status=no;";	// width, height 수정
// 	window.open(popUrl, "", popOption);
	popup(popUrl,370,380,"");
}

</script>

</head>
<body>
<form name="form01" id="form01" method="post" action="/lime/front/">
<!--상단-->
<div id="loginWrap" class="contentFrame">
	<div class="Ltop">
        <h1><a href="#"></a></h1>
    </div>
    <div class="loignArea">
    	<h2>LOGIN</h2>    
    	    		    
        <div class="cover MgT30">        	
	        <div class="left">	        	
                <p>
                    <label>아이디</label>
                    <input class="W230" name="mbrId" type="text" id="member_id" value="lime" title="" />
                </p>
                <p class="MgT15">
                    <label>패스워드</label>
                    <input class="W230" name="passwd" type="password" id="password" value="1" title="" />
                </p>
            </div>
            <a class="right MgL15" href="javascript:fn_submit();">로그인</a>                       
        </div>  
                     
        <ul class="MgT30">
        	<li>계정 비밀번호 5회 입력 오류 시 사용이 제한됩니다.</li>
            <li class="MgT5">로그인이 안되는 경우 로그인 관련 FAQ를 참고하시기 바랍니다. <a class="btn03white MgL10" href="#">FAQ바로가기</a></li>
        </ul>
    </div>
    
    <!--회원가입, 비번찾기-->
    <div class="loginInfo">
    	<div class="section fl">
        	<h4 class="MgB15">회원가입하기</h4>
            <div class="infoTxt fl">회원가입을 하시면 라임컴퍼니 관리시스템을<br />이용하실 수 있습니다.</div>
            <!-- <a class="btn03white fr" href="javascript:window.open('/lime/front/popUp/userRegPop.jsp')">회원가입</a>-->
            <a class="btn03white fr" href="javascript:popupOpen();">회원가입</a>
        </div>
        <div class="section fr">
        	<h4 class="MgB15">아이디/비밀번호 찾기</h4>
            <div class="infoTxt fl">아이디 및 비밀번호를 잊으셨나요?<br />
            계정찾기를 이용해주세요.</div>
            <a class="btn03white fr" href="#">계정찾기</a>
        </div>
    </div>
</div>


<div id="footerArea" class="fullFrame MgT100">
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
</form> 
</body>
</html>