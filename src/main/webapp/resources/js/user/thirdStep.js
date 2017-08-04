var thirdStep = {
    init: function () {
        jQuery(document).ready(function ($) {
            // 设置header标题
            $("#title").html("找回密码");
            $("#password").blur(function () {
                thirdStep.checkPassword();
            });
            $("#repassword").blur(function () {
                thirdStep.checkRepassword();
            });
            $("#nextStep").click(function(){
                thirdStep.nextStep();
            });
        });
    },
    nextStep:function() {
        var passwordFlag = thirdStep.checkPassword();
        var repasswordFlag = thirdStep.checkRepassword();
        if (passwordFlag && repasswordFlag) {
            document.fm.action = "fourthStep";
            document.fm.method = "post";
            document.fm.submit();
        }
    },
    checkPassword: function () {
        var pwd = $.trim($("#password").val());
        if (pwd.length == 0) {
            thirdStep.checkFaile('新登录密码不能为空');
            return false;
        }
        if (pwd.length < 6 || pwd.length > 20) {
            thirdStep.checkFaile("长度只能在6-20个字符之间");
            return false;
        }
        var regex = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,20}$/;
        if (!regex.test(pwd)) {
            thirdStep.checkFaile("有被盗风险, 建议使用字母, 数字和符号两种及以上组合");
            return false;
        }
        thirdStep.checkPass();
        return true;
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
        return true;
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