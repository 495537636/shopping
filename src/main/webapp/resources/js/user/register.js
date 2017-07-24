var usernameFlag = false;
var phoneFlag = false;
var verifyCodeFlag = false;
var timeCount = 120;

$(function () {
    // 设置header标题
    $("#title").html("欢迎注册");

    $(".have_account").show();

    // 显示验证码
    $("#changeCode").find("img").attr("src", "userInfo/getCaptchaImage?type=2&random=" + Math.random());

    // 刷新验证码
    $("#changeCode").click(function () {
        $(this).find("img").attr("src", "userInfo/getCaptchaImage?type=2&random=" + Math.random());
    });

    // 绑定文本框获取焦点事件
    // $(".registerText").focus(function () {
    //     var id = $(this).attr("id");
    //     var roleId = id + "Role";
    //     $("#" + roleId).parent().css("visibility", "visible");
    // });

    // 绑定文本框失去焦点事件
    // $(".registerText").blur(function () {
    //     var id = $(this).attr("id");
    //     var roleId = id + "Role";
    //     $("#" + roleId).parent().css("visibility", "hidden");
    // });

    // 校验用户名是否存在
    $("#username").blur(function() {
        var username = $.trim($(this).val());
        checkUsername(username);
    });

    // 校验手机号是否存在
    $("#phone").blur(function() {
        checkPhoneNum($(this));
    });

    // 获取手机验证码
    $("#getPhoneCode").click(function () {
        getPhoneCheckCode();
    });

    // 用户注册
    $("#register").click(function () {
        checkForm();
    });

});

var app = new Vue({
    //绑定 DOM 元素
    el: '#app',
    // 初始化调用查询方法
    mounted: function () {
    },
    methods: {
    }
});

function checkForm() {
    var usernameObj = $("#username");
    var pwdObj = $("#password");
    var repwdObj = $("#repassword");
    var phoneObj = $("#phone");
    var checkCodeObj = $("#checkCode");
    var phoneCodeObj = $("#phoneCode");
    var username = $.trim(usernameObj.val());
    var pwd = $.trim(pwdObj.val());
    var repwd = $.trim(repwdObj.val());
    var phone = $.trim(phoneObj.val());
    var checkCode = $.trim(checkCodeObj.val());
    var phoneCode = $.trim(phoneCodeObj.val());
    checkUsername(username);
    if (!usernameFlag) {
        return !usernameFlag;
    }
    checkPass(usernameObj);
    if (pwd.length == 0) {
        checkFaile(pwdObj, "请输入密码");
        return false;
    }
    if (pwd.length < 6 || pwd.length > 20) {
        checkFaile(pwdObj, "长度只能在6-20个字符之间");
        return false;
    }
    checkPass(pwdObj);
    if (repwd.length == 0) {
        checkFaile(repwdObj, "请输入确认密码");
        return false;
    }
    if (pwd != repwd) {
        checkFaile(repwdObj, "两次密码输入不一致");
        return false;
    }
    checkPass(repwdObj);
    checkPhoneNum(phoneObj);
    if (!phoneFlag) {
        return !phoneFlag;
    }
    checkPass(phoneObj);
    verifyCheckCode(checkCodeObj);
    if (!verifyCodeFlag) {
        return !verifyCodeFlag;
    }
    checkPass(checkCodeObj.parent());
    return true;
}

// 验证不通过
function checkFaile(obj, msg) {
    // 添加红色文本边框
    obj.addClass("redBorder");
    // 文本框抖动方法
    // $(obj).animate({marginLeft: "20px"}, 100)
    //     .animate({marginLeft: "-20px"}, 100)
    //     .animate({marginLeft: "20px"}, 100)
    //     .animate({marginLeft: "0px"}, 100);
    // 显示消息提示
    $(obj).parent().find(".msgDiv").css("visibility", "visible");
    $(obj).parent().find("span").css("background-image", "url(resources/images/user/error_16.png)");
    $(obj).parent().find("span").text(msg);
}

// 验证通过
function checkPass(obj) {
    obj.parent().find(".msgDiv").css("visibility", "hidden");
    obj.removeClass("redBorder");
}

