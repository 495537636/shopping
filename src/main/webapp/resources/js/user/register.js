$(function () {
    // 设置header标题
    $("#title").html("欢迎注册");

    $(".have_account").show();

    // 显示验证码
    $("#changeCode").find("img").attr("src", "userInfo/getCaptchaImage?random=" + Math.random());

    // 刷新验证码
    $("#changeCode").click(function () {
        $(this).find("img").attr("src", "userInfo/getCaptchaImage?random=" + Math.random());
    });

    // 绑定文本框获取焦点事件
    $(".registerText").focus(function () {
        var id = $(this).attr("id");
        var roleId = id + "Role";
        $("#" + roleId).parent().css("visibility", "visible")
    });

    $("#register").click(function () {
        checkForm();
    });

});

function checkForm() {
    var usernameObj = $("#username");
    var passwordObj = $("#password");
    var repasswordObj = $("#repassword");
    var phoneObj = $("#phone");
    var checkCodeObj = $("#checkCode");
    var phoneCodeObj = $("#phoneCode");
    var username = $.trim(usernameObj.val());
    var password = $.trim(passwordObj.val());
    var repassword = $.trim(repasswordObj.val());
    var phone = $.trim(phoneObj.val());
    var checkCode = $.trim(checkCodeObj.val());
    var phoneCode = $.trim(phoneCodeObj.val());
    if (username.length == 0) {
        shakeText(usernameObj);
        showMessage(usernameObj, "用户名不能为空");
    }
}

// 文本框抖动方法
function shakeText(obj) {
    $(obj).css("border", "1px solid red");
    $(obj).animate({marginLeft: "20px"}, 100)
        .animate({marginLeft: "-20px"}, 100)
        .animate({marginLeft: "20px"}, 100)
        .animate({marginLeft: "0px"}, 100);
}

// 显示消息信息提示
function showMessage(obj, msg) {
    $(obj).parent().find(".msgDiv").css("visibility", "visible");
    $(obj).parent().find("span").css("background-image", "url(resources/images/user/error_16.png)");
    $(obj).parent().find("span").text(msg);
}