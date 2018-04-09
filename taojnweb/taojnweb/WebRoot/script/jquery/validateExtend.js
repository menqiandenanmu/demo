	jQuery.extend(jQuery.validator, {
		messages:{
	        required: "<span class='input_tips_2'>必填项</span>",
		  	remote: "<span class='input_tips_2'>请修正该字段</span>",
	  		email: "<span class='input_tips_2'>电子邮件格式不正确</span>",
		  	url: "<span class='input_tips_2'>请输入合法的网址</span>",
		  	date: "<span class='input_tips_2'>请输入合法的日期</span>",
		  	dateISO: "<span class='input_tips_2'>请输入合法的日期 (ISO).</span>",
		  	number: "<span class='input_tips_2'>请输入合法的数字</span>",
		  	digits: "<span class='input_tips_2'>只能输入整数</span>",
		  	creditcard: "<span class='input_tips_2'>请输入合法的信用卡号</span>",
		  	equalTo: "<span class='input_tips_2'>请再次输入相同的值</span>",
		  	accept: "<span class='input_tips_2'>请输入拥有合法后缀名的字符串</span>",
		  	maxlength: jQuery.validator.format("<span class='input_tips_2'>请输入一个长度最多是 {0} 的字符串</span>"),
		  	minlength: jQuery.validator.format("<span class='input_tips_2'>请输入一个长度最少是 {0} 的字符串</span>"),
		  	rangelength: jQuery.validator.format("<span class='input_tips_2'>请输入一个长度介于 {0} 和 {1} 之间的字符串</span>"),
		  	range: jQuery.validator.format("<span class='input_tips_2'>请输入一个介于 {0} 和 {1} 之间的值</span>"),
		  	max: jQuery.validator.format("<span class='input_tips_2'>请输入一个最大为 {0} 的值</span>"),
		  	min: jQuery.validator.format("<span class='input_tips_2'>请输入一个最小为 {0} 的值</span>"),
		  	password: jQuery.validator.format("<span class='input_tips_2'>请输入一致的密码</span>"),
		  	ip:"<span class='input_tips_2'>请输入有效的IP地址</span>",
		  	chrnum:"<span class='input_tips_2'>只能输入数字、字母或者它们的组合</span>",
		  	phone:"<span class='input_tips_2'>电话号码格式不对</span>",
		  	mobile:"<span class='input_tips_2'>手机号码格式不对</span>",
		  	zipcode:"<span class='input_tips_2'>邮政编码格式不对</span>",
		  	idcardno:"<span class='input_tips_2'>身份证号码格式不正确</span>",
		  	chcharacter:"<span class='input_tips_2'>请输入汉字</span>",
		  	onkeypress:"<span class='input_tips_2'>只可输入中文、字母和数字</span>",
		  	regstr:"<span class='input_tips_2'>请输入有效的数据</span>",
		  	fck:"<span class='input_tips_2'>数据不能为空!</span>",
		  	maxTo:"<span class='input_tips_2'>输入数据有误!</span>",
		  	minTo:"<span class='input_tips_2'>输入数据有误!</span>"
		}
	});
	
	jQuery.validator.addMethod("ip", function(value, element) {        
	    return this.optional(element) || (/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));        
	});
	
	// 增加只能是字母和数字的验证
	  jQuery.validator.addMethod("chrnum", function(value, element) {        
	    return this.optional(element) || (/^([a-zA-Z0-9]+)$/.test(value));        
	  });        
	    
	// 自定义验证规则——对电话号码进行验证
	$.validator.addMethod("phone",function(value, element){           
	   // "/\(?0\d{2,3}[)
		// -]?\d{7,8}/"匹配电话号码的格式多种：010-82839278、(010)82839278、01082839278等，但是，这样有一个问题
	   // 如：(01082839278这样的也会匹配。当然可以用分支条件"|"解决，比较麻烦。而且以什么开始或结束也没有匹配。
	   // 为了简单起见，去掉有"()"的形式。匹配区号3位，则本地号8位，区号4位，则本地号7位的号码。
	   var tel = /^0\d{2}[-]?\d{8}$|^0\d{3}[-]?\d{7}$/;        
	   return this.optional(element) || (tel.test(value));        
	   } );       
	  
	// 手机号码验证
	jQuery.validator.addMethod("mobile", function(value, element) {      
	  var length = value.length;      
	  // 长度为11，以13，15，18开头的
	  return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));      
	});      
	    
	// 邮政编码验证
	jQuery.validator.addMethod("zipcode", function(value, element) {      
	  var tel = /^[0-9]{6}$/;      
	  return this.optional(element) || (tel.test(value));      
	});   
	  
	/* 追加自定义验证方法 */
	// 身份证号码验证
	jQuery.validator.addMethod("idcardno", function(value, element) {
	return this.optional(element) || isIdCardNo(value);
	});

	// 汉字
	jQuery.validator.addMethod("chcharacter", function(value, element) {
	var tel = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || (tel.test(value));
	});
	
