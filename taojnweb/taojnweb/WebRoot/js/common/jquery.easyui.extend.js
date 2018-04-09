function defaults(obj, settings) {
	if (obj) {
		$.extend(obj.defaults, settings);
	}
}
defaults($.fn.pagination, {
	beforePageText : '第',
	afterPageText : '共 {pages} 页',
	displayMsg : '显示 {from} 到 {to} ,共 {total} 记录'
});
defaults($.fn.datagrid, {
	fit : true,
	plain : true,
	loadMsg : false,
	rownumbers : true,
	pagination : true,
	pageSize : 20,
	pageList : [20, 40, 60, 80]
});
defaults($.fn.treegrid, {
	fit : true,
	plain : true,
	loadMsg : false,
	rownumbers : true
});

var Format = {
	/**
	 * 将
	 * @param {} v
	 * @return {}
	 */
	_two : function(v) {
		return (v < 10 ? "0" : "") + v;
	},
	/**
	 * 数值格式化，将数值按格式定义进行格式化后返回。
	 * @param {Number} value 数值
	 * @param {Object} opts 格式定义
	 * @return {String} 格式化后字符串
	 */
	_number : function(value, opts) {
		if (!value && value != 0) {
			return value;
		}
		if (isNaN(parseFloat(value))) {
			return value;
		}
		opts = $.extend({
			precision : 0,
			decimalSeparator : ".",
			groupSeparator : "",
			prefix : "",
			suffix : ""
		}, opts);
		if (opts.precision > 0) {
			value = parseFloat(value).toFixed(opts.precision);
		}
		value = value + "";
		var s1 = value, s2 = "";
		var idx = value.indexOf(".");
		if (idx >= 0) {
			s1 = value.substring(0, idx);
			s2 = value.substring(idx + 1, value.length);
		}
		if (opts.groupSeparator) {
			var p = /(\d+)(\d{3})/;
			while (p.test(s1)) {
				s1 = s1.replace(p, "$1" + opts.groupSeparator + "$2");
			}
		}
		if (s2) {
			return opts.prefix + s1 + opts.decimalSeparator + s2 + opts.suffix;
		} else {
			return opts.prefix + s1 + opts.suffix;
		}
	},
	/**
	 * 日期格式化，将长整型日期转为(yyyy-MM-dd)
	 */
	date : function(value, row, index) {
		var date = new Date(value);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + Format._two(m) + '-' + Format._two(d);
	},
	/**
	 * 时间格式化，将长整型日期转为(HH:mm:ss)
	 */
	time : function(value, row, index) {
		var date = new Date(value);
		var h = date.getHours();
		var m = date.getMinutes();
		var s = date.getSeconds();
		return Format._two(h) + ':' + Format._two(m) + ':' + Format._two(s);
	},
	/**
	 * 日期格式化，将长整型日期转为(yyyy-MM-dd HH:mm:ss)
	 */
	datetime : function(value, row, index) {
		return Format.date(value, row, index) + ' ' + Format.time(value, row, index);
	},
	/**
	 * Money格式化，将数据转换为(￥x,xxx.xx)
	 */
	money : function(value, row, index) {
		return Format._number(value, {
			prefix : '￥',
			precision : 2,
			groupSeparator : ','
		});
	}
};

