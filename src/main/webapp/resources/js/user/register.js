var usernameFlag = false;
var passwordFlag = false;
var repasswordFlag = false;
var phoneFlag = false;
var verifyCodeFlag = false;
var phoneCodeFlag = false;
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

    $("#username").focus(function () {
        var id = $(this).attr("id");
        var msg = '支持中文、字母、数字、“-”“_”的组合，4-20个字符';
        showMessage(id, msg);
    });

    $("#password").focus(function () {
        var id = $(this).attr("id");
        var msg = '建议使用字母、数字和符号两种及以上的组合，6-20个字符';
        showMessage(id, msg);
    });

    $("#repassword").focus(function () {
        var id = $(this).attr("id");
        var msg = '请再次输入密码';
        showMessage(id, msg);
    });

    $("#phone").focus(function () {
        var id = $(this).attr("id");
        var msg = '完成验证后,你可以用该手机登录和找回密码';
        showMessage(id, msg);
    });

    $("#checkCode").focus(function () {
        var id = $(this).attr("id");
        var msg = '看不清？点击图片更换验证码';
        showMessage(id, msg);
    });

    $("#phoneCode").focus(function () {
        var id = $(this).attr("id");
        var msg = '完成验证后,你可以用该手机登录和找回密码';
        showMessage(id, msg);
    });

    // 绑定文本框失去焦点事件
    // $(".registerText").blur(function () {
    //     var id = $(this).attr("id");
    //     var roleId = id + "Role";
    //     $("#" + roleId).parent().css("visibility", "hidden");
    // });

    // 校验用户名是否存在
    $("#username").blur(function() {
        checkUsername($(this));
    });

    // 校验密码是否符合规则
    $("#password").blur(function() {
        checkPassword($(this));
    });

    // 校验确认密码是否符合规则
    $("#repassword").blur(function() {
        checkRepassword($(this));
    });

    // 校验手机号是否存在
    $("#phone").blur(function() {
        checkPhoneNum($(this));
    });

    // // 校验验证码
    // $("#checkCode").blur(function() {
    //     verifyCheckCode($(this));
    // });

    // 校验手机验证码
    $("#phoneCode").blur(function() {
        checkPhoneCode($(this));
    });

    // 获取手机验证码
    $("#getPhoneCode").click(function () {
        getPhoneCheckCode();
    });

    // 用户注册
    $("#register").click(function () {
        var flag = checkForm();
        if (flag) {
            var phone = $.trim($("#phone").val());
            var username = $.trim($("#username").val());
            var password = $.trim($("#password").val());
            $.ajax({
                url : "userInfo/saveUserInfo",
                data : {
                    "username" : username,
                    "password" : password,
                    "userPhone" : phone
                },
                async : false,
                success : function(result) {
                    if ('0000' == result.code && result.data) {
                        registerSuccess(phone);
                    } else {
                        registerFaile(phone);
                    }
                    resetValue();
                }
            });
        }
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
    var phoneCodeObj = $("#phoneCode");
    checkUsername(usernameObj);
    if (!usernameFlag) {
        return usernameFlag;
    }
    checkPass(usernameObj);
    checkPassword(pwdObj);
    if (!passwordFlag) {
        return passwordFlag;
    }
    checkPass(pwdObj);
    checkRepassword(repwdObj);
    if (!repasswordFlag) {
        return repasswordFlag;
    }
    checkPass(repwdObj);
    checkPhoneNum(phoneObj);
    if (!phoneFlag) {
        return phoneFlag;
    }
    checkPass(phoneObj);
    // verifyCheckCode(checkCodeObj);
    // if (!verifyCodeFlag) {
    //     return verifyCodeFlag;
    // }
    // checkPass(checkCodeObj.parent());
    checkPhoneCode(phoneCodeObj);
    if (!phoneCodeFlag) {
        return phoneCodeFlag;
    }
    checkPass(phoneCodeObj.parent());
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
function checkUsername(usernameObj) {
    var username = $.trim(usernameObj.val());
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
    var regex = /^[\u4e00-\u9fa5_-]{4,20}$|^[\dA-Za-z_\-]{4,20}$/;
    if(!regex.test(username)){
        checkFaile(usernameObj, "格式错误，仅支持中文、字母、数字、“-”“_”的组合");
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

// 校验密码是否符合规则
function checkPassword(pwdObj) {
    var pwd = $.trim(pwdObj.val());
    if (pwd.length == 0) {
        checkFaile(pwdObj, "请输入密码");
        return false;
    }
    if (pwd.length < 6 || pwd.length > 20) {
        checkFaile(pwdObj, "长度只能在6-20个字符之间");
        return false;
    }
    var regex = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,20}$/;
    if (!regex.test(pwd)) {
        checkFaile(pwdObj, "有被盗风险, 建议使用字母, 数字和符号两种及以上组合");
        passwordFlag = false;
        return false;
    }
    checkPass(pwdObj);
    passwordFlag = true;
}

// 校验确认密码是否符合规则
function checkRepassword(repwdObj) {
    var repwd = $.trim(repwdObj.val());
    var pwd = $.trim($("#password").val());
    if (repwd.length == 0) {
        checkFaile(repwdObj, "请输入确认密码");
        return false;
    }
    if (pwd != repwd) {
        checkFaile(repwdObj, "两次密码输入不一致");
        return false;
    }
    checkPass(repwdObj);
    repasswordFlag = true;
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
            if ('0000' == result.code) {
                var data = result.data;
                if (data) {
                    checkPass(checkCodeObj.parent());
                    verifyCodeFlag = true;
                } else {
                    checkFaile(checkCodeObj.parent(), "验证码错误");
                    verifyCodeFlag = false;
                }
            } else {
                checkFaile(checkCodeObj.parent(), result.msg);
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
            if (response.code == '0000') {
                var data = response.data;
                if (data) {
                    // 验证码发送成功
                    $("#getPhoneCode").attr("disabled", "disabled");
                    $("#phoneCodeRole").text("短信验证码已发送，请注意查收");
                    $("#changeCode").find("img").attr("src", "userInfo/getCaptchaImage?type=2&random=" + Math.random());
                    $("#phoneCodeRole").show();
                    phoneCodeCountDown();
                } else {
                    checkFaile($("#phoneCode").parent(), response.msg);
                }
            } else {
                checkFaile($("#phoneCode").parent(), response.msg);
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

// 校验手机验证码
function checkPhoneCode(obj) {
    var code = $.trim(obj.val());
    var phoneNum = $.trim($("#phone").val());
    if (code.length == 0) {
        checkFaile(obj.parent(), "请输入手机验证码");
        phoneCodeFlag = false;
        return false;
    }
    $.ajax({
        url : "userInfo/checkPhoneCode",
        type : "post",
        data : {
            "phoneCode" : code,
            "phoneNum" : phoneNum,
            "checkType" : 1
        },
        async : false,
        success : function(response) {
            console.log(response);
            var code = response.code;
            if ('0000' == code) {
                var result = response.data;
                if (result) {
                    phoneCodeFlag = true;
                    checkPass(obj.parent());
                } else {
                    phoneCodeFlag = false;
                    checkFaile(obj.parent(), "验证码错误");
                }
            } else {
                checkFaile(obj.parent(), response.msg);
            }
        }
    });
}

// 显示提示信息
function showMessage(id, msg) {
    var roleId = id + "Role";
    $("#" + roleId).parent().css("visibility", "visible");
    $("#" + roleId).html(msg);
    $("#" + roleId).css("background-image", "url(resources/images/user/warning_16-2.png)");
    $("#" + id).removeClass("redBorder");
}

// 重置表单
function resetValue() {
    $("#username").val('');
    $("#password").val('');
    $("#repassword").val('');
    $("#phone").val('');
    $("#checkCode").val('');
    $("#phoneCode").val('');
}

//全局弹出消息提示
function alertMessage(){
    $('#alertMessage').modal({
        backdrop:true,
        keyboard:true,
        show:true
    });
}

// 注册成功消息提示
function registerSuccess(phone) {
    var html = "<div><img src='resources/images/user/true.png'/>&nbsp;&nbsp;";
    html += "<font color='#7ABD54' size='4'>恭喜，</font>";
    html += "<font color='red' size='5'>" + phone + "</font>";
    html += "<font color='#7ABD54' size='4'>注册成功，快去<a href='login.html'>登录</a>吧</font>";
    html += "</div>";
    $('#myModal').find(".modal-body").html(html);
    $('#myModal').modal();
}

// 注册失败消息提示
function registerFaile(phone) {
    var html = "<div><img src='resources/images/user/error.png'/>&nbsp;&nbsp;";
    html += "<font color='red' size='4'>抱歉，</font>";
    html += "<font color='#7ABD54' size='5'>" + phone + "</font>";
    html += "<font color='red' size='4'>注册失败，请稍后重试</font>";
    html += "</div>";
    $('#myModal').find(".modal-body").html(html);
    $('#myModal').modal();
}