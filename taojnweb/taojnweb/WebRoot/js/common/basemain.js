jQuery(document).ready(function() {
	window.$tabs = $("#workarea");
	var $tabsMenu = $("#tabsMenu");
	window.$tabs.tabs({
		fit : true,
		plain : true,
		onContextMenu : function(e, title, index) {
			e.preventDefault();
			$tabsMenu.menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data('tabTitle', title);
		},
		onLoad : function(panel) {
			alert(panel.html())
		}
	})

	$tabsMenu.menu({
		onClick : function(item) {
			var tabTitle = $(this).data('tabTitle');
			if (item.id === 'closeCurr') {
				var tab = $tabs.tabs('getTab', tabTitle);
				if (tab.panel('options').closable) {
					$tabs.tabs('close', tabTitle);
				}
			} else if (item.id === 'closeAll') {
				var allTabs = $tabs.tabs('tabs');
				var delTabs = [];
				$.each(allTabs, function() {
					var opts = $(this).panel('options');
					if (opts.closable) {
						delTabs.push(opts.title);
					}
				});
				$.each(delTabs, function(index, title) {
					$tabs.tabs('close', title);
				});
			} else if (item.id === 'closeOther') {
				var allTabs = $tabs.tabs('tabs');
				var delTabs = [];
				$.each(allTabs, function() {
					var opts = $(this).panel('options');
					if (opts.closable && opts.title != tabTitle) {
						delTabs.push(opts.title);
					}
				});
				$.each(delTabs, function(index, title) {
					$tabs.tabs('close', title);
				});
			} else if (item.id === 'refresh') {
				var tab = $tabs.tabs('getTab', tabTitle);
				$tabs.tabs('update', {
					tab : tab,
					options : tab.panel('options')
				});
			}
		}
	});

	// 打开指定功能的工作窗口
	window.showFuncWin = function(title, id, url) {
		if ($tabs.tabs("exists", title)) {
			$tabs.tabs("select", title);
		} else {
			var html = '<iframe id="{0}" frameborder="0" width="100%" height="100%" src="{1}" scrolling="no"></iframe>';
			html = html.replace("{0}", "frame_" + id).replace("{1}", url);
			$tabs.tabs('add', {
				title : title,
				content : html,
				closable : true,
				border : false
			});
			$('#frame_' + id).parent().css('overflow', 'hidden');
		}
	}
	
	window.showFuncWin2 = function(title, id, url, scrollable) {
		if ($tabs.tabs("exists", title)) {
			$tabs.tabs("select", title);
			var html = '<iframe id="{0}" frameborder="0" width="100%" height="100%" src="{1}" scrolling="' + (scrollable ? 'yes' : 'no') + '"></iframe>';
			html = html.replace("{0}", "frame_" + id).replace("{1}", url);
			$tabs.tabs("update",
					{tab : $tabs.tabs('getSelected'),
					options : {content: html}
			
			});
		} else {
			var html = '<iframe id="{0}" frameborder="0" width="100%" height="100%" src="{1}" scrolling="' + (scrollable ? 'yes' : 'no') + '"></iframe>';
			html = html.replace("{0}", "frame_" + id).replace("{1}", url);
			$tabs.tabs('add', {
				title : title,
				content : html,
				closable : true,
				border : false
			});
			$('#frame_' + id).parent().css('overflow', 'hidden');
		}
	}
});
var iwindow = {
	winStack : [],
	template : '<div id="{id}" style="width: {width}px; height: {height}px; overflow: hidden;">'
			+ '<iframe frameborder="0" width="100%" height="100%" src="{url}" scrolling="yes"></iframe></div>',
	open : function(options) {
		var win = $("#" + options.id);
		if (win.length > 0) {
			return;
		}
		options = $.extend({
			width : 740,
			height : 520,
			title : '窗口',
			iconCls : 'icon-window',
			maximizable : true
		}, options);
		var html = iwindow.template;
		html = html.replace("{id}", options.id);
		html = html.replace("{width}", options.width);
		html = html.replace("{height}", options.height);
		html = html.replace("{url}", options.url);
		win = $(html).appendTo($(document.body));
		win.window({
			modal : true,
			resizable : false,
			collapsible : false,
			minimizable : false,
			maximizable : options.maximizable,
			iconCls : options.iconCls,
			title : options.title,
			onClose : function() {
				win.window("destroy");
				// 弹出栈中的最后上面的窗口标识
				iwindow.winStack.pop();
			}
		});
		// 打开窗口时，将窗口标识压入栈中
		iwindow.winStack.push(options.id);
	},
	method : function(id, func) {
		var win = $("#" + id);
		win.window(func);
	},
	close : function() {
		// 弹出栈中的最后上面的窗口标识
		var id = iwindow.winStack[iwindow.winStack.length - 1];
		var win = $("#" + id);
		win.window('close');
	},
	call : function(id) {
		if (id == null || id == undefined) {
			// ID为空时取当前处理激活状态下的窗口
			if (iwindow.winStack.length <= 1) {
				var activeTab = window.$tabs.tabs('getSelected');
				var frame = $('iframe', activeTab)[0];
			} else {
				var winId = iwindow.winStack[iwindow.winStack.length - 2];
				var frame = $('iframe', $("#" + winId))[0];
			}
		} else {
			// ID不为空时取指定ID对应的窗口
			var frame = $('iframe', $("#" + id))[0];
		}
		var cwin = frame.contentWindow;
		return cwin;
	}
}
