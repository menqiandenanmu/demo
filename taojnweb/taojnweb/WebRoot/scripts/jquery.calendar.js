/**
 * Created by admin on 2015/12/24.
 */
(function($){
    $.fn.calendar=function(method){
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
            $.error('Method ' + method + ' does not exist on jQuery.calendar');
        }
    };
    // 不把方法扩展在 $.fn.calendar 上. 在闭包内建个"methods"来保存方法, 类似共有方法.
    var methods = {
        /**
         * 初始化方法
         * @param _options
         * @return {*}
         */
        init : function (_options) {
            return this.each(function (i) {
                var $this = $(this);
                var opts = $.extend({}, $.fn.calendar.defaults, _options);
                methods.publicMethod($this,private_methods.mydate(),i);
            });
        },
        publicMethod : function(obj,date,num){
            private_methods.calendarText(obj,date,num);
            $("<div>").addClass('calendar-panel panel-box').data("cdiv","c"+num).appendTo("body").click(function(e){
                e.stopPropagation();
            });
            $(document).click(function(){
                private_methods.closecalendar(num);
            });
        }
    };
    // 私有方法
    var private_methods = {
        mydate:function(){
            var mydate=new Date();
            return mydate;
        },
        calendarPanel:function(num){
            var obj="";
            $(".calendar-panel").each(function(){
                if($(this).data("cdiv")=="c"+num){
                    obj=$(this);
                }
            });
            return obj;
        },
        loadcalendar:function(obj,date,num){
            this.calendarPanel(num).html("").append(this.calendarHeader(obj,date,num),this.calendarMain(obj,date,num),this.calendarBottom(obj,num),this.monthPanel(obj,date,num),this.yearPanel(obj,date,num));
        },
        calendarText:function(obj,date,num){
            var text=$("<div>").addClass('calendar-text custom-text').html("<a href='javascript:;'><i class='custom-icon icon-date'></i></a>").bind('click',function(e){
                var val=obj.val(),newdate=date;
                if(val!=""){
                    var valarray=val.split("-");
                    newdate=new Date(valarray[0],parseInt(valarray[1])-1);
                }
                $(".panel-box").hide();
                private_methods.loadcalendar(obj,newdate,num);
                private_methods.calendarPanel(num).show();
                if($(this).offset().top+31+private_methods.calendarPanel(num).height()-$("body").scrollTop()>$(window).height()){
                    private_methods.calendarPanel(num).css({
                        top:$(this).offset().top-private_methods.calendarPanel(num).height()-26,
                        left:$(this).offset().left
                    });
                }else{
                    private_methods.calendarPanel(num).css({
                        top:$(this).offset().top+31,
                        left:$(this).offset().left
                    });
                }
                e.stopPropagation();
            });
            $(obj).before(text).attr("readonly","readonly");
            text.prepend($(obj));
        },
        calendarHeader:function(obj,date,num){
            var headBox=$("<div>").addClass('calendar-header custom-text'),
                prevBtn=this.calendarHeaderhandle("sub",date,num,obj),
                nextBtn=this.calendarHeaderhandle("add",date,num,obj),
                yearBox=$("<span>").addClass("year-span").html(date.getFullYear()).bind('click',function(){
                    private_methods.calendarPanel(num).find(".month-panel").hide();
                    headBox.find("span:eq(1)").removeClass("f8");
                    $(this).toggleClass("f8");
                    private_methods.calendarPanel(num).find(".year-panel").css({
                        top:41,
                        left:52
                    }).toggle();
                }),
                monthBox=$("<span>").html("<em>"+(date.getMonth()+1)+"</em>月").bind('click',function(){
                    private_methods.calendarPanel(num).find(".year-panel").hide();
                    headBox.find("span:eq(0)").removeClass("f8");
                    $(this).toggleClass("f8");
                    private_methods.calendarPanel(num).find(".month-panel").css({
                        top:41,
                        left:132
                    }).toggle();
                });
            headBox.append(prevBtn,yearBox,monthBox,nextBtn);
            return headBox;
        },
        calendarHeaderhandle:function(mold,date,num,obj){
            var handle=$("<a>").addClass(mold).attr("href","javascript:;").html('<i class="custom-icon icon-'+mold+'"></i>').bind('click',function(e){
                var year=date.getFullYear(),month=date.getMonth();
                switch (mold){
                    case "sub":
                        month--;
                        if (month < 0) {
                            year--;
                            month = 11;
                        }
                        break;
                    case "add":
                        month++;
                        if (month > 11) {
                            year++;
                            month = 0;
                        }
                        break;
                }
                var newdate=new Date(year,month);
                private_methods.loadcalendar(obj,newdate,num);
                e.stopPropagation();
            });
            return handle;
        },
        calendarWeek:function(){
            var thead=$("<thead>").html("<tr></tr>"),ths=[],weeks=["日","一","二","三","四","五","六"];
            $.each(weeks,function(i){
                var th='<th';
                if(i==0||i==6){
                    th+=' class="rest"';
                }
                th+='>'+weeks[i]+'</th>';
                ths.push(th);
            });
            thead.find("tr").html(ths.join(""));
            return thead;
        },
        calendarMain:function(obj,date,num){
            var calendarTable=$("<table>").addClass('calendar-table'),tbody=$("<tbody>"),html='<tr>',
                curmonth=date.getMonth(),curyear=date.getFullYear(),curdate=date.getDate(),today=this.mydate().getFullYear()+"-"+this.cover(this.mydate().getMonth()+1)+"-"+this.cover(this.mydate().getDate()),
                monthdays=this.monthdays(curyear)[curmonth];
            var firstDate=new Date(date.setDate(1)),prevmonth=curmonth-1<0?11:curmonth- 1,prevyear=curmonth-1<0?curyear-1:curyear;
            var nextmonth=curmonth+1>11?0:curmonth+ 1,nextyear=curmonth+1>11?curyear+1:curyear;
            for(var i=0;i<firstDate.getDay();i++){
                html+="<td class='old'>"+(this.monthdays(prevyear)[prevmonth]-firstDate.getDay()+i+1)+"</td>";
            }
            for(var j=0;j<monthdays;j++){
                var className="",curday=curyear+"-"+this.cover(curmonth+1)+"-"+this.cover(j+1);
                if(obj.val()==""&&today==curday){
                    className="selected";
                }else if(curday==obj.val()){
                    className="selected";
                }
                html+='<td class="'+className+'">'+(j+1)+'</td>';
                if((firstDate.getDay()+j+1)%7==0){
                    html += "<tr>";
                }
            }
            for(var k=0;k<42-firstDate.getDay()-monthdays;k++){
                html+="<td class='new'>"+(k+1)+"</td>";
                if ((firstDate.getDay() + monthdays + k + 1) % 7 == 0) {
                    html += "<tr>";
                }
            }
            tbody.html(html);
            calendarTable.append(this.calendarWeek(),tbody);
            calendarTable.find("td").click(function(e){
                var className=$(this).attr("class"),seclectdate="",day=$(this).text(),newdate=date;
                switch (className){
                    case "old":
                        seclectdate=prevyear+"-"+private_methods.cover(prevmonth+1)+"-"+private_methods.cover(day);
                        newdate=new Date(prevyear,prevmonth);
                        break;
                    case "new":
                        seclectdate=nextyear+"-"+private_methods.cover(nextmonth+1)+"-"+private_methods.cover(day);
                        newdate=new Date(nextyear,nextmonth);
                        break;
                    default :
                        seclectdate=curyear+"-"+private_methods.cover(curmonth+1)+"-"+private_methods.cover(day);
                        break;
                }
                obj.val(seclectdate);
                private_methods.closecalendar(num);
                private_methods.loadcalendar(obj,newdate,num);
                e.stopPropagation();
            });
            return calendarTable;
        },
        calendarBottom:function(obj,num){
            var bottom=$("<div>").addClass('calendar-bottom'),
                todayBtn=$('<a>').attr("href","javascript:;").text('今天').bind('click',function(e){
                    private_methods.loadcalendar(obj,private_methods.mydate(),num);
                    var today=private_methods.mydate().getFullYear()+"-"+private_methods.cover(private_methods.mydate().getMonth()+1)+"-"+private_methods.cover(private_methods.mydate().getDate());
                    obj.val(today);
                    e.stopPropagation();
                    private_methods.closecalendar(num);
                });
            bottom.append(todayBtn);
            return bottom;
        },
        yearPanel:function(obj,date,num){
            var panel=$("<div>").addClass("year-panel panel-box"),panelArray=[],newdate=date;
            var yeartext=date.getFullYear(),k=yeartext.toString().charAt(yeartext.toString().length-1,1);
            var arrowprev=$("<a>").attr("href","javascript:;").addClass("year-prev").html('<i class="custom-icon icon-sub"></i>').click(function(){
                yearpanel.find("a").removeClass("selected").each(function(i){
                    var t=$(this).text()-10;
                    if(private_methods.mydate().getFullYear()==t){
                        $(this).addClass("selected");
                    }
                    $(this).text(t);
                });
            });
            var arrownext=$("<a>").removeClass("selected").attr("href","javascript:;").addClass("year-next").html('<i class="custom-icon icon-add"></i>').click(function(){
                yearpanel.find("a").removeClass("selected").each(function(i){
                    var t=parseFloat($(this).text())+10;
                    if(private_methods.mydate().getFullYear()==t){
                        $(this).addClass("selected");
                    }
                    $(this).text(t);
                });
            });
            var yearpanel=$("<div>").addClass("years");
            for(var i=0;i<10;i++){
                var text=yeartext-(parseInt(k)-i);
                var yearitem=$("<a>").attr("href","javascript:;").text(text).click(function(){
                    var month=private_methods.calendarPanel(num).find(".calendar-header").find("span:eq(1)").find("em").text();
                    newdate=new Date($(this).text(),month-1);
                    private_methods.loadcalendar(obj,newdate,num);
                    panel.hide();
                });
                if(i%2==1){
                    yearitem.addClass("brnone");
                }
                if(i==9||i==8){
                    yearitem.addClass("bbnone");
                }
                if(text==date.getFullYear()){
                    yearitem.addClass("selected");
                }
                panelArray.push(yearitem);
            }
            yearpanel.append(panelArray);
            panel.append(arrowprev,yearpanel,arrownext);
            return panel;
        },
        monthPanel:function(obj,date,num){
            var panel=$("<div>").addClass("month-panel panel-box"),panelArray=[],newdate=date;
            for(var i= 0;i<12;i++){
                var a=$("<a>").attr("href","javascript:;").text((i+1)+"月").click(function(){
                    var index=$(this).index(),year=private_methods.calendarPanel(num).find(".calendar-header").find("span:eq(0)").text();
                    newdate=new Date(year,index);
                    private_methods.loadcalendar(obj,newdate,num);
                    panel.hide();
                });
                if(i%2==0){
                    a.addClass("br");
                }
                if(i==date.getMonth()){
                    a.addClass("selected");
                }
                panelArray.push(a);
            }
            panel.append(panelArray);
            return panel;
        },
        closecalendar:function(num){
            private_methods.calendarPanel(num).hide();
        },
        monthdays: function(year) {
            var monthdays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                monthdays[1] = 29;
            }
            return monthdays;
        },
        cover:function(s){
            return s < 10 ? '0' + s: s;
        }
    };
    // 默认参数
    $.fn.calendar.defaults = {
        width: "auto",
        onClick: function(evl,date) {}
    };
})(jQuery);

$(function(){
    $(".calendar").calendar();
});