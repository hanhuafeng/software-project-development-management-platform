layui.define(['jquery'], function(exports){
    var $ = layui.jquery;
    var obj = {
        ajax: function (url, type, dataType, data, callback,error) {
            $.ajax({
                url: url,
                type: type,
                dataType: dataType,
                contentType: "application/json",
                data: JSON.stringify(data),
                success: callback,
                error:error
            });
        },
        get: function (url, dataType, callback,error) {
            $.ajax({
                url: url,
                type: 'GET',
                dataType: dataType,
                contentType: "application/json",
                success: callback,
                error:error
            });
        },
        notAsyncGet: function (url, dataType, callback,error) {
            $.ajax({
                url: url,
                async: false,
                type: 'GET',
                dataType: dataType,
                contentType: "application/json",
                success: callback,
                error:error
            });
        },
        delete: function (url, dataType, callback,error) {
            $.ajax({
                url: url,
                type: 'DELETE',
                dataType: dataType,
                contentType: "application/json",
                success: callback,
                error:error
            });
        },
        getParams: function (name) {
            var pos, str, para, parastr;
            var array = []
            str = window.location.href;
            if (str.split("?")[1] !== undefined && str.split("=")[1] !== undefined) {
                parastr = str.split("?")[1];
                parastr = decodeURIComponent(parastr);
                var arr = []
                arr = parastr.split("&");
                for (var i = 0; i < arr.length; i++) {
                    array[arr[i].split("=")[0]] = arr[i].split("=")[1];
                }
            }
            return array[name];//project为所要获取的参数
        },
        /**
         * 判断时间是否在某个区间内
         * @param beginDateStr
         * @param endDateStr
         * @returns {boolean}
         */
        isDuringDate: function (beginDateStr, endDateStr) {
            var curDate = new Date(),
                beginDate = new Date(beginDateStr),
                endDate = new Date(endDateStr);
            if (curDate >= beginDate && curDate <= endDate) {
                return true;
            }
            return false;
        }
    };
    //输出接口
    exports('common', obj);
});
