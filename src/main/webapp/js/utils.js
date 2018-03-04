function setCookie(name, value) {
    document.cookie = name + "=" + JSON.stringify(value) + ";path=/";
}

function getUser() {
    var userString = getCookie('user');
    if (userString == null) {
        return null;
    }
    var user = $.parseJSON(userString);

    return user;
}

//获取cookie
function getCookie(key) {
    var content = document.cookie;
    var cookies = content.split("; ");
    for (var i = 0; i < cookies.length; i++) {
        var keyvalue = cookies[i];
        var index = keyvalue.indexOf("=");
        var this_key = keyvalue.substring(0, index);
        if (this_key == key) {
            var value = keyvalue.substring(index + 1);
            return value;
        }
    }
    return null;
}

//删除cookie name为cookie名称
function delCookie(name) {
    //alert(getCookie(name));
    var date = new Date();
    date.setTime(date.getTime() - 1);
    var content = getCookie(name);
    if (content != null) {
        document.cookie = name + "=" + content + ";expires=" + date.toGMTString() + ";path=/";
    }
    //alert(getCookie(name));
}

//时间戳返回 2017-03-17 09:48:29
function add0(m) {
    return m < 10 ? '0' + m : m
}
function format(shijianchuo) {
    //shijianchuo是整数，否则要parseInt转换
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth() + 1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
}