$(function () {
    // 设置header标题
    $("#title").html("欢迎注册");

    // 绑定文本框获取焦点事件
    $(".registerText").focus(function () {
        var id = $(this).attr("id");
        var roleId = id + "Role";
        console.log($("#" + roleId));
        $("#" + roleId).parent().css("visibility", "visible")
    });

});