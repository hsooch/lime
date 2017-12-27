  
    $(function(){ 
      $('#header').mouseenter(function () { 
        if(!$($(this).find('#subArea, .two')).is(':animated')) $(this).find('#subArea, .two').slideDown('slow'); 
      }); 
      
      $('#header').mouseleave(function () { 
        $(this).find('#subArea, .two').slideUp('slow'); 
      }); 
    
	
	
	  $("#mainGnb").children("li").each(function(q){
  		$(this).hover(function(){
  			//alert(q); 
  		}, function(){
  			//alert(2);
  		}).focusin(function(){
  			if(!$($('#header').find('#subArea, .two')).is(':animated')) $('#header').find('#subArea, .two').slideDown('slow'); 
  		}).focusout(function(){
  			//$('#header').find('#SubWrap').slideUp('slow'); 
  			//alert(4);
  		});
  	});
																
      
    }); 
    //]]> 
	
	
	
	
	
