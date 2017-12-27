
//페이징용
var pageBlock = 10;
var navigatorNum = 10;


//ajax호출 공통 함수 (비동기식)
function ajaxCall(url,params,sucessFun,errorFun){
	wrapWindowByMask();
	$.ajax({
		type: "POST",
		url: url,
		data: params,
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8", // AJAX contentType		
		success: function(data){
			if(data.errorCode != undefined && data.errorCode == '401'){//세션정보 없음
				alert(data.message);
				if(window.opener != undefined){
					window.opener.location.href = "/lime/login";
					self.close();
				}else{
					location.href = "/lime/login";
				}
				if(typeof(errorFun) == 'function') errorFun();
				closeWindowByMask();
				return;
			}
			
			if(data.message != undefined && data.message != ''){
				alert(data.message);
			}
			
			if(typeof(sucessFun) == 'function') sucessFun(data);
			closeWindowByMask();
		}, 
		error : function(xhr, status, error){
			alert("시스템 오류가 발생하였습니다."+error);
			if(typeof(errorFun) == 'function') errorFun();
			closeWindowByMask();
		}
	});
}

//ajax호출 공통 함수(동기식)
function ajaxCallSync(url,params,sucessFun,errorFun){	
	wrapWindowByMask();
	$.ajax({
		type: "POST",
		url: url,
		data: params,
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8", // AJAX contentType
		async: false,
		success: function(data){
			if(data.errorCode != undefined && data.errorCode == '401'){//세션정보 없음
				alert(data.message);
				if(window.opener != undefined){
					window.opener.location.href = "/lime/login";
					self.close();
				}else{
					location.href = "/lime/login";
				}
				if(typeof(errorFun) == 'function') errorFun();
				closeWindowByMask();
				return;
			}
			
			if(data.message != undefined && data.message != ''){
				alert(data.message);
			}
			
			if(typeof(sucessFun) == 'function') sucessFun(data);
			closeWindowByMask();
		}, 
		error : function(xhr, status, error){
			alert("시스템 오류가 발생하였습니다."+error);
			if(typeof(errorFun) == 'function') errorFun();
			closeWindowByMask();
		}
	});
}


//ajax호출 공통 함수(map호출시 널값, jsondatatype 에러, txt추가)
function ajaxCallSyncText(url,params,sucessFun,errorFun){	
	wrapWindowByMask();
	$.ajax({
	  type: "POST",
	  url: url,
	  data: params,
	  dataType: 'text',
	  contentType: "application/x-www-form-urlencoded; charset=UTF-8", // AJAX contentType
	  async: false,
	  success: function(data){
	  	if(data.errorCode != undefined && data.errorCode == '401'){//세션정보 없음
			  alert(data.message);
			  if(window.opener != undefined){
			  	window.opener.location.href = "/lime/login";
			  	self.close();
			  }else{
			  	location.href = "/lime/login";
			  }
			  if(typeof(errorFun) == 'function') errorFun();
				closeWindowByMask();
			  return;
		  }
	  	
	  	if(data.message != undefined && data.message != ''){
	  		alert(data.message);
	  	}
	  	
	  	if(typeof(sucessFun) == 'function') sucessFun(data);
		closeWindowByMask();
	  }, 
	  error : function(xhr, status, error){
		  alert("시스템 오류가 발생하였습니다."+error);
		  if(typeof(errorFun) == 'function') errorFun();
			closeWindowByMask();
	  }
  });
}

//form 인서트 처리, 동기식
function ajaxCallForm(form,successFun, errorFun){
	var url = form.attr("action");
	var data= form.serializeArray();
	$.ajax({
		url : url,
		type : "POST",
		data : data,
		async: false,
		success : function(data){
			if(data.errorCode != undefined && data.errorCode == '401'){//세션정보 없음
				alert(data.message);
				if(window.opener != undefined){
					window.opener.location.href = "/lime/login";
					self.close();
				}else{
					location.href = "/lime/login";
				}
				if(typeof(errorFun) == 'function') errorFun();
				closeWindowByMask();
				  return;
			  }
			if(data.message != undefined && data.message != ''){

				alert(data.message);
		  	}
			
			if(typeof(successFun) == 'function') {
				successFun(data);
			}
			closeWindowByMask();
		}, 
		error : function(xhr, status, error){
			alert("시스템 오류가 발생하였습니다."+error);
			if(typeof(errorFun) == 'function') errorFun();
			closeWindowByMask();
		  }
	});
}

