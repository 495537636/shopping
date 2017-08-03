var secondStep = {
    timeCount : 120,
    phoneCodeFlag : false,
    init:function() {
        jQuery(document).ready(function($){
            $("#getPhoneCode").click(function() {
                secondStep.sendPhoneCode();
            });
            $("#nextStep").click(function(){
                secondStep.nextStep();
            });
        });
    },
    sendPhoneCode:function(){
        var userPhone = $.trim($("#userPhone").val());
        $.ajax({
            url : "sendPhoneCode",
            type : "post",
            async : false,
            data : {
                "phone" : userPhone,
                "sendType" : 2  // 1=用户注册手机验证码，2=用户找回密码
            },
            success : function(response) {
                if (response.code == '0000') {
                    var data = response.data;
                    if (data) {
                        // 验证码发送成功
                        $("#getPhoneCode").attr("disabled", "disabled");
                        $("#errorMsg").text("校验码已发出，请注意查收短信").show();
                        secondStep.phoneCodeCountDown();
                    } else {
                        $("#errorMsg").text(response.msg).show();
                    }
                } else {
                    $("#errorMsg").text(response.msg).show();
                }
            }
        });
    },
    phoneCodeCountDown:function(){
        var restTime = secondStep.timeCount--;
        var text = "";
        if (restTime <= 0) {
            text = "获取手机校验码";
            $("#getPhoneCode").removeAttr("disabled");
            $("#getPhoneCode").val(text);
            secondStep.timeCount = 120;
        } else {
            text = restTime + "s后重新发送";
            $("#getPhoneCode").val(text);
            setTimeout('secondStep.phoneCodeCountDown('+secondStep.timeCount+')',1000);
        }
    },
    nextStep:function() {
        secondStep.checkPhoneCode();
        if (!secondStep.phoneCodeFlag) {
            return false;
        }
        document.fm.action = "thirdStep";
        document.fm.method = "post";
        document.fm.submit();
    },
    checkPhoneCode:function(){
        var phoneCode = $.trim($("#phoneCode").val());
        var userPhone = $.trim($("#userPhone").val());
        if (phoneCode.length == 0) {
            $("#errorMsg").text("校验码不能为空").show();
            return false;
        }
        $.ajax({
            url : "checkPhoneCode",
            type : "post",
            data : {
                "phoneCode" : phoneCode,
                "phoneNum" : userPhone,
                "checkType" : 2
            },
            async : false,
            success : function(response) {
                var code = response.code;
                if ('0000' == code) {
                    var result = response.data;
                    if (result) {
                        secondStep.phoneCodeFlag = true;
                        secondStep.checkPass();
                    } else {
                        secondStep.phoneCodeFlag = false;
                        secondStep.checkFaile("验证码错误");
                    }
                } else {
                    secondStep.checkFaile(response.msg);
                }
            }
        });
    },
    checkPass:function(msg){
        $("#errorMsg").text("").hide();
    },
    checkFaile:function(msg){
        $("#errorMsg").text(msg).hide();
    }
};

secondStep.init();