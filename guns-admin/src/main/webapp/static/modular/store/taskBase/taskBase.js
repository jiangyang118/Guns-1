/**
 * 任务管理管理初始化
 */
var TaskBase = {
    id: "TaskBaseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TaskBase.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '开始日期', field: 'beginDate', visible: true, align: 'center', valign: 'middle'},
            {title: '结束日期', field: 'endDate', visible: true, align: 'center', valign: 'middle'},
            {title: '任命者', field: 'appointer', visible: true, align: 'center', valign: 'middle'},
            {title: '执法者1', field: 'assignee1', visible: true, align: 'center', valign: 'middle'},
            {title: '执法者2', field: 'assignee2', visible: true, align: 'center', valign: 'middle'},
            {title: '被检查的店铺', field: 'store', visible: true, align: 'center', valign: 'middle'},
            {title: '任务编号', field: 'sn', visible: true, align: 'center', valign: 'middle'},
            {title: '任务状态：0-未检查；1-检查中；2-合格；3-不合格；4-基本合格', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '通知被检查店铺的状态：0-未通知；1-已通知', field: 'notiStatus', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TaskBase.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TaskBase.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加任务管理
 */
TaskBase.openAddTaskBase = function () {
    var index = layer.open({
        type: 2,
        title: '添加任务管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/taskBase/taskBase_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看任务管理详情
 */
TaskBase.openTaskBaseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '任务管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/taskBase/taskBase_update/' + TaskBase.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除任务管理
 */
TaskBase.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/taskBase/delete", function (data) {
            Feng.success("删除成功!");
            TaskBase.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("taskBaseId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询任务管理列表
 */
TaskBase.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TaskBase.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TaskBase.initColumn();
    var table = new BSTable(TaskBase.id, "/taskBase/list", defaultColunms);
    table.setPaginationType("client");
    TaskBase.table = table.init();
});
