/**
 * 检查项信息管理初始化
 */
var Examine = {
    id: "ExamineTable",	// 表格id
    seItem: null,		// 选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Examine.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '创建日期', field: 'createDate', visible: false, align: 'center', valign: 'middle'},
            {title: '修改日期', field: 'modifyDate', visible: false, align: 'center', valign: 'middle'},
            {title: '版本号', field: 'version', visible: false, align: 'center', valign: 'middle'},
            {title: '排序序号', field: 'orders', visible: true, align: 'center', valign: 'middle'},
            {title: '父节点', field: 'parent', visible: true, align: 'center', valign: 'middle'},
            {title: '检查项的显示序号，例如1；1.1；1.2.1；', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '是否废弃，0-不废弃；1-废弃', field: 'deprecate', visible: true, align: 'center', valign: 'middle'},
            {title: '检查项每条详情内容', field: 'item', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'memo', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Examine.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Examine.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加检查项信息
 */
Examine.openAddExamine = function () {
    var index = layer.open({
        type: 2,
        title: '添加检查项信息',
        area: ['800px', '420px'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/examine/examine_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看检查项信息详情
 */
Examine.openExamineDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '检查项信息详情',
            area: ['800px', '420px'], // 宽高
            fix: false, // 不固定
            maxmin: true,
            content: Feng.ctxPath + '/examine/examine_update/' + Examine.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除检查项信息
 */
Examine.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/examine/delete", function (data) {
            Feng.success("删除成功!");
            Examine.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("examineId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询检查项信息列表
 */
Examine.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Examine.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Examine.initColumn();
    var table = new BSTable(Examine.id, "/examine/list", defaultColunms);
    table.setPaginationType("client");
    Examine.table = table.init();
});