// 校验用户名是否存在
function checkUsername(username) {
    var usernameObj = $("#username");
    if (username.length == 0) {
        checkFaile(usernameObj, "请输入用户名");
        usernameFlag = false;
        return false;
    }
    if (username.length < 4 || username.length > 20) {
        checkFaile(usernameObj, "长度只能在4-20个字符之间");
        usernameFlag = false;
        return false;
    }
    var regex = /^[a-zA-Z]|[0-9a-zA-Z]$/;
    if(!regex.test(username)){
        checkFaile(usernameObj, "格式错误，仅支持字母、数字的组合");
        usernameFlag = false;
        return false;
    }
    $.ajax({
        url : "userInfo/checkUsername",
        data : {
            "username" : username
        },
        type : "post",
        success : function (data) {
            var result = data.data;
            if (result) {
                checkFaile(usernameObj, "该用户名已被注册");
                usernameFlag = false;
            } else {
                checkPass(usernameObj);
                usernameFlag = true;
            }
        }
    });
}

// 校验手机号是否已经绑定用户
function checkPhoneNum(phoneObj) {
    var phoneNum = $.trim(phoneObj.val());
    if (phoneNum.length == 0) {
        checkFaile(phoneObj, "请输入手机号");
        phoneFlag = false;
        return false;
    }
    var regex = /^1[3,5,4,8,7,9]\d{9}$/;
    if(!regex.test(phoneNum)){
        checkFaile(phoneObj, "手机号格式有误");
        phoneFlag = false;
        return false;
    }
    $.ajax({
        url : "userInfo/checkPhoneNum",
        data : {
            "phone" : phoneNum
        },
        type : "post",
        success : function (data) {
            var result = data.data;
            if (result) {
                checkFaile(phoneObj, "该手机号已被注册");
                phoneFlag = false;
            } else {
                checkPass(phoneObj);
                phoneFlag = true;
            }
        }
    });
}

// 校验验证码
function verifyCheckCode(checkCodeObj) {
    var code = $.trim(checkCodeObj.val());
    if (code.length == 0) {
        checkFaile(checkCodeObj.parent(), "请输入验证码");
        verifyCodeFlag = false;
        return false;
    }
    if (code.length != 4) {
        checkFaile(checkCodeObj.parent(), "验证码错误");
        verifyCodeFlag = false;
        return false;
    }
    $.ajax({
        url : "userInfo/verifyCheckCode",
        data : {
            "checkCode" : code
        },
        async : false,
        type : "post",
        success : function(result) {
            var data = result.data;
            if (data) {
                checkPass(checkCodeObj.parent());
                verifyCodeFlag = true;
            } else {
                checkFaile(checkCodeObj.parent(), "验证码错误");
                verifyCodeFlag = false;
            }
        }
    });
}

// 获取手机验证码
function getPhoneCheckCode() {
    var phoneObj = $("#phone");
    checkPhoneNum(phoneObj);
    if(!phoneFlag) {
        return !phoneFlag;
    }
    var codeObj = $("#checkCode");
    verifyCheckCode(codeObj);
    if(!verifyCodeFlag) {
        return !verifyCodeFlag;
    }
    $.ajax({
        url : "userInfo/sendPhoneCode",
        type : "post",
        async : false,
        data : {
            "phone" : $.trim(phoneObj.val()),
            "sendType" : 1  // 1=用户注册手机验证码，2=用户找回密码
        },
        success : function(response) {
            var data = response.data;
            if (data) {
                // 验证码发送成功
                $("#getPhoneCode").attr("disabled", "disabled");
                phoneCodeCountDown();
            }
        }
    });
}

// 发送短信倒计时
function phoneCodeCountDown() {
    var restTime = timeCount--;
    var text = "";
    if (restTime <= 0) {
        text = "获取验证码";
        $("#getPhoneCode").removeAttr("disabled");
        $("#getPhoneCode").val(text);
        timeCount = 120;
    } else {
        text = restTime + "s后重新获取";
        $("#getPhoneCode").val(text);
        setTimeout('phoneCodeCountDown('+timeCount+')',1000);
    }
}