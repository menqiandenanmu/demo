/**
 * Created by admin on 2015/12/22.
 */
(function($){
    $.fn.extend({
        customInput:function(){
            $(this).each(function(i){
                if($(this).is('[type=checkbox],[type=radio]')){
                    var input=$(this);
                    var inputType = (input.is('[type=checkbox]')) ? 'checkbox' : 'radio';
                    var label = $('label[for='+input.attr('id')+']');
                    if(label.length==0){
                        label=$('<label>',{
                            "for":input.attr('id')
                        });
                    }
                    var allInputs = $('input[name="'+input.attr('name')+'"]');
                    if(!input.parent().hasClass("custom-checkbox")){
                        label.addClass('custom-label').prepend($("<i>").addClass("custom-icon "+inputType+"-icon"));
                        $("<span>").addClass("custom-"+inputType).insertBefore(input).append(input,label);
                    }else{
                        //console.log(input.attr("checked"));
                    };
                    input.bind('updateState',function(){
                        if(input.is(':checked')){
                            if(input.is(':radio')){
                                allInputs.each(function(){
                                    $('label[for='+$(this).attr('id')+']').removeClass('checked');
                                });
                            };
                            label.addClass('checked');
                        }
                        else if(input.is(':disabled')){
                            label.addClass('disabled');
                        }
                        else {
                            label.removeClass('checked');
                        }
                    }).trigger('updateState').click(function(){
                        $(this).trigger('updateState');
                    });
                }
            });
        },
        numSpinner: function(options) {
            var defaults = {
                increment: 1,
                min: 0,
                max: null,
                onChange: function(evl,value) {}
            }
            var opts = $.extend(defaults, options);
            return this.each(function() {
                var $this = $(this);
                var contentBox = $("<div>", {
                    "class": "numSpinnerBox custom-text"
                });
                var arrowup = $("<a>", {
                    "class": "sub",
                    "href": "javascript:void(0);"
                }).html("<i class='custom-icon icon-sub'></i>");
                var arrowdown = $("<a>", {
                    "class": "add",
                    "href": "javascript:void(0);"
                }).html("<i class='custom-icon icon-add'></i>");
                if(!$this.parent().hasClass("numSpinnerBox")){
                    $this.after(contentBox);
                    contentBox.append($this);
                    $this.before(arrowup).after(arrowdown);
                };
                $this.val($this.val() || 0);
                if($this.val()==opts.min){
                    arrowup.addClass("disabled");
                }
                if($this.val()==opts.max){
                    arrowdown.addClass("disabled");
                }
                arrowup.click(function() {
                    var num=0,optnum=$this.val()-opts.increment;
                    if(opts.min!=null){
                        if(optnum<opts.min){
                            num=opts.min;
                            $(this).addClass("disabled");
                        }else{
                            num=optnum;
                            $(this).removeClass("disabled");
                        }
                    }else{
                        num=optnum;
                    }
                    arrowclick(num);
                    $this.val(num);
                    opts.onChange($this,$this.val());
                });
                arrowdown.click(function() {
                    var num=0,optnum=parseInt($this.val())+opts.increment;
                    if(opts.max!=null){
                        if(optnum>opts.max){
                            num=opts.max;
                            $(this).addClass("disabled");
                        }else{
                            num=optnum;
                        }
                    }
                    else{
                        num=optnum;
                    }
                    arrowclick(num);
                    $this.val(num);
                    opts.onChange($this,$this.val());
                });
                $this.bind('keyup',function(){
                    var val=$(this).val().replace(/[^\d]/g,'')||1;
                    if(opts.min!=null){
                        val=parseFloat(val)<parseFloat(opts.min)?opts.min:val;
                    }
                    if(opts.max!=null){
                        val=parseFloat(val)>parseFloat(opts.max)?opts.max:val;
                    }
                    $(this).val(val);
                }).blur(function(){
                    opts.onChange($this,$this.val());
                });
                function arrowclick(num){
                    if(num==opts.min){
                       $(".numSpinnerBox").find(".sub").addClass("disabled");
                    }else{
                        $(".numSpinnerBox").find(".sub").removeClass("disabled");
                    }
                    if(num==opts.max){
                        $(".numSpinnerBox").find(".add").addClass("disabled");
                    }else{
                        $(".numSpinnerBox").find(".add").removeClass("disabled");
                    }
                }
            });
        },
        selectbox:function(){
            this.each(function(){
                var select=$(this),selectoptions=select.find("option");
                var selectBox=$("<div>").addClass("select-box custom-text").append("<input type='text' readonly />","<a href='javascript:;' class='select-btn'><i class='custom-icon icon-select-up'></i></a>");
                var dropDown=$("<ul>").addClass("dropDown-box panel-box");
                $("body").append(dropDown);
                select.before(selectBox).hide();
                var dropDownHtml="";
                $.each(selectoptions,function(i){
                    var val=selectoptions[i].getAttribute("value");
                    dropDownHtml+="<li ";
                    if(selectoptions[i].selected){
                        dropDownHtml+="class='selected'";
                        selectBox.find("input").val(selectoptions[i].text);
                    }
                    dropDownHtml+=" data-val='"+val+"'>"+selectoptions[i].text+"</li>";
                });
                dropDown.html(dropDownHtml);
                selectBox.bind("click",function(e){
                    var top=$(this).offset().top,left=$(this).offset().left;
                    $(".panel-box").hide();
                    if($(this).find("i").hasClass("icon-select-down")){
                        $(this).find("i").removeClass("icon-select-down");
                        dropDown.hide();
                    }else{
                        $(this).find("i").addClass("icon-select-down");
                        dropDown.show();
                    }
                    dropDown.css({
                        top:top+31,
                        left:left
                    });
                    e.stopPropagation();
                });
                dropDown.on('click','li',function(e){
                    var index=$(this).index();
                    dropDown.find('li').removeClass("selected");
                    $(this).addClass('selected');
                    dropDown.hide();
                    selectBox.find("i").removeClass("icon-select-down");
                    selectBox.find("input").val($(this).text());
                    select.find('option:eq('+index+')').attr("selected","selected");
                    select.trigger("change");
                    e.stopPropagation();
                }).on("hover","li",function(){
                    dropDown.find('li').removeClass("selected");
                    $(this).addClass('selected');
                });
                $(document).click(function(){
                    selectBox.find("i").removeClass("icon-select-down");
                    dropDown.hide();
                    var index=select.find("option:selected").index();
                    dropDown.find('li').removeClass("selected");
                    dropDown.find("li:eq("+index+")").addClass("selected");
                });
            });
        }
    });
})(jQuery);

$(function(){
    $('input').customInput();
    $("input.number").numSpinner();
    $("select").selectbox();
});
