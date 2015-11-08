var index = function(){};

/**
*load visitor statistical to index page
*/
index.loadVisitor = function (){
    console.info("load visitor stat...");
    $.get("/admin/visitor",{},function(result){
        if(result) {
            result = $.parseJSON(result);
            console.debug(result);
            console.debug(result.code == 100);
            var data = result.data;
            $("#todayVisitor").html('<span class="am-icon-btn am-icon-file-text"></span><br/>今日访客<br/>' + data.today.visitor);
            $("#yesterdayVisitor").html('<span class="am-icon-btn am-icon-briefcase"></span><br/>昨日客数<br/>' + data.yesterday.visitor);
            $("#totalVisitor").html('<span class="am-icon-btn am-icon-recycle"></span><br/>总访访客<br/>' + data.total.visitor);
        }
    });
}

/**
*load hot article
*/
index.loadHot = function(hot){
    var hot_list_ul = $("#hot_list");
    hot_list_ul.empty();
    var hot_list = hot.list;
    console.debug(hot_list);
    //for each
    $(hot_list).each(function(){
        var li = '<li><a href="/admin/article/detail/'+this.article_id+'">'+this.title+'</a></li>';
        hot_list_ul.append(li);
    });
}

/**
*load not reply article
*/
index.loadNotReply = function(not_reply){
    var not_reply_list_ul = $("#not_reply_list");
    not_reply_list_ul.empty();
    $(not_reply.list).each(function(){
        not_reply_list_ul.append('<li><a href="/admin/article/detail/'+this.article_id+'">'+this.title+'</a></li>');
    });
}

/**
*load article statistical to index page
*/
index.loadArticle = function(){
    console.info("load article...");
    $.get("/admin/article/stat",{},function(result){
        if(result) {
            result = $.parseJSON(result);
            console.debug(result);
            if(result.code == 100) {
                var not_reply = result.data.not_reply;
                var hot = result.data.hot;
                //load hot
                index.loadHot(hot);
                //load not_reply
                index.loadNotReply(not_reply);
            }
        }
    });
}

/**
* document load finished
*/
$(document).ready(function(){
    console.info("document load finished...");
    index.loadVisitor();
    index.loadArticle();
});

