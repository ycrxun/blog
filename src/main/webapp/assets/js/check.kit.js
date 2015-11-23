/**
 * @file check.kit.js
 * @description simple check for me
 * @author soi.
 * @blog blog.ronggle.com
 * @time 2015-11-17.
 * @dependence jquery,layer
 * @version 0.0.1
 *
 */
var kit = function () {
};

/**
 * check blank by id
 * @param id
 * @returns {boolean}
 */
kit.blank = function (id) {
    return $.trim($(id).val()) == '';
};

kit.notBlank = function (id) {
    return !kit.blank(id);
};

kit.eq = function(s,d){
    return $(s).val() == $(d).val();
};

kit.eqn = function(s,d){
    return !kit.eq(s,d);
};

/**
 * alert message
 * @param s message
 * @param t type 1-true 2-error 3-confirm 4-lock 5-sad 6-smile 7-info
 */
kit.alert = function (s, t) {
    layer.alert(s, {icon: t})
};

/**
 * alert true
 * @param s message
 */
kit.success = function (s) {
    kit.alert(s, 1);
};

/**
 * alert error
 * @param s message
 */
kit.error = function (s) {
    kit.alert(s,2);
};

/**
 * alert confirm
 * @param s message
 */
kit.confirm = function (s) {
    kit.alert(s,3);
};

/**
 * alert lock
 * @param s message
 */
kit.lock = function (s) {
    kit.alert(s,4);
};

/**
 * alert sad
 * @param s message
 */
kit.sad = function (s) {
    kit.alert(s,5);
};

/**
 * alert smile
 * @param s message
 */
kit.smile = function (s) {
    kit.alert(s,6);
};

/**
 * alert info
 * @param s message
 */
kit.info = function (s) {
    kit.alert(s,7);
};