// 셀렉트박스 값
function codeSelect(no, selObj) {
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
        	//if(list[i].CD == '2') check = true; //선택값이 필요한 경우 사용
        	addOptionSelectBox(selObj, list[i].CD, check, list[i].CD_NM1);
        }
    };
    ajaxCall(url,params,sucessFun);
}

//selectBox 전부 삭제
//extOne : 'Y' 경우 처번째엘리먼트 남기기
function removeOptionSelectBox($element,extOne){
	if(extOne == 'Y'){
		$element.find('option:gt(0)').remove();	
	}else{
		$element.find("option").remove();	
	}
}
//selectBox 추가
function addOptionSelectBox($element, strValue, bSelected, strText){
	if( bSelected )
		$element.append('<option value="'+strValue+'" selected>'+strText+'</option>');
	else
		$element.append('<option value="'+strValue+'">'+strText+'</option>');
}

//selectBox 선택된 값
function getSelectVal($element){
	$element.find("option:selected").val();
}
//selectBox 선택된 텍스트값
function getSelectTxt($element){
	$element.find("option:selected").text();
}

/**
 * $element : 해당 테이블
 * cols : 테이블 표출 컬럼들
 * checkBox관련추가 항목: keyName : dataList에서 키값으로 사용할 항목명
 * 						,checkName :checkBox   name으로 사용할 명   
 * dataList
 */
function addTableTbody($element, cols, colEvnts, dataList, keyName, checkName, btnName){	 
	$element.find("tbody tr").remove();
	if(dataList.length == 0){
		var tr = $("<tr></tr>");
		tr.append("<td colspan='"+cols.length+"'>자료가 없습니다.</td>");
		$element.append(tr);
		return;
	}
	
	if(checkNull(checkName) == ""){
		checkName = "CHK_S";
	}		
	
	if(checkNull(btnName) == ""){
		btnName = "BTN_S";
	}
	
	
	for (var j = 0; j < dataList.length; j++) {
		var colList = dataList[j];
		var keyValue = checkNull(colList[keyName]);
		var tr = $('<tr></tr>');
		//var bNm="개별입력";
	   
		console.log(keyValue);
		
		for (var k = 0; k < cols.length; k++) {
			var columnName = cols[k];
			console.log(colEvnts[k]);
			
			if(columnName=='CHK' && checkNull(keyValue)!="")
		   	{
				if(colEvnts[k] != undefined && colEvnts[k] !='' && colEvnts[k] != null){
					tr.append('<td><input name="'+checkName+'" type="checkbox" value="'+keyValue+'" onClick="'+colEvnts[k]+'" class="item"></td>');										
				}else{
					tr.append('<td><input name="'+checkName+'" type="checkbox" value="'+keyValue+'" class="item"></td>');
				}
		   	}
			else if(columnName=='BTN' && checkNull(keyValue)!="")
		   	{
				if(colEvnts[k] != undefined && colEvnts[k] !='' && colEvnts[k] != null){
					tr.append('<td><input name="'+btnName+'_'+k+'" type="button" value="'+checkNull(colList[columnName])+'"  onClick="'+colEvnts[k]+'(\''+keyValue+'\')"></td>');										
				}else{
					tr.append('<td><input name="'+btnName+'_'+k+'" type="button" value="'+checkNull(colList[columnName])+'" ></td>');
				}
		   	}
			else
			{
				if(colEvnts[k] != undefined && colEvnts[k] !='' && colEvnts[k] != null  && btnName != "POP"){
					tr.append('<td><a href="" onClick="'+colEvnts[k]+'">'+checkNull(colList[columnName])+'</a></td>');
				}else if(colEvnts[k] != undefined && colEvnts[k] !='' && colEvnts[k] != null && btnName == "POP"){ //popup
					tr.append('<td><a href="#" onClick="'+colEvnts[k]+'(\''+keyValue+'\')">'+checkNull(colList[columnName])+'</a></td>');
				}else{
					tr.append('<td>' + checkNull(colList[columnName]) + '</td>');
				}
			}
		}
		$element.append(tr);
	}
}