//	//只可输入中文、字母和数字
//	jQuery.validator.addMethod("onkeypress", function(value, element) {
//		var onkeypress="return   /[\w\u4e00-\u9fa5]/.test(String.fromCharCode(window.event.keyCode))"  
//			var  onpaste="return   !/[^\w\u4e00-\u9fa5]/g.test(window.clipboardData.getData('Text'))"  
//				var ondragenter="return   false"
//		return this.optional(element) || (onkeypress.test(value)|| (onpaste.test(value)|| (ondragenter.test(value));
//		});
//	
	//正则表达式验证
	jQuery.validator.addMethod("regstr",function(value,element,param){
	    return this.optional(element)||(param.test(value));
	});
	/**
	 * fck验证
	 */
	jQuery.validator.addMethod("fck",function(value,element,param){
		var flag=true;
		var oEditor = FCKeditorAPI.GetInstance($(element).attr("name"));   //获取名为content的FCK编辑器实例
		var content = oEditor.GetXHTML(); //获取编辑器内容
		$(element).val(content);
		if(param==true){
		    if(content == ""||content == "<br />"||content == "&nbsp;"){
				oEditor.SetHTML("");
	            oEditor.Focus();
	            flag=false;
	        }
		}
	    return flag;
	});
	/**
	 *比较验证最大
	 */
	jQuery.validator.addMethod("maxTo",function(value,element,param){
		
		var flag=false;
		var temp=parseInt($(param).val());
		if(temp==""){
			flag=true;
		}
		else{
		if(parseInt(value) >= temp)
			 flag=true;
		}
	    return flag;
	});
	/**
	 *比较验证最小
	 */
	jQuery.validator.addMethod("minTo",function(value,element,param){
		var flag=false;
		var temp=parseInt($(param).val());
		if(temp==""){
			flag=true;
		}
		else{
		if(parseInt(value)<= temp)
			 flag=true;
		}
	    return flag;
	});


	
	/**
	 * 身份证号码验证
	 * 
	 */
	function isIdCardNo(num) {

	var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
	var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2");
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
	return false;
	}
	// check and set value
	for(i=0;i<intStrLen;i++) {
	varArray[i] = idNumber.charAt(i);
	if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
	return false;
	} else if (i < 17) {
	varArray[i] = varArray[i] * factorArr[i];
	}
	}

	if (intStrLen == 18) {
	// check date
	var date8 = idNumber.substring(6,14);
	if (isDate8(date8) == false) {
	return false;
	}
	// calculate the sum of the products
	for(i=0;i<17;i++) {
	lngProduct = lngProduct + varArray[i];
	}
	// calculate the check digit
	intCheckDigit = parityBit[lngProduct % 11];
	// check last digit
	if (varArray[17] != intCheckDigit) {
	return false;
	}
	}
	else{        // length is 15
	// check date
	var date6 = idNumber.substring(6,12);
	if (isDate6(date6) == false) {

	return false;
	}
	}
	return true;

	}
	/**
	 * 判断是否为"YYYYMM"式的时期
	 * 
	 */
	function isDate6(sDate) {
	if(!/^[0-9]{6}$/.test(sDate)) {
	return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	if (year < 1700 || year > 2500) return false
	if (month < 1 || month > 12) return false
	return true
	}
	/**
	 * 判断是否为"YYYYMMDD"式的时期
	 * 
	 */
	function isDate8(sDate) {
	if(!/^[0-9]{8}$/.test(sDate)) {
	return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	day = sDate.substring(6, 8);
	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]
	if (year < 1700 || year > 2500) return false
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12) return false
	if (day < 1 || day > iaMonthDays[month - 1]) return false
	return true
	}