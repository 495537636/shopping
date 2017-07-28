$(function () {
    // 设置header标题
    $("#title").html("找回密码");

});

var app = new Vue({
    //绑定 DOM 元素
    el: '#app',
    data: {
        user: {
            loginName: '',
            checkCode: ''
        },
        loginNameFlag: false,
        checkCodeFlag: false
    },
    // 初始化调用查询方法
    mounted: function () {
    },
    methods: {
        checkInputValue: function () {
            this.checkLoginName();
            if(!this.loginNameFlag){
                return false;
            }
            alert(1111111);
        },
        checkLoginName: function () {
            if (this.user.loginName.length == 0) {
                this.shakeText($("#loginName"), '请输入用户名/手机号');
                this.loginNameFlag = false;
                return false;
            }
            this.$http.get('userInfo/checkLoginNameExists', {"loginName" : this.user.loginName}).then(function (response) {
                if (response.data == null) {
                    this.loginNameFlag = false;
                    this.shakeText($("#loginName"), '账户名不存在');
                } else {
                    this.loginNameFlag = true;
                }
            }, function (response) {
                console.log(response);
            });
        },
        verifyCheckCode: function () {
            if (this.user.checkCode.length == 0) {
                this.shakeText($("#checkCode"), '请输入验证码');
                return false;
            }
        },
        shakeText: function (obj, msg) {
            obj.css("border", "1px solid red");
            $("#errorMsg").text(msg).parent().show();
            obj.animate({marginLeft: "20px"}, 100)
                .animate({marginLeft: "-20px"}, 100)
                .animate({marginLeft: "20px"}, 100)
                .animate({marginLeft: "0px"}, 100);
        }
    }
});

// // 校验输入内容
// function checkInputValue() {
//     var loginNameObj = $("#loginName");
//     var checkCodeObj = $("#checkCode");
//     var checkCode = $.trim(checkCodeObj.val());
//     var loginNameFlag = checkLoginName(loginNameObj);
//     if (!loginNameFlag) {
//         return false;
//     }
//     alert(111111111111);
//     return true;
// }
//
// // 校验账户名
// function checkLoginName(obj) {
//     var loginName = $.trim(obj.val());
//     if (loginName.length == 0) {
//         shakeText(obj, '请输入用户名/手机号');
//         return false;
//     }
//     $.ajax({
//         url : "userInfo/checkLoginNameExists",
//         data : {
//             "username" : loginName
//         },
//         type : "post",
//         async : false,
//         success : function (data) {
//             var result = data.data;
//             if (result == null) {
//                 shakeText(obj, "帐号不存在");
//                 return false;
//             } else {
//                 return true;
//             }
//         }
//     });
//     return true;
// }
//
// // 文本框抖动方法
// function shakeText(obj, msg) {
//     obj.css("border", "1px solid red");
//     $("#errorMsg").text(msg).parent().show();
//     obj.animate({marginLeft: "20px"}, 100)
//         .animate({marginLeft: "-20px"}, 100)
//         .animate({marginLeft: "20px"}, 100)
//         .animate({marginLeft: "0px"}, 100);
// }