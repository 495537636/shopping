var loginNameFlag = false, checkCodeFlag = false;

$(function () {
    // 设置header标题
    $("#title").html("找回密码");

    // 显示验证码
    $("#changeCode").find("img").attr("src", "userInfo/getCaptchaImage?type=3&random=" + Math.random());

    // 下一步
    $("#nextStep").click(function () {
        var flag = checkInputValue();
        if (flag) {
            document.fm.action = "userInfo/secondStep";
            document.fm.method = "post";
            document.fm.submit();
        }
    });

    // 刷新验证码
    $("#changeCode").click(function () {
        $(this).find("img").attr("src", "userInfo/getCaptchaImage?type=3&random=" + Math.random());
    });
});

// 校验输入内容
function checkInputValue() {
    checkLoginName();
    if (!loginNameFlag) {
        return false;
    }
    verifyCheckCode();
    if (!checkCodeFlag) {
        return false;
    }
    return true;
}

// 校验账户名
function checkLoginName() {
    var obj = $("#loginName");
    var loginName = $.trim(obj.val());
    if (loginName.length == 0) {
        checkFaile(obj, '请输入用户名/手机号');
        loginNameFlag = false;
        return false;
    }
    $.ajax({
        url: "userInfo/checkLoginNameExists",
        data: {
            "loginName": loginName
        },
        type: "post",
        async: false,
        success: function (response) {
            if (response.data == null) {
                loginNameFlag = false;
                checkFaile($("#loginName"), '账户名不存在');
            } else {
                loginNameFlag = true;
                checkPass($("#loginName"));
            }
        }
    });
    return true;
}

// 校验验证码
function verifyCheckCode() {
    var obj = $("#checkCode");
    var checkCode = $.trim(obj.val());
    if (checkCode.length == 0) {
        checkFaile(obj.parent(), '请输入验证码');
        checkCodeFlag = false;
        return false;
    }
    $.ajax({
        url : "userInfo/verifyCheckCode",
        data : {
            "checkCode" : checkCode,
            "type" : 3
        },
        type : "post",
        async : false,
        success : function(response) {
            if (response.data) {
                checkCodeFlag = true;
                checkPass($("#checkCode"));
            } else {
                checkCodeFlag = false;
                checkFaile($("#checkCode"), '验证码错误');
            }
        }
    });
}

// 校验未通过
function checkFaile(obj, msg) {
    obj.css("border", "1px solid red");
    $("#errorMsg").text(msg).parent().show();
}

// 校验通过
function checkPass(obj) {
    obj.css("border", "1px solid #ccc");
    $("#errorMsg").text('').parent().hide();
}