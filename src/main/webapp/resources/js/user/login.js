$(function () {
    // 设置header标题
    $("#title").html("欢迎登录");

    // 绑定输入内容改变事件
    $(".loginText").keyup(function () {
        $(this).css("border", "1px solid #BDBDBD");
        var value = $.trim($(this).val());
        if (value.length != 0) {
            $(this).parent().find("a").css("display", "inline");
        } else {
            $(this).parent().find("a").css("display", "none");
        }
    });

    // 绑定清除文本框内容事件
    $(".clearBtn").click(function () {
        $(this).parent().find(".loginText").val('');
        $(this).css("display", "none");
    });

    // 刷新验证码
    $("#changeCode").click(function () {
        $(this).find("img").attr("src", "userInfo/getCaptchaImage?type=1&random="+Math.random());
    });

    // 用户名失去焦点事件
    $("#loginName").blur(function () {
        var showFlag = $("#checkCodeDiv").css("display");
        if (showFlag == 'none') {
            var value = $.trim($(this).val());
            $.ajax({
                url : "userInfo/checkSessionId",
                success: function (data) {
                    var result = data.data;
                    if (result) {
                        showCheckCode();
                    } else {
                        hideCheckCode();
                    }
                }
            });
        }
    });

    // 绑定登录按钮事件
    $("#login").click(function () {
        $(".msgDiv").hide();
        var checkFlag = checkForm();
        if (checkFlag) {
            $("#login").text("正在登录...");
            var loginName = $.trim($("#loginName").val());
            var password = $.trim($("#password").val());
            var checkCode = $.trim($("#checkCode").val());
            $.ajax({
                url: "userInfo/login",
                data: {
                    "loginName": loginName,
                    "password": password,
                    "checkCode" : checkCode,
                    "type" : 1
                },
                type: "post",
                success: function (response) {
                    var code = response.code;
                    if ('0000' == code) {
                        // 登录成功，跳转到首页
                        window.location.href = "index.html";
                    } else {
                        // 登录失败
                        $("#login").text("登录");
                        $(".msgDiv").show();
                        $("#password").val('');
                        $("#errorMsg").html(response.msg);
                        console.log(code);
                        if ('0002' == code) {
                            showCheckCode();
                        }
                    }
                }
            });
        }
    });

});

// 加载登录页面宣传图片
var app = new Vue({
    //绑定 DOM 元素
    el: '#app',
    data: {
        dataList: [],
        imageInfo: {}
    },
    // 初始化调用查询方法
    mounted: function () {
        this.getImageInfoList();
    },
    methods: {
        getImageInfoList: function () {
            this.$http.post('image/queryLoginImageList', this.imageInfo, {emulateJSON: true}).then(function (response) {
                this.dataList = response.data.data;
            }, function (response) {
                console.log(response);
            });
        }
    }
});

function checkForm() {
    var loginName = $.trim($("#loginName").val());
    var password = $.trim($("#password").val());
    var checkCode = $.trim($("#checkCode").val());
    var showFlag = $("#checkCodeDiv").css("display");
    if (loginName.length == 0 && password.length == 0) {
        shakeText($("#loginName"));
        shakeText($("#password"));
        showMessage(3);
        return false;
    }
    if (loginName.length == 0) {
        shakeText($("#loginName"));
        showMessage(1);
        return false;
    }
    if (password.length == 0) {
        shakeText($("#password"));
        showMessage(2);
        return false;
    }
    if (showFlag == 'block') {
        if (checkCode.length == 0) {
            shakeText($("#checkCodeDiv"));
            showMessage(4);
            return false;
        }
    }
    return true;
}

// 文本框抖动方法
function shakeText(obj) {
    $(obj).css("border", "1px solid red");
    $(obj).animate({marginLeft: "20px"}, 100)
        .animate({marginLeft: "-20px"}, 100)
        .animate({marginLeft: "20px"}, 100)
        .animate({marginLeft: "0px"}, 100);
}

// 显示提示消息
function showMessage(flag) {
    $(".msgDiv").show();
    var html = "";
    if (1 == flag) {
        html = "请输入用户名";
    }
    if (2 == flag) {
        html = "请输入密码";
    }
    if (3 == flag) {
        html = "请输入用户名和密码";
    }
    if (4 == flag) {
        html = "请输入验证码"
    }
    $("#errorMsg").html(html);
}

// 显示验证码
function showCheckCode() {
    $("#changeCode").find("img").attr("src", "userInfo/getCaptchaImage?type=1&random="+Math.random());
    $("#checkCodeDiv").show();
}

// 隐藏验证码
function hideCheckCode() {
    $("#checkCodeDiv").hide();
}