function popup(url,w,h,s,target){
	var l, t, objPopup
	if(target == 'undefined' || target=='' || target==null) {
		var target='win1';
	}
	l = (screen.width-w)/2;
	t = (screen.height-h)/2;
	if(s==1 || s=="Y")
		objPopup  = window.open(url,target,'width='+w+',height='+h+',left='+l+',top='+t+',resizable=0,scrollbars=1');
	else if (s=="" || s==0 || s=="N" || !s || s=="0" )
		objPopup = window.open(url,target,'width='+w+',height='+h+',left='+l+',top='+t+',resizable=0,scrollbars=0,status=0');
	else
		objPopup = window.open(url,target,'width='+w+',height='+h+',left='+l+',top='+t+',resizable=1,menubar=1,toolbar=1,scrollbars=1,status=1');
	if (objPopup == null) {
		alert("차단된 팝업창을 허용해 주십시오.");
	}
	return objPopup;
}

//jquery 달력
function addDatePicker(obj, dateformat) {
	if(dateformat == "" || dateformat == undefined){
		dateformat = 'yy-mm-dd';
	}
	$( obj ).datepicker({
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월' ],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		dateFormat: dateformat,   // 날짜형식 = 20130329
		autoSize: false,	// 자동리사이즈 (false 이면 상위 정의에 따름)
		changeMonth: true,   // 월변경 가능
		changeYear: true,   // 연변경 가능
		showMonthAterYear: true,  // 년 위에 월 표시
		showOn: 'both',	// 엘리먼트와 이미지 동시사용 (both, button)
		buttonImageOnly: true,   // 이미지 표시
		buttonText: '달력',   // 버튼 텍스트 표시
		buttonImage: '/cms/_images/common/icoCalendar.gif',  // 이미지 주소
		showMonthAfterYear: true,
		showButtonPanel: true
		//yearRange: 'c-99:c+99',  // 1990~2020년 까지
		//maxDate: '+6Y',	// 오늘 부터 6년 후까지만.  +0d 오늘 이전 날짜만 선택
		//minDate: '-30d'
	});
	
	$(".ui-datepicker-trigger").css("vertical-align","middle");
	$(".ui-datepicker-trigger").css("padding-left","2px");
	$(".ui-datepicker").css("font-size","14px");
}

//input 객체들 구분자로 문자열 반환
function objToString(obj){
	var len = obj.length;
	var ele = obj;
	var str="";
	if(obj.length > 1){
		for(var i=0; i < len; i++){
			if (i > 0) {
				str += "|";
			}
			str += ele[i].value;
		}
	}else{
		str = obj.value;
	}
	return str;
}

function gfn_number_set(val){
	val = String(val);
	if(val.length < 2){
	val = "0"+val;	
	}
	return val
}


function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();

	if (n.length < digits) {
		for (i = 0; i < digits - n.length; i++)
			zero += '0';
	
	}
	return zero + n;
}

function GetCurYYYY(){
	var d = new Date();
	
	return leadingZeros(d.getFullYear(), 4);
}

function GetCurMM(){
	var d = new Date();
	
	return leadingZeros(d.getMonth() + 1, 2);
}

function GetCurDD(){
	var d = new Date();
	
	return leadingZeros(d.getDate(), 2);
}

function GetCurDate(){
	var d = new Date();
	
	var date = '';
	
	date += leadingZeros(d.getFullYear(), 4) + '-';
	date += leadingZeros(d.getMonth() + 1, 2) + '-';
	date += leadingZeros(d.getDate(), 2) + ' ';
	date += leadingZeros(d.getHours(), 2) + ':';
	date += leadingZeros(d.getMinutes(), 2) + ':';
	date += leadingZeros(d.getSeconds(), 2);

	return date;
}

