/**
 * 初始化job详情对话框
 */
var StoreBaseInfoDlg = {
    storeBaseInfoData : {}
};

/**
 * 清除数据
 */
StoreBaseInfoDlg.clearData = function() {
    this.storeBaseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StoreBaseInfoDlg.set = function(key, val) {
    this.storeBaseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StoreBaseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StoreBaseInfoDlg.close = function() {
    parent.layer.close(window.parent.StoreBase.layerIndex);
}

/**
 * 收集数据
 */
StoreBaseInfoDlg.collectData = function() {
    this
    .set('id')
    .set('createDate')
    .set('modifyDate')
    .set('version')
    .set('storeType')
    .set('name')
    .set('address')
    .set('contact')
    .set('phone');
}

/**
 * 提交添加
 */
StoreBaseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/storeBase/add", function(data){
        Feng.success("添加成功!");
        window.parent.StoreBase.table.refresh();
        StoreBaseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.storeBaseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StoreBaseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/storeBase/update", function(data){
        Feng.success("修改成功!");
        window.parent.StoreBase.table.refresh();
        StoreBaseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.storeBaseInfoData);
    ajax.start();
}

$(function() {

});
