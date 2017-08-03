var thirdStep = {
    init: function () {
        jQuery(document).ready(function ($) {
            $("#password").blur(function () {
                thirdStep.checkPassword();
            });
            $("#repassword").blur(function () {
                thirdStep.checkRepassword();
            });
        });
    },
    checkPassword: function () {
        var pwd = $.trim($("#password").val());
        if (pwd.length == 0) {
            thirdStep.checkFaile('新登录密码不能为空');
            return false;
        }
        thirdStep.checkPass();
    },
    checkRepassword:function(){
        var repwd = $.trim($("#repassword").val());
        if (repwd.length == 0) {
            thirdStep.checkFaile('确认新密码不能为空');
            return false;
        }
        var pwd = $.trim($("#password").val());
        if (repwd != pwd) {
            thirdStep.checkFaile('两次密码输入不一致');
            return false;
        }
        thirdStep.checkPass();
    },
    checkFaile: function (msg) {
        $("#errorMsg").text(msg);
        $(".msgDiv").show();
    },
    checkPass: function () {
        $("#errorMsg").text('');
        $(".msgDiv").hide();
    }
};

thirdStep.init();