/**
* 체크된 개수
* @param itemName 체크박스명
*/
function getMultiCheckedNum(itemName){
	var obj = document.getElementsByName(itemName);
	if(typeof(obj) == 'undefined'){
		return 0;
	}
	var chkedCnt=0;

	for(var i=0; i<obj.length; i++){
		if(obj[i].checked)
			chkedCnt++;
	}
	return chkedCnt;
}
/**
* 체크된 항목들 값을 취합해서 리턴
* @param itemName 체크박스명
* @param delim	구분자
*/
function getMultiCheckedString(itemName, delim){
	var obj = document.getElementsByName(itemName);
	var div = delim;
	if(div=="")
		div="|";
	var chkCnt=0;
	if(typeof(obj) == 'undefined'){
		return "";
	}
	var s="";
	var n=0;
	for(var i=0; i<obj.length; i++){
		if(obj[i].checked){
			if(n>0)
				s += div;
			s += obj[i].value;
			n++;
		}
	}
	return s;
}

function getMultiNonCheckedString(itemName, delim){
	var obj = document.getElementsByName(itemName);
	var div = delim;
	if(div=="")
		div="|";
	var chkCnt=0;
	if(typeof(obj) == 'undefined'){
		return "";
	}
	var s="";
	var n=0;
	for(var i=0; i<obj.length; i++){
		if(obj[i].checked){
			
		}else{
			if(n>0)
				s += div;
			s += obj[i].value;
			n++;
			
		}
	}
	return s;
}

function getMultiAllCheckedString(itemName, delim){
	var obj = document.getElementsByName(itemName);
	var div = delim;
	if(div=="")
		div="|";
	var chkCnt=0;
	if(typeof(obj) == 'undefined'){
		return "";
	}
	var s="";
	var n=0;
	for(var i=0; i<obj.length; i++){
		if(n>0)
			s += div;
		s += obj[i].value;
		n++;
			
	}
	return s;
}

//엑셀 스타일의 반올림 함수 정의
function roundXL(n, digits) {
	if (digits >= 0)
		return parseFloat(n.toFixed(digits)); // 소수부 반올림

	digits = Math.pow(10, digits); // 정수부 반올림
	var t = Math.round(n * digits) / digits;

	return parseFloat(t.toFixed(0));
}

function checkNull(data){
	var chr = '';
	if(data == null || data == ""){
		return chr;
	}else{
		return data;
	}   
}


//테이블 정렬. tableNm = "tableName", tCount = 정렬할 행수 ( colspan으로 묶인 경우 처리가 안돼서 추가)
//정확히 처리 위해서는 plugin사용 혹은 하드코딩 필요
function setSortTable(tableNm, tCount){
	--tCount;
	$('#'+tableNm+'> thead th').each(function (column) {
		$(this).click(function() {
			if($(this).is('.asc')) {
				$(this).removeClass('asc');
				$(this).addClass('desc');
				sortdir=-1;
			} else {
				$(this).addClass('asc');
				$(this).removeClass('desc'); sortdir=1;
			}
			$(this).siblings().removeClass('asc');
			$(this).siblings().removeClass('desc');
	
			var rec = $('#'+tableNm).find('tbody>tr').get();
			rec.sort(function (a, b) {
				var val1 = $(a).children('td').eq(column).text().toUpperCase();
				var val2 = $(b).children('td').eq(column).text().toUpperCase();
				if($.isNumeric(val1) && $.isNumeric(val2)){
					val1 = fillzero(val1,7);
					val2 = fillzero(val2,7);
					return (val1 < val2)?-sortdir:(val1>val2)?sortdir:0;
				}else{
					return (val1 < val2)?-sortdir:(val1>val2)?sortdir:0;
				}
			});
			$.each(rec, function(index, row) {
				var tbody1 = $('#'+tableNm+' tbody');
				tbody1.append(row);
			 });
		});
		if(column == tCount) return false;
	});
}

