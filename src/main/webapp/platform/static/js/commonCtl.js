layui.define([], function(exports) {
    var PREFIX = 'http://127.0.0.1:8888/software/project/development/management/server';
    var obj = {
        GET_USER_PERMISSION: function(){
            return PREFIX + '/user/getUserPermission';
        },
        LOGIN_URL: function(){
            return PREFIX + '/user/login';
        },
        USER_LIST:function () {
            return PREFIX + '/user/getUserListByPage';
        },
        USER_ADD:function () {
            return PREFIX + '/user/add';
        },
        DELETE_USER:function () {
            return PREFIX + '/user/delete';
        },
        GET_USER_BY_USER_ID:function () {
            return PREFIX + '/user/getUserInfoById';
        },
        UPDATE_USER:function () {
            return PREFIX + '/user/update';
        },
        GET_USERS_BY_USER_TYPE:function () {
            return PREFIX + '/user/getUsersByUserType?userType=';
        },
        ADD_NEW_PROJECT:function () {
            return PREFIX + '/project/add';
        },
        GET_PROJECT_BY_PAGE:function () {
            return PREFIX + '/project/get/page';
        },
        DELETE_PROJECT:function () {
            return PREFIX + '/project/delete?id=';
        },
        UPDATE_PROJECT:function () {
            return PREFIX + '/project/update';
        },
        GET_PROJECT_BY_ID:function () {
            return PREFIX + '/project/getById?id=';
        },
        UPDATE_PROJECT_STATUS:function () {
            return PREFIX + '/project/updateStatus';
        },
        GET_PROJECT_LOG:function () {
            return PREFIX + '/log/getProjectLog';
        },
        ADD_NEW_PROJECT_LOG:function () {
            return PREFIX + '/log/addNewLog';
        },
        SUBSCRIPTION_TO_OTHER_PERSON:function () {
            return PREFIX + '/project/subscriptionToOtherPerson';
        },
        UPDATE_SUBSCRIPTION_STATUS:function () {
            return PREFIX + '/project/updateSubscriptionStatus';
        },
        SUBSCRIPTION_GET:function () {
            return PREFIX + '/subscription/get';
        },
        ADD_NEW_FUNDS:function () {
            return PREFIX + '/funds/add';
        },
        GET_FUNDS:function () {
            return PREFIX + '/funds/get';
        },
        DEAL_FUNDS_SUBSCRIPTION:function () {
            return PREFIX + '/funds/deal';
        },
        GET_PROJECT_CHARTS:function () {
            return PREFIX + '/project/getProjectCharts';
        },
        GET_PROJECT_STATUS_NUM:function () {
            return PREFIX + '/project/getProjectStatusNum';
        },
    };
    exports('commonCtl', obj);
});


