
$(document).ready(function () {

	/*검색결과-탭*/
	$(".tabMenu li").click(function () {
		$(this).addClass("on");
		$(this).siblings().removeClass("on");
	});
	
	$(".tabMenu li.tab1").click(function () {
		$("#result1").show();
		$("#result1").siblings().hide();
	});
	
	$(".tabMenu li.tab2").click(function () {
		$("#result2").show();
		$("#result2").siblings().hide();
	});
	
	$(".tabMenu li.tab3").click(function () {
		$("#result3").show();
		$("#result3").siblings().hide();
	});
	
	
	<!--열기/접기-->
	$(".btn_on").click(function () {
		$("#contArea").slideUp("slow");
		$(this).fadeOut("fast");
		$(".btn_off").fadeIn("fast");
	});
	$(".btn_off").click(function () {
		$("#contArea").slideDown("slow");
		$(this).fadeOut("fast");
		$(".btn_on").fadeIn("fast");
	});
	
	
	<!--검색 후 테이블보이기-->
	$('.search').click(function() {
		$('.box2').slideDown('slow', function() {
			// 객체가 다 펼치지거나 접히고 나면 여기에 든 내용이 실행된다.
		});
	});
	
	
	<!--메뉴 떨어지기-->
	$(".box2> div> p").click(function () {
		$(this).toggleClass("off");
		$(this).siblings("ul").slideToggle("fast");
		
	});
	
	
	<!--모두선택-->
	$( '.checkAll' ).click( function() {
		$( '.item' ).prop( 'checked', this.checked );
	});


	<!--서브메뉴-->
	// menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
	$(".local>a")./*mouseenter*/click(function(){
		var submenu = $(this).next(".subTree");
		if( submenu.is(":visible") ){
			submenu.slideUp();
		}else{
			submenu.slideDown();
		}
	});
	
	
	
	
	 $("tr.info").mouseenter(function(){        //function_tr 
		$(this).css("background-color","#fdffd0"); 
	 }); 
	 $("tr.info").mouseleave(function(){        //function_tr 
		$(this).css("background-color","#ffffff"); 
	 }); 
	   /*
	  $("tr.info td").click(function(){     //function_td 
		$(this).css("font-weight","bold"); 
	  }); */




});




	



	


  
