/**
 * Created by admin on 2015/12/29.
 */
(function($){
    $.fn.dialog=function(method){
        // 如果第一个参数是字符串, 就查找是否存在该方法, 找到就调用; 如果是object对象, 就调用init方法;.
        if (methods[method]) {
            // 如果存在该方法就调用该方法
            // apply 是吧 obj.method(arg1, arg2, arg3) 转换成 method(obj, [arg1, arg2, arg3]) 的过程.
            // Array.prototype.slice.call(arguments, 1) 是把方法的参数转换成数组.
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            // 如果传进来的参数是"{...}", 就认为是初始化操作.
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.dialog');
        }
    };
    // 不把方法扩展在 $.fn.dialog 上. 在闭包内建个"methods"来保存方法, 类似共有方法.
    var methods = {
        /**
         * 初始化方法
         * @param _options
         * @return {*}
         */
        init : function (_options) {
            return this.each(function () {
                var $this = $(this);
                var opts = $.extend({}, $.fn.dialog.defaults, _options);
                opts.title=$this.attr("title")!=""?$this.attr("title"):opts.title;
                methods.publicMethod($this,opts);
            });
        },
        publicMethod : function(obj,opts){
            private_methods.mask.show();
            var dialogpanel=$("<div>").addClass("panel-box dialog-panel").data("obj",obj).appendTo("body").append(private_methods.dialogHead(obj,opts),obj,private_methods.dialogHandle(obj,opts)).bind("click",function(e){
                e.stopPropagation();
            }).show(),winwidth=$(window).width(),winHeight=$(window).height();
            var dialogcss={"width":opts.width,"height":opts.height};
            dialogcss.top=opts.position[0]=="center"?(winHeight-opts.height)*0.5:opts.position[0];
            dialogcss.left=opts.position[1]=="center"?(winwidth-opts.width)*0.5:opts.position[1];
            dialogpanel.css(dialogcss);
            obj.height(opts.height-120);
        },
        close:function(){
            $(".dialog-panel,#mask").hide();
            //private_methods.dialoClose(obj);
        }
    };
    // 私有方法
    var private_methods = {
        dialogPanel:function(obj){
            var div="";
            $(".dialog-panel").each(function(){
                if($(this).data("obj")==obj){
                    div=$(this);
                }
            });
            return div;
        },
        dialogHead:function(obj,opts){
            var head=$("<div>").addClass("dialog-head").html("<span>"+opts.title+"</span>");
            var closebtn=$("<a>").attr("href","javascript:;").html("<i class='custom-icon icon-close'></i>").addClass("dialog-close").click(function(){
                private_methods.dialoClose(obj);
            });
            head.append(closebtn);
            return head;
        },
        dialogHandle:function(obj,opts){
            var handle=$("<div>").addClass("dialog-handle"),
                enterBtn=$("<a>").attr("href","javascript:;").addClass("btn btn-small bg-blue").text("确认").click(function(){
                    opts.enterhandle();
                }),
                cancelBtn=$("<a>").attr("href","javascript:;").addClass("btn btn-small bg-orange").text("取消").click(function(){
                    private_methods.dialoClose(obj);
                });
            handle.append(enterBtn,cancelBtn);
            return handle;
        },
        mask:$("<div>",{"id":"mask"}).addClass("mask").height($(document).height()).appendTo("body"),
        dialoClose:function(obj){
            this.dialogPanel(obj).hide();
            this.mask.hide();
        }
    };
    // 默认参数
    $.fn.dialog.defaults = {
        width: 640,
        height:200,
        title:"",
        position:["center","center"],
        enterhandle:function(){}
    };
})(jQuery);