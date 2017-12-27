<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagCommon.jsp" %>
<script type="text/javascript" src="/js/excel/jquery.base64.js"></script>
<script type="text/javascript" src="/js/excel/jquery.battatech.excelexport.min.js"></script>
<script>
$(function () {
	testSelectBox();
});


//ajax 호출 및 selectBox 생성
var sss ;
function testSelectBox()
{
    var url = "/lime/code/listAjax";
    var params = {
    	    sqlNo : 1, pa1 : 1
    };
    //ajax 성공후 호출 함수
    var sucessFun = function(data) {
        var list = data.list;
        var selObj = $("#testSel");
        //removeOptionSelectBox(selObj);//전체 옵션 지우기
        removeOptionSelectBox(selObj,"Y");//첫번재옵션을 제외하고 지우기
        for(i in list)
        {
        	var check = false;
        	sss= list[i];
        	//if(list[i].CD == '2') check = true; //선택값이 필요한 경우 사용
        	addOptionSelectBox(selObj, list[i].CD, check, list[i].CD_NM);
        }
    };
    ajaxCall(url,params,sucessFun);
}

function goExcel(){
	location.href="/lime/test/excel?no=47";
}

//조회
function goSearch(){
	var url = "/lime/test/listAjax";
    var params = {
        no : 47
    };
    //ajax 성공후 호출 함수
    var sucessFun = function(data) {
        var dataList = data.list;
        var tableObj = $("#testTable");
        //표출데이터 컬럼
        var cols = ["NO","CD", "CD_NM1", "CD_NM2", "CD_NM3", "CD_NM4", "CD_NM5", "CD_NM6", "CD_NM7", "RIV_YN", "EST_YN", "DPI_YN"];
        //이벤트등록 - 이벤트가 필요할 경우 
        var colEvnts = ["","alert('sss');return false;", "", "", "", "", "", "", "", "", "", ""];
        addTableTbody(tableObj,cols, colEvnts, dataList);
        var totCnt = dataList.length;
        $("#testTableCnt").text(totCnt);
    };
    ajaxCall(url,params,sucessFun);
}

</script>
<div>
<br/><br/><br/>
<select id="testSel">
<option value="">---선택하세요----</option>
</select>
<br/><br/><br/>
<html:selectList name='code' list='codeList'  optionNames='--전체--' optionValues='' listValue='CD' listName='CD_NM1' selectedValue='${param.code}' defaultValue="1" title="코드" script="onchange='alert(1);return false;'"/>
<br/><br/><br/>
<html:selectList name='searchType' optionValues='ALL|TITL|NM|ARTC_CNTNS' optionNames='전체|제목|작성자|내용' selectedValue='${param.searchType}' title="검색조건" script="class='W120'" />
<br/><br/><br/>
<html:radio name="TEST_YN" value='Y|N' text='사용|미사용' checkedValue='${param.NOTICE_YN}' defaultValue='N' space="&nbsp;" id='Yes2|No2'/>
<br/><br/><br/>
<br/><br/><br/>
------------------------------------------------------------------------------
<br/><br/><br/>
<div class="box">
    <select class="W60" name="p1" id="p1" title="년도">
        <c:forEach begin="2008" end="${curYY}" varStatus="st">
            <option value="${curYY-st.count+1}">${curYY-st.count+1}</option>
        </c:forEach>
    </select>년
    <select class="W60" name="p2" id="p2" title="회차">
        <c:forEach begin="1" end="2" varStatus="st">
            <option value="${st.count}">${st.count}</option>
        </c:forEach>
    </select>회차  <input type="button" value="조회" onclick="goSearch();return false;">
</div>
<div class="resultArea" id="result1" style="width: 70%">
                <div class="resultUtil">
                    <span>검색결과 : <b id="testTableCnt">0</b>건</span>
                    <a class="btn02blue" href="#" id="btnExport" onclick="goExcel();return false;">엑셀 다운로드</a>
                </div>
                <table summary="조사자료 검색결과" id="testTable">
                    <colgroup>
                        <col width="50">
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                    </colgroup>
                    <thead>
                        <tr>
                            <th rowspan="2">번호</th>
                            <th rowspan="2">조사연도</th>
                            <th rowspan="2">조사구간명</th>
                            <th colspan="6">위치</th>
                            <th rowspan="2">하천형</th>
                            <th rowspan="2">하폭</th>
                            <th>등급</th>
                        </tr>
                        <tr>
                            <th>대권역</th>
                            <th>수계</th>
                            <th>중권역</th>
                            <th>소권역</th>
                            <th>하천명</th>
                            <th>본류구분</th>
                            <th>TDI</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="12">자료가 없습니다.</td>
                    </tr>
                    </tbody>
                </table> 
            </div>
            <br/><br/><br/>
            <form name="frm" method="post" onsubmit="return validate(this)">
            <p>제목 :<input type="text" name="sss" id="sss" title="제목" checkNull></p>
            <p>셀렉트 :<select class="W60" name="p1" id="p1" title="셀렉트" checkNull><option value="">--선택--</option></select></p>
            <input type="submit" value="저장" class="btn01" />
            </form>
</div>

