(function() {
	$.igrid = function(t, opts) {
		if (t.igrid) {
			return false;
		}
		opts = $.extend({
			height : 'auto', // 表格高度，暂未使用
			width : 750, // 表格宽度，暂未使用
			url : false, // 数据请求URL
			method : 'POST', // 数据请求类型
			dataType : 'json', // 数据返回格式
			params : {}, // 数据请求不变参数
			onParams : false, // 数据请求变化参数回调
			idProperty : 'id', // 数据标识字段
			page : 1, // 当前页数
			numRows : 0, // 总记录数
			limit : 20, // 每页记录数
			sort : '', // 默认排序字段
			order : 'asc', // 默认排序方式
			checkbox : false, // 是否显示多选列
			serial : false, // 是否显示序号列
			multiple : false, // 行选择是否为多选
			nodatamsg : '没有数据', // 无数据显示
			errormsg : '连接错误', // 连接错误
			autoload : true, // 是否自动加载
			useLayer : true, // 是否使用遮罩层
			// 以下为事件
			onSubmit : false, // 连接提交前加调
			onSuccess : false, // 连接成功回调
			onError : false, // 连接异常回调
			onPreProcess : false, // 数据处理前回调
			onStatProcess : false, // 统计信息处理
			onRowClick : false, // 行单击事件
			onRowDblClick : false, // 行双击事件
			structure : [], // 数据结构
			showToolbar : true, // 是否显示工具
			showPagination : true, // 是否显示分页工具条
			buttons : [],
			toolbar : ''
		}, opts);

		if (opts.serial) {
			opts.structure.unshift({
				name : 'NO.',
				width : 25,
				serial : true,
				align : 'right'
			});
		}
		if (opts.checkbox) {
			opts.structure.unshift({
				name : '',
				width : 15,
				checkbox : true
			});
		}

		var grid = {
			msg : null,
			layer : null,
			table : null,
			holder : null,
			loading : false,
			items : [],
			selectAll : function(options) {
				$('tbody tr .igrid-box', grid.table).each(function() {
					this.checked = true;
				});
			},
			unselectAll : function(options) {
				$('tbody tr .igrid-box', grid.table).each(function() {
					this.checked = false;
				});
			},
			reload : function(options) {
				opts.newp = 1;
				$.extend(opts.params, options)
				this.populate();
			},
			getSelectedId : function(options) {
				var boxes = $('.igrid-box', grid.table);
				for (var i = 0; i < boxes.length; i++) {
					if (boxes[i].checked) {
						return $(boxes[i]).val();
					}
				}
				return null;
			},
			getSelectedIds : function(options) {
				var values = [];
				$('.igrid-box', grid.table).each(function(i) {
					if (this.checked) {
						values.push($(this).val());
					}
				})
				return values;
			},
			getItem : function(options) {
				var items = this.getItems();
				for (var i = 0; i < items.length; i++) {
					if (items[i][opts.idProperty] === options) {
						return items[i];
					}
				}
				return null;
			},
			getItems : function(options) {
				return this.items || [];
			},
			buildPager : function() {
				var pages = $('.list-bottom', grid.holder).find(".pages").empty();
				var finfo = $('.finfo', grid.holder).empty();
				if (opts.pages > 0) {
					var index = opts.page;
					for (var i = 1; i <= opts.pages; i++) {
						if (Math.abs(i - index) < 3) {
							var txt = '<a index="' + i;
							if (i == index) {
								txt += '" class="cur';
							}
							txt += '">'+ i + '</a>';
							pages.append(txt)
							continue;
						}
						if (i == 1 || i == opts.pages) {
							pages.append('<a index="' + i + '">' + i + '</a>');
						}
					}
					var footbtn;
					if (index > 1) {
						footbtn = '<a href="javascript:;" index="' + (index - 1) + '" title="上一页"><i class="pages-icon icon-prev"></i></li>';
					} else {
						footbtn = '<a href="javascript:;" class="disabled" title="上一页"><i class="pages-icon icon-prev"></i></a>';
					}
					pages.prepend(footbtn);
					if (index < opts.pages) {
						footbtn = '<a href="javascript:;" index="' + (index + 1) + '" title="下一页"><i class="pages-icon icon-next"></i></li>';
					} else {
						footbtn = '<a href="javascript:;" class="disabled" title="下一页"><i class="pages-icon icon-next"></i></a>';
					}
					pages.append(footbtn);
					$('.pages a', grid.holder).click(function() {
						if ($(this).hasClass('disabled') || $(this).hasClass('active')) {
							return false;
						}
						opts.newp = parseInt($(this).attr('index'));
						grid.changePage();
						return false;
					});
					var infoTxt = '{start} - {end} 条，共 {total} 条';
					var start = (opts.newp - 1) * opts.limit + 1;
					var end = start + opts.pageRows - 1;
					infoTxt = infoTxt.replace('{start}', start);
					infoTxt = infoTxt.replace('{end}', end);
					infoTxt = infoTxt.replace('{total}', opts.numRows);

					finfo.text(infoTxt);
				}
				grid.toggleTbar();
			},
			changePage : function() {
				if (opts.newp == opts.page) {
					return false;
				}
				this.populate();
			},
			renderCell : function(td, struct, row) {
				if (struct.formatter) {
					var res = struct.formatter(row);
					if (typeof res == 'string') {
						td.innerHTML = res;
					} else {
						$(td).append(res);
					}
				} else {
					td.innerHTML = row[struct.field];
				}
			},
			showMsg : function(message) {
				$('.igrid-msg', grid.msg).text(message);
				$('tbody', grid.table).empty().append(grid.msg);
			},
			addData : function(data) {
				this.items = [];
				data = $.extend({
					items : [],
					page : 0,
					numRows : 0,
					stats : {},
					success : true,
					message : '查询异常'
				}, data);
				if (opts.onPreProcess) {
					data = opts.onPreProcess(data);
				}
				grid.loading = false;
				if (!data) {
					// 显示错误
					opts.pages = 0;
					grid.showMsg(opts.errormsg);
					grid.layer.remove();
					grid.buildPager();
					if (opts.onStatProcess) {
						opts.onStatProcess({});
					}
					if (opts.onSuccess) {
						opts.onSuccess(this);
					}
					return false;
				}
				if (data.success == false) {
					opts.pages = 0;
					grid.showMsg(data.message);
					grid.layer.remove();
					grid.buildPager();
					if (opts.onStatProcess) {
						opts.onStatProcess({});
					}
					if (opts.onSuccess) {
						opts.onSuccess(this);
					}
					return false;
				}
				opts.numRows = data.numRows;
				opts.pageRows = data.items.length;
				if (opts.numRows === 0) {
					$('tbody', grid.table).empty();
					opts.pages = 0;
					opts.page = 0;
					grid.buildPager();
					grid.showMsg(opts.nodatamsg);
					grid.layer.remove();
					if (opts.onStatProcess) {
						opts.onStatProcess(data.stats);
					}
					if (opts.onSuccess) {
						opts.onSuccess(this);
					}
					return false;
				}
				opts.pages = Math.ceil(opts.numRows / opts.limit);
				opts.page = opts.newp;
				grid.buildPager();
				var tbody = document.createElement('tbody');
				this.items = data.items;
				$.each(data.items, function(i, row) {
					var tr = document.createElement('tr');
					if (row[opts.idProperty]) {
						tr.id = 'row' + row[opts.idProperty];
					}
					$('thead tr.igrid-title th', grid.table).each(function() {
						var cell = $(this);
						var td = document.createElement('td');
						if (cell.attr('checkbox') == 'true') {
							//var checkbox=$("<input>",{
							//	"type":"checkbox"
							//}).addClass("igrid-box").val(row[opts.idProperty]);
							$(td).addClass('center').html("<input type='checkbox' class='igrid-box' value='"
									+ row[opts.idProperty] + "'>");
							//$(td).addClass('center').append(checkbox);
						} else if (cell.attr('serial') == 'true') {
							$(td).addClass('right').html(i + 1);
						} else {
							var idx = $(this).attr('axis').substr(3);
							var struct = opts.structure[idx];
							grid.renderCell(td, struct, row);
							if (struct.align) {
								$(td).addClass(struct.align);
							}
						}
						$(tr).append(td);
					});
					$(tbody).append(tr);
				});
				$('tr', t).unbind();
				$('tbody', grid.table).empty().append($(tbody).children());
				grid.addCellProp();
				grid.addRowProp();
				if (opts.onStatProcess) {
					opts.onStatProcess(data.stats);
				}
				if (opts.onSuccess) {
					opts.onSuccess(this);
				}
				if (opts.useLayer) {
					grid.layer.remove();
				}
				//$('tbody', grid.table).find("input").customInput();
				//console.log($('tbody', grid.table).find("input").length);
			},
			addCellProp : function() {
				$('tbody tr td', grid.table).each(function() {
					var idx = $('td', $(this).parent()).index(this);
					var pth = $('tr.igrid-title th:eq(' + idx + ')', grid.table).get(0);
					if ($(pth).attr('sort')) {
						$(pth).removeClass('asc desc');
					}
					if (opts.sort && opts.sort == $(pth).attr('sort')) {
						$(pth).addClass(opts.order);
					}
					var tdDiv = document.createElement('div');
					if ($(pth).attr('wrap')) {
						$(tdDiv).css('overflow', 'hidden').css('height', $(pth).attr('wrap'));
						var text;
						try {
							text = $(this.innerHTML).text();
						} catch (e) {
							text = this.innerHTML;
						}
						$(this).attr('title', text);
					}
					if (this.innerHTML == '') {
						this.innerHTML = '&nbsp;';
					}
					tdDiv.innerHTML = this.innerHTML;
					$(this).empty().append(tdDiv);
				});
			},
			addRowProp : function() {
				$('tbody tr .igrid-box', grid.table).click(function(e) {
					e.stopPropagation();
					var isSelectAll = true;
					var boxes = $('.igrid-box', grid.table);
					for (var i = 0; i < boxes.length; i++) {
						if (boxes[i].checked == false) {
							isSelectAll = false;
							break;
						}
					}
					if (isSelectAll == false) {
						var sall = $('.sall input');
						if (sall.length > 0) {
							sall[0].checked = false;
						}
					} else {
						var sall = $('.sall input');
						if (sall.length > 0) {
							sall[0].checked = true;
						}
					}
					return true;
				});
				$('tbody tr', grid.table).on('click', function(e) {
					if (opts.checkbox) {
						if (!opts.multiple) {
							$('tbody tr .igrid-box:checked', grid.table).each(function() {
								this.checked = false;
							});
						}
						$(this).find('.igrid-box', grid.table)[0].checked = true;
					}
					if (opts.onRowClick) {
						opts.onRowClick(this, grid);
					}
				}).on('dblclick', function() {
					if (opts.onRowDblClick) {
						opts.onRowDblClick(this, grid);
					}
				});
			},
			callParams : function() {
				var params = $.extend({}, opts.params);
				if (opts.onParams) {
					$.extend(params, opts.onParams());
				}
				if (!opts.newp) {
					opts.newp = 1;
				}
				if (opts.page > opts.pages) {
					opts.page = opts.pages;
				}

				var psort = '';
				if (opts.sort != '') {
					psort = opts.sort;
					if (opts.order == 'desc') {
						psort = '-' + psort;
					}
				}

				$.extend(params, {
					start : (opts.newp - 1) * opts.limit,
					count : opts.limit,
					sort : psort
				});
				return params;
			},
			populate : function() {
				if (grid.loading) {
					return true;
				}
				if (opts.onSubmit) {
					var gh = opts.onSubmit();
					if (!gh) {
						return false;
					}
				}
				grid.loading = true;
				if (!opts.url) {
					return false;
				}
				if (opts.useLayer) {
					grid.holder.prepend(grid.layer);
				}
				// get request params.
				var params = grid.callParams();
				$.ajax({
					type : opts.method,
					url : opts.url,
					data : params,
					dataType : opts.dataType,
					success : function(data) {
						grid.addData(data);
					},
					error : function(xhr, status, error) {
						grid.showMsg(opts.errormsg);
						opts.pages = opts.page = 0;
						grid.buildPager();
						try {
							if (opts.onError) {
								opts.onError(xhr, status, error);
							}
						} catch (e) {
						}
						grid.layer.remove();
					}
				});
				var sall = $('.sall input');
				if (sall.length > 0) {
					sall[0].checked = false;
				}
			},
			createBar : function(thead) {
				if (opts.showToolbar == false) {
					return;
				}
				var cols = $('.igrid-title th', thead).length;
				// igrid-sep
				var handle=$("<div>").addClass("page-item list-handle");
				//var tr = document.createElement('tr');
				//var td = document.createElement('td');
				//$(td).attr('colspan', cols);
				//$(tr).addClass('igrid-sep').append(td);
				grid.holder.before(handle);
				// bar
				//var tr = document.createElement('tr');
				//var td = document.createElement('td');
				//$(td).attr('colspan', cols);
				//$(td).html('<div class="list-handle"></div>');

				if (opts.buttons.length > 0) {
					if (opts.checkbox) {
						var allEl = $('<label class="sall"><input type="checkbox" name="checkAll" id="checkAll"/></label>');
						$('input:checkbox', allEl).click(function() {
							if ($(this).attr('checked') == 'checked') {
								grid.selectAll();
							} else {
								grid.unselectAll();
							}
						});
						$('.igrid-title th:first', thead).append(allEl);
					}
					$.each(opts.buttons, function(i, btn) {
						  if (btn.display != undefined && btn.display == 'none') {
						}else{
						var btnEl = $('<button type="button" class="btn btn-small bg-blue"></button>').html(btn.text);
						btnEl.click(btn.onclick);
					  handle.append(btnEl);
                                               }
					});
					var page=$("<div>").addClass("pages");
					grid.prev = $('<a href="javascript:;" title="上一页"><i class="pages-icon icon-prev"></i></a>');
					grid.next = $('<a href="javascript:;" title="下一页"><i class="pages-icon icon-next"></i></a>');
					grid.prev.click(function() {
						if (!$(this).hasClass('disabled')) {
							opts.newp--;
							grid.populate();
						}
					});
					grid.next.click(function() {
						if (!$(this).hasClass('disabled')) {
							opts.newp++;
							grid.populate();
						}
					});
					page.append(grid.prev).append(grid.next);
					handle.append(page);
				} else if (opts.toolbar && opts.toolbar != '') {
					if (typeof opts.toolbar === 'string') {
						opts.toolbar = $(opts.toolbar);
					}
					$('.list-handle').append(opts.toolbar);
				}
				//$(tr).addClass('igrid-bar').append(td);
				//$(thead).prepend(tr);
			},
			toggleTbar : function() {
				if (grid.prev) {
					if (opts.newp <= 1) {
						grid.prev.addClass('disabled');
					} else {
						grid.prev.removeClass('disabled');
					}
					if (opts.newp >= opts.pages) {
						grid.next.addClass('disabled');
					} else {
						grid.next.removeClass('disabled');
					}
				}
			},
			createTitle : function(thead) {
				var tr = document.createElement('tr');
				$.each(opts.structure, function(i, cm) {
					var th = document.createElement('th');
					$(th).attr('axis', 'col' + i);
					if (cm.name) {// 标题
						$(th).html(cm.name);
					}
					if (cm.checkbox) {
						$(th).attr('checkbox', 'true');
					}
					if (cm.serial) {
						$(th).attr('serial', 'true');
					}
					if (cm.field && cm.sortable) {// 排序字段
						$(th).append("<span></span>")
						$(th).attr('sort', cm.field);
						$(th).click(function() {
							var sort = $(this).attr('sort');
							if (sort) {
								if ($(this).hasClass('asc')) {
									opts.order = 'desc';
								} else {
									opts.order = 'asc';
								}
								opts.sort = sort;
								grid.populate();
							}
						}).css('cursor', 'pointer');
					}
					if (cm.wrap) {
						$(th).attr('wrap', cm.wrap);
					}
					if (cm.talign) {
						$(th).addClass(cm.talign);
					} else if (cm.align) {
						$(th).addClass(cm.align);
					}
					if (cm.width) {
						$(th).attr('width', cm.width);
					}
					if (cm.hide) {
						th.hidden = true;
					}
					$(tr).append(th);
				})
				$(thead).append(tr);
				$(tr).addClass('igrid-title')
			},
			createThead : function(table) {
				var thead = document.createElement('thead');
				grid.createTitle(thead);
				grid.createBar(thead);
				$(table).append($(thead));
			},
			createTfoot : function(table) {
				//var cols = $('thead .igrid-title th', table).length;
				//var td = $('<td></td>').attr('colspan', cols);
				var footer="";
				if (opts.showPagination) {
					footer=$("<div>").addClass("list-bottom clearfix").html('<span class="finfo"></span><div class="pages"></div>');
					//td.append('<div class=""><span class="finfo"></span><div class="pages"></div></div>');
				}
				//$(table).append($('<tfoot></tfoot>').append(td));
				grid.holder.append(footer);
			},
			createTable : function() {
				var table = document.createElement('table');
				table.cellPadding = 0;
				table.cellSpacing = 0;
				$(table).addClass('list-table');
				grid.createThead(table);
				table.appendChild(document.createElement('tbody'));
				grid.createTfoot(table);
				return table;
			},
			createLayer : function() {
				grid.layer = $('<div class="igrid-layer"><div></div></div>');
			},
			createMsg : function() {
				var tr = document.createElement('tr');
				var td = document.createElement('td');
				var len = $('thead tr.igrid-title th', grid.table).length;
				$(td).attr("colspan", len).append('<div class="igrid-msg"></div>');
				grid.msg = $(tr).append($(td));
			},
			initGrid : function(t) {
				grid.holder = $(t);
				grid.holder.css({
					'position' : 'relative',
					'min-height' : '100px'
				});
				grid.table = grid.createTable();
				// set table to dom tree.
				grid.holder.prepend(grid.table);
				grid.createMsg();
				grid.createLayer();

			}
		};

		grid.initGrid(t);

		if (opts.url && opts.autoload) {
			grid.populate();
		}
		t.igrid = grid;
	}

	var docloaded = false;
	$(document).ready(function() {
		docloaded = true;
	});
	jQuery.fn.grid = function(opts, param) {
		if (typeof opts == 'string') {
			return jQuery.fn.grid.methods[opts](this[0], param);
		}
		opts = jQuery.extend({
			structure : []
		}, opts || {});

		return this.each(function() {
			if (!docloaded) {
				$(this).hide();
				var t = this;
				$(document).ready(function() {
					$.igrid(t, opts);
				});
			} else {
				$.igrid(this, opts);
			}
		});
	}

	$.fn.grid.methods = {
		// 修改表格初始化参数
		option : function(jq, options) {
			jq.igrid.option(options);
		},
		// 选中所有
		selectAll : function(jq, options) {
			jq.igrid.selectAll(options);
		},
		// 取消所有选中
		unselectAll : function(jq, options) {
			jq.igrid.unselectAll(options);
		},
		// 重新加载表格
		reload : function(jq, options) {
			jq.igrid.reload(options);
		},
		// 获取选中的标识，如果选中多个，返回第一个，未选中返回Null
		getSelectedId : function(jq, options) {
			return jq.igrid.getSelectedId(options);
		},
		// 获取选中的标识数组
		getSelectedIds : function(jq, options) {
			return jq.igrid.getSelectedIds(options);
		},
		// 根据ID获取数据项
		getItem : function(jq, options) {
			return jq.igrid.getItem(options);
		},
		// 获取数据列表
		getItems : function(jq, options) {
			return jq.igrid.getItems(options);
		}
	}

	$.rowbtn = function(btnName, clsName, id) {
		var html = '<a vid="{id}" class="btn btn-mini btn-grid {clsName}">{btnName}</a>';
		return html.replace('{id}', id).replace('{clsName}', clsName).replace('{btnName}', btnName);
	}

})(jQuery);
