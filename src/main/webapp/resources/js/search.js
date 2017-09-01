$(function() {

    $("#settleupDiv").mouseover(function() {
        $(this).addClass("hover");
    });

    $("#settleupDiv").mouseout(function() {
        $(this).removeClass("hover");
    });

    // 加载服务地区信息
    loadRegion();

    // 加载分类列表
    loadCategoryList();

});

// 加载当前服务地区信息
function loadRegion(obj) {
    var areaCode = '';
    if (typeof(obj) != 'undefined') {
        areaCode = $(obj).attr("data-id");
    }
    $.ajax({
        url: "../serviceArea/getRegionCode",
        data: {
            "areaCode" : areaCode
        },
        type: "post",
        success: function(response) {
            var area = response.data;
            if (null != area) {
                $("#ttbar-mycity span").attr("data-id", area.areaCode);
                $("#ttbar-mycity span").attr("title", area.areaName);
                $("#ttbar-mycity span").text(area.areaName);
                if (area.areaName.length > 2) {
                    $("#shortcut-2014 #ttbar-mycity .dd-spacer").css("width", "89px");
                } else {
                    $("#shortcut-2014 #ttbar-mycity .dd-spacer").css("width", "77px");
                }
                getServiceAreaList(area.areaCode);
            }
        }
    });
}

// 加载服务地区信息列表
function getServiceAreaList(areaCode) {
    $.ajax({
        url: "../serviceArea/queryList",
        data: {
            "areaCode" : areaCode
        },
        type: "post",
        success: function(response) {
            var areaList = response.data;
            var html = '';
            $.each(areaList, function(index, area) {
                html += "<div class=\"item\">";
                html += "<a href=\"javascript:void(0);\" data-id=\"" + area.areaCode +"\"";
                if (areaCode == area.areaCode) {
                    html += " class=\"selected\"";
                }
                html += " onclick=\"loadRegion(this)\">";
                html += area.areaName;
                html += "</a>";
                html += "</div>";
            });
            $("#regionList").html(html);
            $("#ttbar-mycity").removeClass("hover");
        }
    });
}

//加载分类列表
function loadCategoryList() {
    $.ajax({
        url: "../category/queryList",
        type: "post",
        success: function(response) {
            var categoryList = response.data;
            var html = '';
            var tempGroupId = 0;
            var num = 1;
            $.each(categoryList, function(index, category) {
                if (index == 0) {
                    // 第一次循环
                    html += '<div class="item fore'+num+'" data-index="'+category.groupId+'" clstag="h|keycount|2015|0501a">';
                    html += '<h7>';
                    html += '<a href="" target="_blank">'+category.name+'</a>';
                    tempGroupId = category.groupId;
                    num ++;
                } else {
                    var currentGroupId = category.groupId;
                    if (currentGroupId != tempGroupId) {
                        html += '</h7></div>';
                        if (index != categoryList.length - 1) {
                            html += '<div class="item fore'+num+'" data-index="'+category.groupId+'" clstag="h|keycount|2015|0501a">';
                            html += '<h7>';
                            html += '<a href="" target="_blank">'+category.name+'</a>';
                            num ++;
                        }
                    } else {
                        html += '<span class="cate_menu_line">/</span>';
                        html += '<a href="" target="_blank">'+category.name+'</a>';
                        if (index == categoryList.length - 1) {
                            html += '</h7></div>';
                        }
                    }
                    tempGroupId = category.groupId;
                }
            });
            $("#category").html(html);
        }
    });
}