var Validator = {
	zip : /^[1-9]\d{5}$/,
	url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	number : /^\d+$/,
	ip  : /^[\d\.]{7,15}$/,
	phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
	mobile : /^((\(\d{3}\))|(\d{3}\-))?13[0-9]\d{8}?$|15[89]\d{8}?$|18[0-9]\d{8}$/,
	validRegexp : function(regexp, value) {
		return regexp.test(value);
	},
	isIdCard : function(number) {
		number = number.toUpperCase();
		var date, Ai;
		var verify = "10X98765432";
		var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var area = ['', '', '', '', '', '', '', '', '', '', '', '北京', '天津', '河北', '山西', '内蒙古', '', '', '', '', '',
				'辽宁', '吉林', '黑龙江', '', '', '', '', '', '', '', '上海', '江苏', '浙江', '安微', '福建', '江西', '山东', '', '', '',
				'河南', '湖北', '湖南', '广东', '广西', '海南', '', '', '', '重庆', '四川', '贵州', '云南', '西藏', '', '', '', '', '', '',
				'陕西', '甘肃', '青海', '宁夏', '新疆', '', '', '', '', '', '台湾', '', '', '', '', '', '', '', '', '', '香港', '澳门',
				'', '', '', '', '', '', '', '', '国外'];
		var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
		if (re == null)
			return false;
		if (re[1] >= area.length || area[re[1]] == "")
			return false;
		if (re[2].length == 12) {
			Ai = number.substr(0, 17);
			date = [re[9], re[10], re[11]].join("-");
		} else {
			Ai = number.substr(0, 6) + "19" + number.substr(6);
			date = ["19" + re[4], re[5], re[6]].join("-");
		}
		if (!this.isDate(date, "ymd"))
			return false;
		var sum = 0;
		for (var i = 0; i <= 16; i++) {
			sum += Ai.charAt(i) * Wi[i];
		}
		Ai += verify.charAt(sum % 11);
		return (number.length == 15 || number.length == 18 && number == Ai);
	},
	isDate : function(op, formatString) {
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch (formatString) {
			case "ymd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
				if (m == null)
					return false;
				day = m[6];
				month = m[5] * 1;
				year = (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
				break;
			case "dmy" :
				m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
				if (m == null)
					return false;
				day = m[1];
				month = m[3] * 1;
				year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
				break;
			default :
				break;
		}
		if (!parseInt(month))
			return false;
		month = month == 0 ? 12 : month;
		var date = new Date(year, month - 1, day);
		return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth() + 1) && day == date
				.getDate());
		function GetFullYear(y) {
			return ((y < 30 ? "20" : "19") + y) | 0;
		}
	}
};

if ($.fn.validatebox) {
	$.extend($.fn.validatebox.defaults.rules, {
		minnumber : {
			validator : function(value, args) {
				return $.trim(value) >= args[0];
			},
			message : "输入的数字必须大于或等于{0}"
		},
		maxnumber : {
			validator : function(value, args) {
				return $.trim(value) <= args[0];
			},
			message : "输入的数字必须小于或等于{0}"
		},
		minlength : {
			validator : function(value, args) {
				return $.trim(value).length >= args[0];
			},
			message : "输入内容长度必须大于或等于{0}"
		},
		maxlength : {
			validator : function(value, args) {
				return $.trim(value).length <= args[0];
			},
			message : "输入内容长度必须小于或等于{0}"
		},
		zip : {
			validator : function(value) {
				return Validator.validRegexp(Validator.zip, value);
			},
			message : '邮政编码不正确'
		},
		phone : {
			validator : function(value) {
				return Validator.validRegexp(Validator.phone, value);
			},
			message : '电话号码不正确'
		},
		mobile : {
			validator : function(value) {
				return Validator.validRegexp(Validator.mobile, value);
			},
			message : '手机号码不正确'
		},
		number : {
			validator : function(value) {
					return Validator.validRegexp(Validator.number, value);
			},
			message : '请输入合法的数字'
		},
		url : {
			validator : function(value) {
					return Validator.validRegexp(Validator.number, value);
			},
			message : '请输入合法的网站'
		},
		adress : {
			validator : function(value) {
					return Validator.validRegexp(Validator.number, value);
			},
			message : '请输入合法的IP地址'
		},
		idcard : {
			validator : function(value) {
				return Validator.isIdCard(value);
			},
			message : '身份证号不正确'
		}
	});
};

var easyuiPanelOnMove = function(left, top) {
	if (left < 0) {
		$(this).window('move', {
			left : 1
		});
	}
	if (top < 0) {
		$(this).window('move', {
			top : 1
		});
	}
	var width = $(this).panel('options').width;
	var height = $(this).panel('options').height;
	var right = left + width;
	var buttom = top + height;
	var browserWidth = $(document).width();
	var browserHeight = $(document).height();
	if (right > browserWidth) {
		$(this).window('move', {
			left : browserWidth - width
		});
	}
	if (buttom > browserHeight) {
		$(this).window('move', {
			top : browserHeight - height
		});
	}
};
// $.fn.panel.defaults.onMove = easyuiPanelOnMove;
// $.fn.window.defaults.onMove = easyuiPanelOnMove;
// $.fn.dialog.defaults.onMove = easyuiPanelOnMove;
