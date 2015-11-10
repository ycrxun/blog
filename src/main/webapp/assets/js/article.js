/**
 * article js
 */

//article object
var article = function () {
}

article.initTable = function () {
    console.debug("init table ...");
    //get request
    $.getJSON('/admin/article/list/1', null, function (result) {
        console.debug(result);
        laypage({
            cont: $("#page"),
            pages: result.data.totalPage,
            curr: result.data.pageNumber,
            skip: false,
            jump: function (e) {
                $.getJSON("/admin/article/list/" + e.curr, null, function (result) {
                    console.debug(result);
                    e.pages = e.last = result.data.totalPage;

                    $("#pageView").html("当前为第" + result.data.pageNumber + "页,共" + result.data.totalPage + "页");

                    var dataTableTbody = $("#article_table tbody");
                    dataTableTbody.empty();
                    $(result.data.list).each(function () {
                        var tr = '<tr>' +
                            '<td>' + this.id + '</td>' +
                            '<td><a href="/admin/article/detail/' + this.id + '">' + this.title + '</a></td>' +
                            '<td>' + this.article_type + '</td>' +
                            '<td class="am-hide-sm-only">' + new Date(this.create_time * 1000).format("yyyy-MM-dd hh:mm:ss") + '</td>' +
                            '<td class="am-hide-sm-only">' + new Date(this.update_time * 1000).format("yyyy-MM-dd hh:mm:ss") + '</td>' +
                            '<td>' +
                            '<div class="am-btn-toolbar">' +
                            '<div class="am-btn-group am-btn-group-xs">' +
                            '<a href="/admin/article/detail/' + this.id + '" class="am-btn am-btn-default am-btn-xs am-text-secondary">' +
                            '<span class="am-icon-pencil-square-o"></span> 编辑 </a>' +
                            '<a href="/admin/article/delete/' + this.id + '" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">' +
                            '<span class="am-icon-trash-o"></span> 删除 </a>' +
                            '</div>' +
                            '</div>' +
                            '</td>' +
                            '</tr>';
                        dataTableTbody.append(tr);
                    });
                });
            }
        });
    });
}

//document load finished
$(document).ready(function () {
    console.debug("document load finished...");
    article.initTable();
});