/**
 * Created by admin on 2015/12/14.
 */
require.config({
    paths: {
        echarts: 'scripts/echarts'
    }
});
require(
    [
        'echarts',
        'echarts/chart/bar',
        'echarts/chart/line',
        'echarts/chart/pie',
        'echarts/echart-config'
    ],
    function (ec) {
        var option=require('echarts/echart-config');
        var myChartLine = ec.init(document.getElementById('line'));
        myChartLine.setOption(option.lineoption);
        var orderPei=ec.init(document.getElementById('order-pei')),checkedPei =ec.init(document.getElementById('checked-pei')),refundPei=ec.init(document.getElementById('refund-pei'));
        var orderPeidata=[{value:126, name:'本日订单量',itemStyle:{normal:{color:"#028dd2"}}},{value:0, name:'订单总数',itemStyle:{normal:{color:"#69696a"}}}];
        orderPei.setOption(option.peioption("#028dd2",orderPeidata));
        var checkedPeidata=[{value:16, name:'本日检票量',itemStyle:{normal:{color:"#3bc0c3"}}},{value:16, name:'本日未检票量',itemStyle:{normal:{color:"#f0f0f0"}}}];
        checkedPei.setOption(option.peioption("#3bc0c3",checkedPeidata));
        var refundPeidata=[{value:6, name:'本日退票量',itemStyle:{normal:{color:"#ff9900"}}},{value:10, name:'本日未退票量',itemStyle:{normal:{color:"#f0f0f0"}}}];
        refundPei.setOption(option.peioption("#ff9900",refundPeidata));

        $(window).scroll(function(){
            var top=$(this).scrollTop();
            if(top>30){
                $(".header-main").addClass('header-scroll');
            }else{
                $(".header-main").removeClass('header-scroll');
            }
        });
    }
);

$(document).ready(function() {
	$('a[data-id]').click(function(){
		var id = $(this).data("id");
		//移除所有属性
		$(".cur").removeClass("cur")
		//设置当前属性
		$("#"+id).attr("class","cur");
	});
	
});