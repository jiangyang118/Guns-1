/**
 * 初始化任务管理详情对话框
 */
var TaskBaseInfoDlg = {
    taskBaseInfoData : {}
};

/**
 * 清除数据
 */
TaskBaseInfoDlg.clearData = function() {
    this.taskBaseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TaskBaseInfoDlg.set = function(key, val) {
    this.taskBaseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TaskBaseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TaskBaseInfoDlg.close = function() {
    parent.layer.close(window.parent.TaskBase.layerIndex);
}

/**
 * 收集数据
 */
TaskBaseInfoDlg.collectData = function() {
    this
    .set('id')
    .set('createDate')
    .set('modifyDate')
    .set('version')
    .set('beginDate')
    .set('endDate')
    .set('appointer')
    .set('assignee1')
    .set('assignee2')
    .set('store')
    .set('sn')
    .set('status')
    .set('notiStatus');
}

/**
 * 提交添加
 */
TaskBaseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/taskBase/add", function(data){
        Feng.success("添加成功!");
        window.parent.TaskBase.table.refresh();
        TaskBaseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.taskBaseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TaskBaseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/taskBase/update", function(data){
        Feng.success("修改成功!");
        window.parent.TaskBase.table.refresh();
        TaskBaseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.taskBaseInfoData);
    ajax.start();
}

$(function() {

});
