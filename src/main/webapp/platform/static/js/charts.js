layui.define(['jquery'], function(exports){
    var charts = {
        getCharts: function (ydata,successList,errorList,elementId) {
            // 基于准备好的dom，初始化echarts实例
            var chartDom = document.getElementById(elementId);
            var myChart = echarts.init(chartDom);
            var option;
            option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['完成量(有效天数)', '超时次数(次)']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'value',
                        boundaryGap: [0, 0.01]
                    }
                ],
                yAxis: [
                    {
                        type: 'category',
                        data: ydata
                    }
                ],
                series: [
                    {
                        name: '完成量(有效天数)',
                        type: 'bar',
                        data: successList
                    },
                    {
                        name: '超时次数(次)',
                        type: 'bar',
                        data: errorList
                    }
                ]
            };
            option && myChart.setOption(option);
        },
        getCircleCharts: function (data,elementId) {
            var chartDom = document.getElementById(elementId);
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '40',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: data
                    }
                ]
            };

            option && myChart.setOption(option);
        }
    }

    exports('charts', charts);
});
