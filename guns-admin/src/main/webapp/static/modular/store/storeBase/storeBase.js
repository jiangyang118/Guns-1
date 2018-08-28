/**
 * 餐饮商管理管理初始化
 */
var StoreBase = {
    id: "StoreBaseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StoreBase.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '店铺id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '店铺类型', field: 'storeTypeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '店铺门头名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '生产经营地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'contact', visible: true, align: 'center', valign: 'middle'},
            {title: '店铺电话', field: 'phone', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
StoreBase.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        StoreBase.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加餐饮商管理
 */
StoreBase.openAddStoreBase = function () {
    var index = layer.open({
        type: 2,
        title: '添加餐饮商管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/storeBase/storeBase_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看餐饮商管理详情
 */
StoreBase.openStoreBaseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '餐饮商管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/storeBase/storeBase_update/' + StoreBase.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除餐饮商管理
 */
StoreBase.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/storeBase/delete", function (data) {
            Feng.success("删除成功!");
            StoreBase.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("storeBaseId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询餐饮商管理列表
 */
StoreBase.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    StoreBase.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = StoreBase.initColumn();
    var table = new BSTable(StoreBase.id, "/storeBase/list", defaultColunms);
    table.setPaginationType("client");
    StoreBase.table = table.init();
});
