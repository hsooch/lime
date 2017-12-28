<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/tagCommon.jsp" %>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>식당 통합 DB 관리시스템</title>
<!--[if lt ie 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->
<script type="text/javascript">

$(function () {
	codeSelect(96, $('#CTG_SEL'));
	codeSelect(97, $('#MENU_SEL'));
	codeSelect(98, $('#AREA_SEL'));
});

// 입력
function writeRestaurant(id) {
	var popUrl;
	
	if (id != undefined) {
		popUrl = "/restaurant/popup?RS_ID=" + id;
	} else {
		popUrl = "/restaurant/popup";
	}
	popup(popUrl,370,380,"");
}

// 수정
function updateRestaurant() {
}

// 삭제
function deleteRestaurant() {
	var chkedCnt = getMultiCheckedNum('CHK_S');
	var chkedStr = getMultiCheckedString('CHK_S', ',');
// 	var nonChkedStr = getMultiNonCheckedString('CHK_S', '');
	
	var url = '/restaurant/delete';
	var params = {
			RS_ID : chkedStr
// 			RS_ID : 1039
	};
	var sucessFun = function(data) {
		alert(chkedCnt + '개 삭제함');
		location.reload();
	}
	
	ajaxCall(url, params, sucessFun);
}

// 검색
var sss;
function searchRestaurant() {
	var ctg = $('#CTG_SEL').val();
	var menu = $('#MENU_SEL').val();
	var area = $('#AREA_SEL').val();
	var url = '/restaurant/search/ajax';
	var params = {
			CATEGORY_CD : ctg,
			MENU_CD : menu,
			AREA_CD : area
	};
	var sucessFun = function(data) {
		var dataList = data.list;
        var tableObj = $("#tableResult");
        //표출데이터 컬럼
        var cols = ["CHK", "RS_ID", "RS_AREA", "RS_NM", "RS_CATEGORY", "RS_MENU", "RS_INFO", "RS_ADDR", "RS_PHONE", "RS_TIME"];
        //이벤트등록 - 이벤트가 필요할 경우 
        var colEvnts = ["" ,"writeRestaurant","", "", "", "", "", "", "", ""];
        addTableTbody(tableObj,cols, colEvnts, dataList, "RS_ID","", "POP");
//         addTableTbody(tableObj,cols, colEvnts, dataList, "RS_ID");
        var totCnt = dataList.length;
        $("#tableResultCnt").text(totCnt);
//       	$("input[name='CHK_S']").addClass('item');	// 공통 부분이므로 common.js 에 추가함
	};
	ajaxCall(url,params,sucessFun);
}

</script>
</head>	
<body>
<!--상단-->
<div id="moddleWrap">
    <!--타이틀-->
    <div id="titleArea" class="contentFrame">
        <h2>식당검색</h2>
        <p>식당과 관련된 검색조건을 단계별로 작성하실 경우 원하시는 데이터를 조회하실 수 있습니다.</p>
    </div>
    <!--검색조건-->
    <h3 class="contentFrame MgT10 MgB10 icon01">검색조건</h3>
    <div id="contArea" class="fullFrame">
        <div id="mainCont" class="contentFrame">
            <!--왼쪽 검색조건-->
            <div class="contDivi W100p">
                <table>
                    <colgroup>
                        <col width="120">
                        <col />
                        <col width="120">
                        <col />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th><h4>자료구분</h4></th>
                            <td colspan="3">
                                <div class="box">
<%--                                 <html:selectList name='CTG_SEL' list='code96' optionNames='--분류선택--' optionValues='' listValue='CD' listName='CD_NM1' selectedValue='' --%>
<%-- 									script=""/> --%>
<%--                                 <html:selectList name='MENU_SEL' list='code97' optionNames='--메뉴선택--' optionValues='' listValue='CD' listName='CD_NM1' selectedValue='' --%>
<%-- 									script=""/> --%>
                                    <select id="CTG_SEL" class="W150">
                                        <option value=''>---분류 선택---</option>
                                    </select>
                                    <select id="MENU_SEL" class="W150">
                                        <option value=''>---메뉴 선택---</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
<!--                             <th><h4>검색기간</h4></th> -->
<!--                             <td class="PdR80"> -->
<!--                                 <div class="box"> -->
<!--                                     <select class="W80"> -->
<!--                                         <option>2014</option> -->
<!--                                     </select> 년 -->
<!--                                     <select class="W60"> -->
<!--                                         <option>1</option> -->
<!--                                     </select> 회차 ~ -->
<!--                                     <select class="W80"> -->
<!--                                         <option>2014</option> -->
<!--                                     </select> 년 -->
<!--                                     <select class="W60"> -->
<!--                                         <option>1</option> -->
<!--                                     </select> 회차 -->
<!--                                 </div> -->
<!--                             </td> -->
                            <th><h4>지역선택</h4></th>
                            <td>
                                <div class="box">
<%--                                    <html:selectList name='AREA_SEL' list='code98' optionNames='--지역 선택--' optionValues='' listValue='CD' listName='CD_NM1' selectedValue='' --%>
<%-- 										script=""/> --%>
 									<select id="AREA_SEL" class="W150">
                                        <option value=''>---지역---</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <a class="btn05_blue_gra MgT10 fr" href="javascript:searchRestaurant();">검색</a>
            </div>
        </div>
    </div>
    
    
    <!--접기, 열기-->
    <div id="btn" class="fullFrame">
        <p class="btn_on AC contentFrame"></p>
        <p class="btn_off AC contentFrame" style="display: none;"></p>
    </div>
    
    
    <!--검색결과-->
    <div class="contentFrame">
        <h3 class="MgT40 MgB10 icon02">검색 결과</h3>
        <ul class="tabMenu">
            <li class="tab1 on">등록현황</li>
            <li class="bin" style="width: 828px;"></li>
        </ul>
        
        <div>
            <!--등록현황-->
            <div id="result1" class="resultArea">
                <div class="resultUtil">
                    <span>검색결과 : <b id="tableResultCnt">0</b>건</span>
                    <a class="btn01gray MgL3" href="javascript:deleteRestaurant();">삭제</a>
                    <a class="btn02blue" href="javascript:writeRestaurant();">입력</a>
                </div>
                <table summary="등록현황 검색결과" id="tableResult">
                    <colgroup>
                        <col width="50">
                        <col width="50">
                        <col />
                        <col />
                        <col />
                        <col />
                        <col />
                        <col />
                        <col />
                        <col />
                        <col />
                    </colgroup>
                    <thead>
                        <tr>
                            <th><input name="" type="checkbox" value="" class="checkAll"></th>
                            <th>번호</th>
                            <th>지역</th>
                            <th>식당이름</th>
                            <th>분류</th>
                            <th>메뉴</th>
                            <th>소개</th>
                            <th>주소</th>
                            <th>연락처</th>
                            <th>영업시간</th>
                        </tr>
                    </thead>
                    
<!--                     <tbody> -->
<!--                         <tr> -->
<!--                             <td><input name="" type="checkbox" value="" class="item"></td> -->
<!--                             <td></td> -->
<!--                             <td>오류</td> -->
<!--                             <td>하천</td> -->
<!--                             <td>부착돌말류</td> -->
<!--                             <td>일반정보</td> -->
<!--                             <td>2014</td> -->
<!--                             <td>2</td> -->
<!--                             <td>부착_2014_02.xlsx</td> -->
<!--                             <td>2015.09-17 13:02</td> -->
<!--                         </tr> -->
<!--                     </tbody> -->
                </table> 
            </div>
        </div>
    </div>
</div>

</body>
</html>