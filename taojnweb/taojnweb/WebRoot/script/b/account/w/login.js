function checkForm() {
	if (document.forms[0].loginName.value == ""
			|| document.forms[0].loginName.value == "请输入用户名") {
		alert("请输入用户名！");
		return false;
	}

	if (document.forms[0].loginPass.value == ""
			|| document.forms[0].loginPass.value == "请输入用户密码") {
		alert("请输入用户密码！");
		return false;
	}

	if (document.forms[0].checkCode.value == ""
			|| document.forms[0].checkCode.value == "请输入验证码") {
		alert("请输入验证码！");
		return false;
	}
}  
function checkForm2() {
	if (document.forms[1].loginName.value == ""
			|| document.forms[0].loginName.value == "请输入用户名") {
		alert("请输入用户名！");
		return false;
	}

	if (document.forms[1].loginPass.value == ""
			|| document.forms[0].loginPass.value == "请输入用户密码") {
		alert("请输入用户密码！");
		return false;
	}

	if (document.forms[1].checkCode.value == ""
			|| document.forms[0].checkCode.value == "请输入验证码") {
		alert("请输入验证码！");
		return false;
	}
} 

function changeCode(base) {
	$("#checkCodeImg").attr("src", base+"/checkCode.htm?rand=" + new Date());
} 
function changeCode2(base) {
	$("#checkCodeImg2").attr("src", base+"/checkCode.htm?rand=" + new Date());
}