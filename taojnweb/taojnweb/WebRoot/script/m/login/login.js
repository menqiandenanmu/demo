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
function changeCode() {
	$("#checkCodeImg").attr("src",
			"${base}/manage/checkCode.htm?rand=" + new Date());
}
function init() {
	if (parent != this) {
		parent.location = document.location;
	}
}
init();
$(document).ready(function(){
	var arVersion = navigator.appVersion.split("MSIE")
	var version = parseFloat(arVersion[1])
	if ((version >= 5.5) && (document.body.filters)) {
		for ( var j = 0; j < document.images.length; j++) {
			var img = document.images[j]
			var imgName = img.src.toUpperCase()
			if (imgName.substring(imgName.length - 3, imgName.length) == "PNG") {
				var imgID = (img.id) ? "id='" + img.id + "' " : ""
				var imgClass = (img.className) ? "class='" + img.className
						+ "' " : ""
				var imgTitle = (img.title) ? "title='" + img.title + "' "
						: "title='" + img.alt + "' "
				var imgStyle = "display:inline-block;" + img.style.cssText
				if (img.align == "left")
					imgStyle = "float:left;" + imgStyle
				if (img.align == "right")
					imgStyle = "float:right;" + imgStyle
				if (img.parentElement.href)
					imgStyle = "cursor:hand;" + imgStyle
				var strNewHTML = "<span "
						+ imgID
						+ imgClass
						+ imgTitle
						+ " style=\""
						+ "width:"
						+ img.width
						+ "px; height:"
						+ img.height
						+ "px;"
						+ imgStyle
						+ ";"
						+ "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
						+ "(src=\'" + img.src
						+ "\', sizingMethod='scale');\"></span>"
				img.outerHTML = strNewHTML
				j = j - 1
			}
		}
	}
});