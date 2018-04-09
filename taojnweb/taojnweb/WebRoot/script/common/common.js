/**
 * 公用方法
 */
var common = new function(){
	/**
	 * 提交url，完成后刷新页面
	 * message为提交的确认信息。
	 */
	this.doPost = function(url,message){
		if(confirm(message)){
			var url=url;
			var options={};
			options.url=url;
			options.dataType="text";
			options.success=function(text){
					var obj=eval(text);
					alert(obj.message[0]); 
					if(obj.flag=="success"){
						location.reload();
						}
					};
			$.ajax(options);
		}
	}
	this.doPostData = function(url,message,optionsValue){
		if(confirm(message)){
			var url=url;
			var options={};
			options.data=optionsValue;
			options.url=url;
			options.dataType="text";
			options.success=function(text){
					var obj=eval(text);
					alert(obj.message[0]); 
					if(obj.flag=="success"){
						location.reload();
						}
					};
			$.ajax(options);
		}
	}
	 $(".main-menu").find(".nav-header").click(function(){
	    	$(this).nextUntil(".nav-header").slideToggle("slow");
	    });
}