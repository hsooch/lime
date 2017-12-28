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
	setSelBox(96, $('#CTG_SEL'));
	setSelBox(97, $('#MENU_SEL'));
	setSelBox(98, $('#AREA_SEL'));
});

function setSelBox(no, selObj) {
	var sss;
	var url = "/lime/code/listAjax";
	var params = {
			no: no
	};
	var sucessFun = function(data) {
        var list = data.list;
        //removeOptionSelectBox(selObj);//전체 옵션 지우기
        removeOptionSelectBox(selObj,"Y");//첫번재옵션을 제외하고 지우기
        for(i in list)
        {
//        	console.log("list"+[i]+": " + list[i]);
        	var check = false;
        	sss= list[i];
        	if(list[i].CD == '${result.AREA_CD}') check = true;
        	if(list[i].CD == '${result.MENU_CD}') check = true;
        	if(list[i].CD == '${result.CATEGORY_CD}') check = true;
        	addOptionSelectBox(selObj, list[i].CD, check, list[i].CD_NM1);
        }
    };
    ajaxCall(url,params,sucessFun);
}

function cuRest() {
	var formData = document.getElementById('restForm');
	var form = $('#restForm');
	
	if (!validate(formData)) return false;

	//ajax 성공후 호출 함수
	var sucessFun = function(data) {
		alert('성공');
	};
	
    // ajax 실패시 호출 함수
    var errorFun = function() {
    	alert('실패');
    };
    
    ajaxCallForm(form, sucessFun, errorFun);
}

</script>

</head>
<body>
${init }
<form name="restForm" id="restForm" action="/restaurant/cu" method="post">
	<input type="hidden" name="RS_ID" value="${result.RS_ID }" />
	<input type="hidden" name="mode" value="<c:out value='${mode}'/>"/>
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
		                	<select id="AREA_SEL" name="AREA_SEL"  title="지역" checkNull>
		                		<option value="">---선택하세요----</option>
							</select>
						</td>
	                </tr>
	                <tr>
	                	<th>식당이름</th>
	                	<td class="AL PdL10 borR0"><input class="W160" name="REST_NM" type="text" id="REST_NM" title="식당이름" value="${result.RS_NM}" checkNull/></td>
	                </tr>
	                <tr>
	                	<th>분류</th>
	                	<td class="AL PdL10 borR0">      
		                	<select id="CTG_SEL" name="CTG_SEL" title="분류" checkNull>
		                		<option value="">---선택하세요----</option>
							</select>
						</td>
	                </tr>
	                <tr>
	                	<th>메뉴</th>
	                	<td class="AL PdL10 borR0">      
	                	<select id="MENU_SEL" name="MENU_SEL" title="메뉴" checkNull>
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
	                	<td class="AL PdL10 borR0"><input class="W160" name="REST_INFO" type="text" id="REST_INFO" title="소개"  value="${result.RS_INFO}" checkNull/></td>
	                </tr>
	                <tr>
	                   	<th>주소</th>
	                	<td class="AL PdL10 borR0"><input class="W200" name="REST_ADDR" type="text" id="REST_ADDR" title="주소"  value="${result.RS_ADDR}" checkNull/></td>
	                </tr>
	                <tr>
	                	<th>연락처</th>
	                	<td class="AL PdL10 borR0"><input class="W200" name="REST_PHONE" type="text" id="REST_PHONE" title="연락처"  value="${result.RS_PHONE}" checkNull phoneKr/></td>
	                </tr>
	                <tr>
	                	<th>영업시간</th>
	                	<td class="AL PdL10 borR0"><input class="W200" name="REST_TIME" type="text" id="REST_TIME" title="영업시간"  value="${result.RS_TIME}" checkNull/></td>
	                </tr>
	            </tbody>
	        </table>
	        <p class="btnArea">
	       		<c:if test="${empty result.RS_ID}">
	            	<a class="btn05_blue_gra" href="javascript:cuRest();">확인</a>
	            </c:if>
	            <c:if test="${!empty result.RS_ID}">
	            	<a class="btn05_blue_gra" href="javascript:cuRest();">수정</a>
	            </c:if>
	            <a class="btn06_gray_gra" href="javascript:self.opner=self;window.close();">닫기</a>
	        </p>
	    </div>
	    
	</div>
</form>
</body>
</html>