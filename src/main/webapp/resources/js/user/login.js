$(function () {
    // 设置header标题
    $("#title").html("欢迎登录");

    // 绑定输入内容改变事件
    $(".loginText").keydown(function () {
        $(this).css("border", "1px solid #BDBDBD");
        $(this).parent().find("a").css("display", "inline");
    });

    // 绑定清除文本框内容事件
    $(".clearBtn").click(function () {
        $(this).parent().find(".loginText").val('');
        $(this).css("display", "none");
    });

    // 绑定登录按钮事件
    $("#login").click(function () {
        $(".msgDiv").hide();
        var checkFlag = checkForm();
        if (checkFlag) {
            $("#login").text("正在登录...");
            var username = $.trim($("#username").val());
            var password = $.trim($("#password").val());
            $.ajax({
                url: "userInfo/login",
                data: {
                    "username": username,
                    "password": password
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
                        $("#errorMsg").html(response.msg);
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
                console.log(response)
            });
        }
    }
});

function checkForm() {
    var username = $.trim($("#username").val());
    var password = $.trim($("#password").val());
    if (username.length == 0 && password.length == 0) {
        shakeText($("#username"));
        shakeText($("#password"));
        showMessage(3);
        return false;
    }
    if (username.length == 0) {
        shakeText($("#username"));
        showMessage(1);
        return false;
    }
    if (password.length == 0) {
        shakeText($("#password"));
        showMessage(2);
        return false;
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
    $("#errorMsg").html(html);
}