/**
 * fileId file控件
 * divId uploadify 动画使用
 * fileListId 返回展示div
 * name 提交控件的名字
 * multi 多文件上传标志
 * url 文件上传action
 */
Upload = function(fileId, divId, fileListId, name, multi, url) {
	this.divId = divId;
	this.fileId = fileId;
	this.fileListId = fileListId;
	this.multi = multi;
	//成功返回
	this.complete = function(event, queueID, fileObj, response, data) {
		var result = eval("(" + response + ")");
		if (result.flag == "success") {
			if (!multi) {
				$("#" + fileListId).html("");
			}
			var span = "<span class='upload'></span>";
			span = span + "<input  type='hidden' value='" + result.message[0]
					+ "' name='" + name + "' id='" + name + "'/>";
			if(result.message[0].indexOf(".xls") > 0 ){
				span = span+"上传成功，点击 选择文件重新上传";
			}
			else{
				//span = span
				//+ "<a href='#' onclick='$(this).parent().remove();'>删除</a>";
				span = span + "<a href='" + result.message[0]
				+ "' style='color:red' target='_blank'>预览</a>";
			}
			$("#" + fileListId).append(span);
		} else {
			alert(result.message[0]);
		}
	}
	//调用请求
	this.upload = function() {
		this.uploadify.uploadifyUpload();
	}
	//注册uploadify
	this.uploadify = $("#" + this.fileId);
	this.uploadify
			.uploadify( {
				'uploader' : applicationContent.baseUrl + '/script/uploadify/uploadify.swf',
				'script' : url,
				'cancelImg' : applicationContent.baseUrl + '/script/uploadify/cancel.png',
				'folder' : 'folder',
				'fileDataName' : 'file',
				'buttonText' : '选 择 文 件',
				'queueID' : divId,
				'onComplete' : this.complete,
				'auto' : true,
				'multi' : multi
			});
}
