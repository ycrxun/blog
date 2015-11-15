/**
 * Created by soi on 15-11-11.
 */
/**
 * for all client page
 */
/**
 *################################################################
 * layout_right
 *################################################################
 */
/**
 *define layout_right
 */
var layout_right = function () {
};

/**
 * load last article list
 */
layout_right.load_last_article_list = function () {
    //find last_article_list
    var last_article_list = $("#last_article_list");
    //empty last_article_list
    last_article_list.empty();
    //add title
    last_article_list.append('<div class="collapsible-header indigo-text text-darken-4"> 最新文章&nbsp;|&nbsp;Latest</div>');
    //load data
    layout_right.load_data_list('/article/last', last_article_list);
};

/**
 * load hot article list
 */
layout_right.load_hot_article_list = function () {
    //find hot article list
    var hot_article_list = $("#hot_article_list");
    //empty hot_article_list
    hot_article_list.empty();
    //add title
    hot_article_list.append('<div class="collapsible-header indigo-text text-darken-4"> 最热文章&nbsp;|&nbsp;Hot</div>');
    //load data
    layout_right.load_data_list('/article/hot', hot_article_list);
};

/**
 * load data by ajax url and element
 * @param ajax_url request ajax url
 * @param el element
 */
layout_right.load_data_list = function (ajax_url, el) {
    $.getJSON(ajax_url, null, function (result) {
        if (result && result.code == 100) {
            $(result.data).each(function () {
                var a_line = '<a href="/article/detail/' + this.id + '.html" class="collection-item waves-effect waves-light">' + this.title + '</a>';
                el.append(a_line);
            });
        }
    });
};

/**
 *################################################################
 *detail
 *################################################################
 /**
 * define detail for detail page
 */
var detail = function () {
};

/**
 * detail page init time-format time
 */
detail.init_time = function () {
    var fmt = 'yyyy-MM-dd hh:mm:ss';
    var date = $(".c_time").val();
    console.log(date);
    var new_date = new Date(date * 1000).format(fmt);
    console.log(new_date);
    $("#c_time_show").html("发布于：" + new_date).removeClass("hiddendiv");
};

/**
 * detail page init article content
 */
detail.init_article_content = function () {
    $("#article_content").html(marked($("#article_content_area").val())).removeClass("hiddendiv");
};

/**
 * preview reply content
 */
detail.preview = function () {
    $("#preview_content").html(marked($("#reply_content").val())).removeClass("hiddendiv");
};

/**
 * reply request
 */
detail.reply = function () {
    console.log("in reply...");
    if (detail.check_reply_form()) {
        console.log("form can submit...");
        var reply_form = $("#reply_form");
        $.post("/article/reply/put",
            reply_form.serialize(),
            function (result) {
                reply_form.reset();
                result = $.parseJSON(result);
                if (result && result.code == 100) {
                    console.log(result.message);
                    var data = result.data;
                    console.log(data);
                    var reply_list = $("#reply_list");
                    var div = '<div class="row valign-wrapper">' +
                        '<div class="col s2">' +
                        '<img alt="" src="/assets/images/xn.png" class="circle responsive-img"/>' +
                        '</div>' +
                        '<div class="col s10">' +
                        '<span class="black-text"><small>' + data.reply_name + ' 回复于 ' + new Date().format("yyyy-MM-dd hh:mm:ss") + '</small><br/>' + data.content + '</span>' +
                        '</div>' +
                        '</div>'
                    console.log(div);
                    var li = '<li class="collection-item">' + div + '</li>';
                    console.log(li);
                    reply_list.append(li);
                }
            }
        );
    }
};

/**
 * check reply form not null or blank
 */
detail.check_reply_form = function () {
    if ($.trim($("#reply_name").val()) == "") {
        alert("昵称不能为空");
        return false;
    }
    if ($.trim($("#reply_email").val()) == "") {
        alert("邮箱不能为空");
        return false;
    }
    if ($.trim($("#reply_content").val()) == "") {
        alert("内容不能为空");
        return false;
    }
    return true;
};

/**
 * init reply list
 */
detail.init_reply_list = function () {
    var reply_list = $("#reply_list");
    //$.getJSON("/article/reply");
}
