/**
 * Created by admin on 2015/12/17.
 */
define(function(){
    var mydate=new Date(),weekday=[];
    for(var i=0;i<7;i++){
        weekday.push(date(i-6));
    }
    function date(day){
        var myDate = new Date();
        myDate.setDate(myDate.getDate() + parseFloat(day));
        return Appendzero(myDate.getMonth()+1)+"-"+Appendzero(myDate.getDate());
    }
    function Appendzero (obj) {
        if (obj < 10) return "0" + obj; else return obj;
    }
    console.log(weekday);
    var option={
        lineoption:{
            grid:{
                x:22,
                y:30,
                x2:10,
                y2:85,
                borderWidth:0
            },
            legend: {
                y:260,
                data:['检票额','订单额','退票额']
            },
            xAxis : [
                {
                    type : 'category',
                    data :weekday,
                    splitLine:false,
                    axisLabel:{
                        textStyle: {
                            color: "#666",
                            fontSize: 12
                        }
                    },
                    axisLine:{
                        lineStyle:{
                            color:"#e5e5e5",
                            width:1
                        }
                    },
                    axisTick:{
                        inside:true,
                        lineStyle:{
                            color:"#e5e5e5",
                            width:1
                        }
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    splitLine:{
                        lineStyle:{
                            color: ['#e5e5e5'],
                        }
                    },
                    axisLine:false
                }
            ],
            series : [
                {
                    name:'检票额',
                    type:'line',
                    symbol:'circle',
                    data:[20,30,40,50,20,50,60]
                },
                {
                    name:'订单额',
                    type:'line',
                    symbol:'circle',
                    data:[20,20,22,35,43,61,80]
                },{
                    name:'退票额',
                    type:'line',
                    symbol:'circle',
                    data:[10,30,25,32,30,25,30]
                }
            ]
        },
        peioption:function(color,data){
            var opts={
                series : [
                    {
                        center:['50%','50%'],
                        type:'pie',
                        radius : ['90%', '100%'],
                        itemStyle : {
                            normal : {
                                label : {
                                    show : true,
                                    position : 'center',
                                    textStyle : {
                                        fontSize : '24',
                                        color:color
                                    },
                                    formatter:function(params){
                                        if(params.name==opts.series[0].data[0].name){
                                            return params.value;
                                        }
                                    }
                                },
                                labelLine : {
                                    show : false
                                }
                            }
                        },
                        data:data
                    }
                ]
            }
            return opts;
        }
    };
    return option;
});
