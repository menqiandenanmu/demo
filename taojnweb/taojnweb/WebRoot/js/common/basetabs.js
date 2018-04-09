/**
 * 定义全局对象，类似于命名空间或包的作用
 * @author liwei
 */
var util = $.extend({
	/**
	 * 在系统的右下角显示消息提示
	 * @param {String} msg 消息内容
	 * @param {String} title 消息标识，默认为：系统消息
	 */
	show : function(msg, title) {
		top.$.messager.show({
			msg : msg,
			height : 120,
			timeout : 2500,
			title : title ? title : '系统消息'
		});
	},
	/**
	 * 显示警告窗口。
	 * @param {String} title 显示在窗口头部的标题文本。
	 * @param {String} msg 显示在窗口中的文本。
	 * @param {String} icon 显示的图片，可选值：error,question,info,warning。
	 * @param {Function} fn 当窗口关闭时触发的回调函数。
	 */
	alert : function(title, msg, icon, fn) {
		top.$.messager.alert(title, msg, icon, fn);
	},
	/**
	 * 显示一个带有确认和取消按钮的确认信息窗口。
	 * @param {String} title 显示在窗口头部的文本。
	 * @param {String} msg 显示在窗口中的文本。
	 * @param {Function} sure 回调函数，当用户点击确认按钮时，回调该函数。
	 * @param {Function} cancel 回调函数，当用户点击取消按钮时，回调该函数。
	 */
	confirm : function(title, msg, sure, cancel) {
		top.$.messager.confirm(title, msg, function(flag) {
			if (flag) {
				if (sure) {
					sure();
				}
			} else {
				if (cancel) {
					cancel();
				}
			}
		});
	},
	/**
	 * 显示一个带进度条信息的窗口。
	 * @param {} options
	 */
	loading : function(flag) {
		if (flag || flag == undefined) {
			top.$.messager.progress()
		} else {
			top.$.messager.progress("close");
		}
	},
	/**
	 * 显示一个带有确认和取消的输入信息窗口。
	 * @param {String} title 显示在窗口头部的标题文本。
	 * @param {String} msg 显示在窗口中的信息
	 * @param {Function} fn 接受用户输入作为参数的回调函数。
	 */
	prompt : function(title, msg, fn) {
		top.$.messager.prompt(title, msg, fn);
	},
	/**
	 * 在框架中弹出窗口，使用Iframe，参数为JSON对象。<br>
	 * 参数说明：<br>
	 * id : 必须，窗口的唯一标识<br>
	 * url : 必须，窗口的内容<br>
	 * title : 必须，窗口标题<br>
	 * iconCls : 可选，窗口图标<br>
	 * width : 可选，窗口的宽度，默认680<br>
	 * height : 可选，窗口的高度，默认480<br>
	 * maximizable : 可选，是否可以最大化，默认可以
	 * @param {Object} options
	 */
	open : function(options) {
		top.iwindow.open(options);
	},
	method : function(id, method) {
		top.iwindow.method(id, method);
	},
	/**
	 * 关闭弹出窗口。
	 */
	close : function() {
		top.iwindow.close();
		parent.$('#win').window('close');
	},
	/**
	 * 获取其它窗口对象
	 * @param {String} id 窗口标识
	 * @return {Window} Window对象
	 */
	call : function(id) {
		return top.iwindow.call(id);
	}
}, util);
var context = top.context;

jQuery(document).ready(function() {
//设置combobox不可编辑
	 $('.easyui-combobox').combobox({  
       editable:false 
   });  
	 });
