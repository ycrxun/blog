/**
 * define link
 * Created by soi on 15-11-16.
 */
var link = function () {
};

/**
 * init link table
 */
link.init_link_table = function () {
    console.log("init link tale...");
    $.getJSON("/admin/link/list",
        {
            "pageNow": 1
        },
        function (result) {
            laypage({
                cont: $("#page"),
                curr: result.data.pageNumber,
                pages: result.data.totalPage,
                skip: false,
                jump: function (e) {
                    $.getJSON("/admin/link/list",
                        {
                            "pageNow": e.curr
                        },
                        function (result) {
                            console.log(result);
                            if (result && result.code == 100) {
                                e.pages = e.last = result.data.totalPage;
                                $("#pageView").html("当前为第" + result.data.pageNumber + "页,共" + result.data.totalPage + "页");

                                var link_data_table_body = $("#link_table tbody");
                                //clear all tr
                                link_data_table_body.empty();
                                //each data list
                                $(result.data.list).each(function () {
                                    var link_data_table_body_tr = '<tr>' +
                                        '<td>' + this.id + '</td>' +
                                        '<td><a href="'+this.link+'">' + this.link_name + '</a></td>' +
                                        '<td>' + this.link + '</td>' +
                                        '<td>' + link.link_status(this.status) + '</td>' +
                                        '<td>' + new Date(this.create_time * 1000).format("yyyy-MM-dd hh:mm:ss") + '</td>'+
                                        '<td>' +
                                            '<div class="am-btn-toolbar">' +
                                                '<div class="am-btn-group am-btn-group-xs">' +
                                                    '<a class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="link.update_link(\''+this.id+'\')"><span class="am-icon-pencil-square-o"></span> 编辑' +
                                                    '</a>' +
                                                    '<a class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="link.delete_link(\''+this.id+'\')"><span class="am-icon-trash-o"></span> 删除' +
                                                    '</a>' +
                                                '</div>' +
                                            '</div>' +
                                        '</td>' +
                                    '</tr>';
                                    link_data_table_body.append(link_data_table_body_tr);
                                });
                            }
                        }
                    );
                }
            });
        }
    );
};

/**
 * link show status
 * @param status
 * @returns {*}
 */
link.link_status = function (status) {
    if (status == 1) {
        return "显示";
    } else if (status == 0) {
        return "不显示";
    } else {
        return "";
    }
};

/**
 * add link
 */
link.plus_link = function(){
    console.log("add a link");
    layer.open({
        type: 2,
        title: '新增友链',
        shadeClose: true, //点击遮罩关闭层
        area : ['650px' , '380px'],
        content: '/admin/link/add'
    });
};

link.update_link = function(link_id){
    console.log("update a link by id " + link_id);
    layer.open({
        type: 2,
        title: '新增友链',
        shadeClose: true, //点击遮罩关闭层
        area : ['650px' , '380px'],
        content: '/admin/link/update/'+link_id
    });
};

link.delete_link = function (link_id) {
    //layer.confirm();
};

