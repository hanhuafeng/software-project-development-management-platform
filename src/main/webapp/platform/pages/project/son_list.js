layui.extend({
    admin: '{/}./../../static/js/admin',
    common: '{/}./../../static/js/common',
    commonCtl: '{/}./../../static/js/commonCtl'
});

layui.use(['table', 'jquery', 'form', 'admin', 'commonCtl', 'common', 'laytpl'], function () {
    var table = layui.table,
        $ = layui.jquery,
        common = layui.common,
        commonCtl = layui.commonCtl,
        laytpl = layui.laytpl,
        form = layui.form;
    let dataTable;
    let fatherId = common.getParams('fatherId');
    let min = common.getParams('min');
    let max = common.getParams('max');
    if (fatherId == null || typeof fatherId == 'undefined') {
        fatherId = 0;
    }
    const data = { //数据
        "fatherId": fatherId,
        "min": min,
        "max": max
    };
    const getTpl = document.getElementById("addBtnTpl").innerHTML
        , addBtn = document.getElementById('addBtn');
    laytpl(getTpl).render(data, function (html) {
        addBtn.innerHTML = html;
    });
    dataTable = table.render({
        elem: '#articleList',
        cellMinWidth: 80,
        url: commonCtl.GET_PROJECT_BY_PAGE(),
        where: {
            //设定异步数据接口的额外参数，任意设
            'projectName': '',
            'fatherId': fatherId,
        },
        cols: [
            [{
                field: 'id', title: 'ID', sort: true
            }, {
                field: 'name', title: '项目名称', sort: true
            }, {
                field: 'describeInfo', title: '项目描述', sort: true
            }, {
                field: 'dutyPersonName', title: '负责人姓名', sort: true
            }, {
                field: 'startTime', title: '开始时间', sort: true
            }, {
                field: 'endTime', title: '结束时间', sort: true
            }, {
                field: 'status', title: '项目状态', templet: '#statusTpl', unresize: true
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
                        'projectName': data.field.projectName.replace(/&/g, "&amp;")
                            .replace(/</g, "&lt;")
                            .replace(/>/g, "&gt;")
                            .replace(/"/g, "&quot;")
                            .replace(/©/g, "&copy;"),
                        'fatherId': fatherId,
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
        form.on('switch(status_change_status)', function (data) {
            let check = data.elem.checked;
            let id = data.elem.value;
            let msg = '确定要将状态修改为已完成吗？\n注意：此操作会将所有子任务的状态改为完成！'
            if (!data.elem.checked) {
                msg = '确定要将状态修改为未完成吗?'
            }
            layer.confirm(msg, function (index) {
                common.ajax(commonCtl.UPDATE_PROJECT_STATUS(), 'PUT', 'json', {
                    'id': id,
                    'status': check ? '1' : '0'
                }, (callback) => {
                    if (callback.code == 10000) {
                        layer.msg('修改状态成功!', {
                            icon: 1,
                            time: 1000
                        });
                    } else {
                        layer.msg('修改状态失败,错误代码:' + callback.code, {
                            icon: 5,
                            time: 1000
                        });
                    }

                }, (error) => {
                    layer.msg('修改状态失败,错误信息:' + JSON.stringify(error), {
                        icon: 5,
                        time: 1000
                    });
                })
                layer.close(index);
            }, function () {
                data.elem.checked = !check;
                form.render();
            });
        });
        common.get(commonCtl.GET_PROJECT_BY_ID() + fatherId, 'json', (callback) => {
            if (callback.code == 10000) {
                initFatherProjectData(callback.data);
            } else {
                layer.msg('获取父页面信息失败,错误代码:' + callback.code, {
                    icon: 5,
                    time: 1000
                });
            }

        }, (error) => {
            layer.msg('获取父页面信息失败,错误：' + error, {
                icon: 5,
                time: 1000
            });
        })
        form.render();
    });
    window.initFatherProjectData = function (data) {
        $('#father_name').text(data.name);
        $('#father_start_end_time').text(data.startTime + ' - ' + data.endTime);
        $('#father_duty_person').text(data.dutyPersonName);
    }

    window.projectDelete = function (id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            common.delete(commonCtl.DELETE_PROJECT() + id, 'json', (callback) => {
                if (callback.code == 10000) {
                    layer.msg('删除成功!', {
                        icon: 1,
                        time: 1000
                    }, function () {
                        reloadTableData();
                    });
                } else {
                    layer.msg('删除失败,错误代码:' + callback.code, {
                        icon: 5,
                        time: 1000
                    });
                }

            }, (error) => {
                layer.msg('删除失败,错误：' + error, {
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

function delAll(argument) {
    var data = tableCheck.getData();
    layer.confirm('确认要删除吗？' + data, function (index) {
        //捉到所有被选中的，发异步进行删除
        layer.msg('删除成功', {
            icon: 1
        });
        $(".layui-form-checked").not('.header').parents('tr').remove();
    });
}
