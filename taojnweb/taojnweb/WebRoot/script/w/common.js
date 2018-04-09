var winWidth=$(window).width();
$("html").css("fontSize",(winWidth/640)*40+"px");

$(function(){

	if($('#home_slider').size()>0){
		$('#home_slider').flexslider({
			animation : 'slide',
			controlNav : true,
			directionNav : true,
			animationLoop : true,
			slideshow : false,
			useCSS : false,
			slideshow:true,
			slideshowSpeed: 2000
		});
	}


		if($("#tab").length>0){
			tab("tab");
		}
		
		if($(".number").length>0){
			$(".number").numSpinner({
				min:1,
				onChange:function(obj,value){
					if($(".shoppingCart-list").length>0){
						shoppinginfo();
					}
					
				}
			});
		} 
		 $("input[type='checkbox']").each(function(){
		 	checked(this);
		 });
		 // $("input[type='checkbox']").click(function(){
			// 		var checked1=$(this).attr("checked");
			// 		if(checked1=="true"||checked1){
			// 			$(this).parent().removeClass('checked');
			// 			$(this).next().removeClass('icon-done');
			// 		}
			// 		else{
			// 			$(this).parent().addClass('checked');
			// 			$(this).next().addClass('icon-done');							
			// 		}
			// 	});
	});

function tab(div){
	$("#"+div).find("li").find("a").click(function(e){
		$(this).parent().addClass('active');
		$(this).parent().siblings().removeClass('active');
		var id=$(this).attr("href");
		$(".tab-panel").find(".tab-item").hide();
		$(id).show();
		return false;
	});
}

function checked(obj){
var checked=$(obj).is(':checked');
		 	if(checked=="true"||checked||checked=="checked"){
				$(obj).parent().addClass('checked');
				$(obj).next().addClass('icon-done');
			}
			else{
				$(obj).parent().removeClass('checked');
				$(obj).next().removeClass('icon-done');							
			}
}