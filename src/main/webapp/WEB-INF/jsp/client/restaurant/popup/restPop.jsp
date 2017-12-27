<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagCommon.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>라임컴퍼니</title>
<!--[if lt ie 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->

<script type="text/javascript">

$(function () {
	codeSelect(96, $('#CTG_SEL'));
	codeSelect(97, $('#MENU_SEL'));
	codeSelect(98, $('#AREA_SEL'));
});

function insertRest() {
	var formData = $("#restForm");

	//ajax 성공후 호출 함수
	var sucessFun = function(data) {
		alert('입력 성공');
	};
	
    // ajax 실패시 호출 함수
    var errorFun = function() {
    	alert('입력 실패');
    };
    
    ajaxCallForm(formData, sucessFun, errorFun);
}

</script>

</head>
<body>
${init }
<form name="restForm" id="restForm" action="/restaurant/cu" method="post">
<div id="outskirts" class="W360">
	<h4 class="pic1">식당입력하기</h4>
    <div class="popConts MgT10">   
    
    	<table class="inventory" summary="">
            <colgroup>
                <col width="70">
                <col />
            </colgroup>
            <tbody>
                <tr>
                	<th>지역</th>
                	<td class="AL PdL10 borR0">     
	                	<select id="AREA_SEL" name="AREA_SEL">
	                		<option value="">---선택하세요----</option>
						</select>
					</td>
                </tr>
                <tr>
                	<th>식당이름</th>
                	<td class="AL PdL10 borR0"><input class="W160" name="REST_NM" type="text" id="REST_NM" title="" value="${result.RS_NM}"/></td>
                </tr>
                <tr>
                	<th>분류</th>
                	<td class="AL PdL10 borR0">      
	                	<select id="CTG_SEL" name="CTG_SEL">
	                		<option value="">---선택하세요----</option>
						</select>
					</td>
                </tr>
                <tr>
                	<th>메뉴</th>
                	<td class="AL PdL10 borR0">      
                	<select id="MENU_SEL" name="MENU_SEL">
                		<option value="">---선택하세요----</option>
					</select>
<%-- 					<html:selectList name='code97' list='code97' optionNames='--전체--' optionValues='' listValue='CD' listName='CD_NM1' selectedValue='' --%>
<%-- 						script=""/> --%>
                    </td>
                </tr>
<!--                 <tr> -->
<!--                 	<td class="AL PdL10 borR0"> -->
<!--                         <label><input id="INST_RADIO" disabled name="" type="radio" value="">기타</label> -->
<!--                         <input name="" type="text" disabled class="W170 MgL10" id="INST_ETC" title="" /> -->
<!--                     </td> -->
<!--                 </tr> -->
                <tr>
                	<th>소개</th>
                	<td class="AL PdL10 borR0"><input class="W160" name="REST_INFO" type="text" id="REST_INFO" title=""  value="${result.RS_INFO}" /></td>
                </tr>
                <tr>
                   	<th>주소</th>
                	<td class="AL PdL10 borR0"><input class="W200" name="REST_ADDR" type="text" id="REST_ADDR" title=""  value="${result.RS_ADDR}" /></td>
                </tr>
                <tr>
                	<th>연락처</th>
                	<td class="AL PdL10 borR0"><input class="W200" name="REST_PHONE" type="text" id="REST_PHONE" title=""  value="${result.RS_PHONE}" /></td>
                </tr>
                <tr>
                	<th>영업시간</th>
                	<td class="AL PdL10 borR0"><input class="W200" name="REST_TIME" type="text" id="REST_TIME" title=""  value="${result.RS_TIME}" /></td>
                </tr>
            </tbody>
        </table>
        <p class="btnArea">
            <a class="btn05_blue_gra" href="javascript:insertRest();">확인</a>
            <a class="btn06_gray_gra" href="javascript:self.opner=self;window.close();">닫기</a>
            
        </p>
    </div>
    
</div>
</form>
</body>
</html>