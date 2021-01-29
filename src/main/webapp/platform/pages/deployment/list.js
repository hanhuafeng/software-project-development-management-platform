layui.extend({
    admin: '{/}./../../static/js/admin',
    common: '{/}./../../static/js/common',
    commonCtl: '{/}./../../static/js/commonCtl'
});

layui.use(['table', 'jquery', 'form', 'admin', 'common', 'commonCtl'], function () {
    var table = layui.table,
        $ = layui.jquery,
        common = layui.common,
        commonCtl = layui.commonCtl,
        form = layui.form;
    let dataTable;
    let fatherId = 0;
    console.log(fatherId);
    dataTable = table.render({
        elem: '#articleList',
        cellMinWidth: 80,
        url: commonCtl.SUBSCRIPTION_GET(),
        where: {
            //设定异步数据接口的额外参数，任意设
            'createPersonName': ''
        },
        cols: [
            [{
                field: 'subscriptionId', title: '记录编号', sort: true
            }, {
                field: 'projectName', title: '子项目名称', sort: true
            }, {
                field: 'projectId', title: '子项目编号', sort: true
            }, {
                field: 'createUserTrueName', title: '申请人', sort: true
            }, {
                field: 'createTime', title: '申请时间', sort: true
            }, {
                field: 'reason', title: '申请理由', sort: true
            }, {
                field: 'toUserTrueName', title: '被流转人姓名', sort: true
            }, {
                field: 'operate', title: '操作', toolbar: '#operateTpl', unresize: true
            }]
        ],
        event: true,
        page: true,
        parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.list //解析数据列表
            };
        },
        response: {
            statusName: 'code' //规定数据状态的字段名称，默认：code
            , statusCode: 10000 //规定成功的状态码，默认：0
            , msgName: 'msg' //规定状态信息的字段名称，默认：msg
            // ,countName: 'total' //规定数据总数的字段名称，默认：count
            , dataName: 'data' //规定数据列表的字段名称，默认：data
        },
        done: function (res, curr, count) {
        }
    });
    /*
     *数据表格中form表单元素是动态插入,所以需要更新渲染下
     * http://www.layui.com/doc/modules/form.html#render
     * */
    $(function () {
        form.on('submit(search)', function (data) {
            var containSpecial = RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);
            if (!containSpecial.test(data.field.username)) {
                dataTable.reload({
                    where: {
                        //设定异步数据接口的额外参数，任意设
                        'createPersonName': data.field.username.replace(/&/g, "&amp;")
                            .replace(/</g, "&lt;")
                            .replace(/>/g, "&gt;")
                            .replace(/"/g, "&quot;")
                            .replace(/©/g, "&copy;")
                    }
                });
            } else {
                layer.msg('不能有特殊字符!', {
                    icon: 5,
                    time: 1000
                });
            }
            return false;
        });
        form.render();
    });

    window.deal = function (id,status) {
        let msg = "确认要同意吗？"
        if(status == -1){
            msg = "确认要拒绝吗"
        }
        layer.confirm(msg, function (index) {
            let param = {
                "id":id,
                "status":status
            }
            common.ajax(commonCtl.UPDATE_SUBSCRIPTION_STATUS(),'put', 'json',param, (callback) => {
                if (callback.code == 10000) {
                    layer.msg('审批成功!', {
                        icon: 1,
                        time: 1000
                    }, function () {
                        reloadTableData();
                    });
                } else {
                    layer.msg('审批失败,错误代码:' + callback.code, {
                        icon: 5,
                        time: 1000
                    });
                }

            }, (error) => {
                layer.msg('审批失败,错误：' + error, {
                    icon: 5,
                    time: 1000
                });
            })
        });
    }
    window.reloadTableData = function () {
        dataTable.reload();
    }
});
