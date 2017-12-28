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
// MBR_ID(아이디), MBR_NM(이름), PASSWD(비번), INST_CD(소속기관코드), INST_ETC(소속기관기타), DEPT_NM(담당부서), INST_ADM_CD(소속지역코드), EMAIL(이메일) -> 사용자입력
// AUTH_CD(권한코드), REG_DT(최초등록일), CHG_DT(최종등록일), CONF_YN(승인여부), REG_NM(등록자) -> 관리자입력

$(function () {
	codeSelect(98, $('#AREA_SEL'));
	codeSelect(99, $('#INST_SEL'));
	
	$("#INST_SEL").change(function(){
		var selected = $("#INST_SEL option:selected").text();
		if (selected === '기타') {
			$('#INST_RADIO').attr('disabled', false);
			$('#INST_RADIO').prop('checked', true);
			$('#INST_ETC').attr('disabled', false);
		}
		else {
			$('#INST_RADIO').attr('disabled', true);
			$('#INST_RADIO').prop('checked', false);
			$('#INST_ETC').attr('disabled', true);
			$('#INST_ETC').val('');
		}
	});
});

// 아이디 중복체크
var dplChked = false;
function dplChk() {
	var url = "/idDuplChk";
	var mbr_id = $('#MBR_ID').val().trim();
    var params = {
    	    MBR_ID : mbr_id		// 대소문자 구분
    };
    //ajax 성공후 호출 함수
    var sucessFun = function(data) {
        var result = data.status;	// count 1 or 0
        
        if (result === 1) {
        	alert('아이디가 중복됩니다');
        	dplChked = false;
        }
        else  {
        	alert('사용하세요.');
        	dplChked = true;
        }
    };
    ajaxCall(url,params,sucessFun);
}

// 회원가입
function registMember() {
// 	var formData = $("#userForm");
	var formData = document.getElementById('userForm');
	var form = $('#userForm');
	
	if (!validate(formData)) return false;
	
	//ajax 성공후 호출 함수
	var sucessFun = function(data) {
        var result = data.status;	// 1 or 0
        
        if (result === 'ok') {
        	alert('가입 성공');
        }
        location.reload();
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
<form id="userForm" name="userForm" action="/signUp" method="post">
<input type="hidden" id="AUTH_CD" name="AUTH_CD" value="A9" />
<input type="hidden" id="REG_DT" name="REG_DT" value="" />
<input type="hidden" id="CHG_DT" name="CHG_DT" value="" />
<input type="hidden" id="CONF_YN" name="CONF_YN" value="0" />
<input type="hidden" id="REG_NM" name="REG_NM" value="" />
	<div id="outskirts" class="W360">
		<h4 class="pic1">회원가입하기</h4>
	    <div class="popConts MgT10">   
	    
	    	<table class="inventory" summary="아이디, 비밀번호, 이름, 생년월일, 소속기관, 담당업무로 구성된 표입니다.">
	            <colgroup>
	                <col width="70">
	                <col />
	            </colgroup>
	            <tbody>
	                <tr>
	                	<th>아이디</th>
	                	<td class="AL PdL10 borR0">
	                    	<input class="W160" name="MBR_ID" type="text" id="MBR_ID" title="아이디" value="" checkNull idChk/>
	                        <a class="btn04_overlap" href="#" onclick="dplChk();return false;"><span></span>중복확인</a>
	                    </td>
	                </tr>
	                <tr>
	                	<th>비밀번호</th>
	                	<td class="AL PdL10 borR0">
	                		<input class="W160" name="PASSWD" type="password" id="PASSWD" title="비밀번호" checkNull passChk />
                		</td>
	                </tr>
	                <tr>
	                	<th>이름</th>
	                	<td class="AL PdL10 borR0">
	                		<input class="W120" name="MBR_NM" type="text" id="MBR_NM" title="이름" checkNull />
	                	</td>
	                </tr>
	                <tr>
	                	<th rowspan="2">소속기관</th>
	                	<td class="AL PdL10 borR0">      
	                	<select name="INST_SEL" id="INST_SEL" title="소속기관" checkNull>
	                		<option value="">---선택하세요----</option>
						</select>
	<%-- 					<html:selectList name='code99' list='code99' optionNames='--전체--' optionValues='' listValue='CD' listName='CD_NM1' selectedValue='' --%>
	<%-- 						script="onchange='alert(1);return false;'"/> --%>
	                    </td>
	                </tr>
	                <tr>
	                	<td class="AL PdL10 borR0">
	                        <label><input id="INST_RADIO" disabled name="" type="radio" value="">기타</label>
	                        <input name="" type="text" disabled class="W170 MgL10" id="INST_ETC" title="" />
	                    </td>
	                </tr>
	                <tr>
	                	<th>담당부서</th>
	                	<td class="AL PdL10 borR0">
	                		<input class="W160" name="DEPT_NM" type="text" id="DEPT_NM" title="담당부서" checkNull />
	                	</td>
	                </tr>
	                <tr>
	                	<th>소속지역</th>
	                	<td class="AL PdL10 borR0">        
		                	<select name="AREA_SEL" id="AREA_SEL" title="소속지역" checkNull>
		                		<option value="">---선택하세요----</option>
							</select>        	    
	                    </td>
	                </tr>
	                <tr>
	                	<th>이메일</th>
	                	<td class="AL PdL10 borR0">
	                		<input class="W200" name="EMAIL" type="text" id="EMAIL" title="이메일" checkNull emailkr />
	                	</td>
	                </tr>
	            </tbody>
	        </table>
	        <p class="btnArea">
	            <a class="btn05_blue_gra" href="javascript:registMember();">확인</a>
	            <a class="btn06_gray_gra" href="javascript:self.opner=self;window.close();">닫기</a>
	        </p>
	    </div>
	</div>
</form>
</body>
</html>