//숫자 일시 왼쪽 채우기 
function fillzero(obj, len) {
	obj= '00000000'+obj;
	return obj.substring(obj.length-len);
}

//글자 바이트체크
function chkStrLength(str) {
	var str;
	var han_count=0;
	han_count = (escape(str)+"%u").match(/%u/g).length-1;
return (str.length + han_count);
}

//글자 바이트체크
function getByteLength( data ) {
    var len = 0;
    var str = data.substring(0);
    if ( str == null ) return 0;
    for(var i=0; i < str.length; i++) {
        var ch = escape(str.charAt(i));

        if( ch.length == 1 ) len++;
        else if( ch.indexOf("%u") != -1 )  len += 2;//Db가 한글을 3byte로 인식하여 2->3
        else if( ch.indexOf("%") != -1 ) len += ch.length/3;
    }
    return len;
}



function wrapWindowByMask() {
	//화면의 높이와 너비를 구한다.
	var maskHeight = $(document).height();  
	var maskWidth = window.document.body.clientWidth;
	 
	var mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
	var loadingImg = '';
	 
	loadingImg += "<div id='loadingImg' style='position:absolute; left:50%; top:40%; display:none; z-index:10000;'>";
	loadingImg += " <img src='/images/sub/viewLoading.gif'/>"; 
	loadingImg += "</div>";   
 
	//화면에 레이어 추가 
	$('body')
		.append(mask)
		.append(loadingImg)
	   
	//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
	$('#mask').css({
			'width' : maskWidth
			, 'height': maskHeight
			, 'opacity' : '0.3'
	});  
 
	//마스크 표시
	$('#mask').show();	
 
	//로딩중 이미지 표시
	$('#loadingImg').show();
}

function closeWindowByMask() {
	$('#mask, #loadingImg').hide();
	$('#mask, #loadingImg').remove();  
}



function drawPaging($elementDiv, startIndex, totalCount, pageBlock, navigatorNum){
	
	$elementDiv.empty();
	
	var lastPageNum		= Math.floor((totalCount-1)/pageBlock) + 1;
	var previewPageNum  = startIndex == 1 ? 1 : startIndex-1;
	var nextPageNum		= startIndex == lastPageNum ? lastPageNum : startIndex+1;
	var indexNum		= startIndex <= navigatorNum  ? 0 : parseInt((startIndex-1)/navigatorNum) * navigatorNum;
	$elementDiv.append("<img src='/images/sub/btn_next.gif />");
	
	if (totalCount > 1) {
		
		if (startIndex > 1) {
			$elementDiv.append("<a class='btn_first disabled' href='javascript:goSearch("+1+")' ><img src='/images/sub/btn_start.gif' /></a>");
			$elementDiv.append("<a class='btn_first disabled' href='javascript:goSearch("+previewPageNum+")' ><img src='/images/sub/btn_pre.gif' /></a>");
		}else{
			$elementDiv.append("<a class='btn_first disabled' href='javascript:;' ><img src='/images/sub/btn_start.gif' /></a>");
			$elementDiv.append("<a class='btn_first disabled' href='javascript:;' ><img src='/images/sub/btn_pre.gif' /></a>");
		}
		
		for (var i=1; i<=navigatorNum; i++) {
			var pageNum = i + indexNum;
			
			if (pageNum == startIndex) 
				$elementDiv.append("<a class='btn_selected'> "+pageNum+" </a>");
			else 
				$elementDiv.append("<a class='btn_first disabled' href='javascript:goSearch("+pageNum+")' ><em> "+pageNum+" </em></a>");
			
			if (pageNum==lastPageNum)
				break;
		}
		
		if (startIndex < lastPageNum) {
			$elementDiv.append("<a class='btn_first disabled' href='javascript:goSearch("+nextPageNum+")' ><img src='/images/sub/btn_next.gif' /></a>");
			$elementDiv.append("<a class='btn_first disabled' href='javascript:goSearch("+lastPageNum+")' ><img src='/images/sub/btn_end.gif' /></a>");
		}
	}
}
