/**
 * Created by soi on 15-11-17.
 * personal module js
 */

/**
 * define personal class
 */
var personal = function () {
};

/**
 * check personal form
 * @return boolean
 */
personal.check_personal_form = function () {
    if (kit.blank("#account_name")) {
        kit.sad('姓名没有填写');
        $("#account_name").focus();
        return false;
    }
    if (kit.blank("#account_email")) {
        kit.sad('电子邮件没有填写');
        $("#account_email").focus();
        return false;
    }
    return true;
};

/**
 * submit personal form
 */
personal.submit_personal_form = function () {
    $.post('/admin/profile/alter', $("#personal_form").serialize(), function (result) {
        if (result) {
            result = $.parseJSON(result);
            console.log(result);
            if (result.code == 100) {
                layer.msg('操作成功');
                //
                location.reload();
            }
        } else {
            layer.msg('操作失败，请稍后重试!');
        }
    });
};

personal.check_personal_modify_form = function () {
    if (kit.blank("#old_password")) {
        kit.sad('请输入原密码');
        $("#old_password").focus();
        return false;
    }
    if (kit.blank("#new_password")) {
        kit.sad('请输入新密码');
        $("#new_password").focus();
        return false;
    }
    if (kit.blank("#again_password")) {
        $("#again_password").focus();
        layer.msg('请确认新密码');
        return false;
    }

    if (kit.eqn("#new_password", "#again_password")) {
        layer.msg('两次输入不一致');
        return false;
    }
    return true;
};

/**
 * check password
 * @returns {boolean}
 */
personal.check_new_password_again = function () {
    var again_password = $("#again_password");
    var b = true;
    again_password.blur(function () {
        if (again_password.val() != $("#new_password").val()) {
            layer.msg('两次输入密码不一致！');
            b = false;
        }
    });
    return b;
};

/**
 * submit personal modify form
 */
personal.submit_personal_modify_form = function () {
    var modify_form = $("#personal_modify_form");
    modify_form.ajaxSubmit({
        url: '/admin/profile/modify',
        type: 'post',
        dataType: 'json',
        success: function (r) {
            if (r) {
                if (r.code == 100) {
                    layer.msg(r.message + '10秒后自动注销登录', function () {
                        setTimeout('personal.logout()', 10 * 1000);
                    });
                    modify_form[0].reset();
                } else {
                    layer.msg(r.message);
                }
            }
        },
        error: function (r) {
            console.log(r);
            layer.msg('修改失败');
        }
    });
};

personal.logout = function () {
    location.href = '/admin/logout';
};