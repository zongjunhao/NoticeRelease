var baseurl = "/profession/views/pages";
//var utype = "leader";
//var utype = "administrator";

//var ctype = "account_management";

//更新用户名
$('#welcome-text').html('欢迎您，' + $.cookie('ZUEL_uname'));

//加载侧边栏
switch ($.cookie('utype')) {
    case "administrator":
        //超级管理员侧边栏
        $("#sidebar-holder").load(baseurl + '/sidebar_administrator.html');
        break;
    case "leader":
        //校领导/采招中心领导侧边栏
        $("#sidebar-holder").load(baseurl + '/sidebar_leader.html');
        break;
    case "leader_project":
        //校领导/采招中心领导侧边栏
        $("#sidebar-holder").load(baseurl + '/sidebar_leader.html');
        break;
    case "receiver":
        //采招中心项目受理人侧边栏
        $("#sidebar-holder").load(baseurl + '/sidebar_receiver.html');
        break;
    case "principle":
        //采招中心项目负责人侧边栏
        $("#sidebar-holder").load(baseurl + '/sidebar_principle.html');
        break;
    case "contact":
        //采招中心项目联系人侧边栏
        $("#sidebar-holder").load(baseurl + '/sidebar_contact.html');
        break;
}
//调用加载内容区函数
page($.cookie('ctype'));


//加载页面内容区
function page(type) {
    switch (type) {
        case "user_center":
            //加载个人中心页面
            $("title").html("电子采购系统 - 个人中心");
            $.ajax({
                url: baseurl + '/userCenter.html',
                type: "post",
                success: function (info) {

                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式
                    $("#side-nav li:eq(0) a").removeClass("active");
                    $("#side-nav li:eq(1) a").removeClass("active");
                    $("#side-nav li:eq(2) a").removeClass("active");
                    $("#side-nav li:eq(3) a").removeClass("active");
                    $("#side-nav li:eq(4) a").removeClass("active");
                    $("#side-nav li:eq(5) a").removeClass("active");
                    $("#side-nav li:eq(6) a").removeClass("active");
                    $("#subPages").removeClass("in");
                    $("#subPages2").removeClass("in");
                    $("#side-nav li:eq(0) a").addClass("active");
                    $.cookie("ctype", "user_center");
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });
            break;
        case "account_management":
            //加载查看账号页面
            $("title").html("电子采购系统 - 查看账号");
            $.ajax({
                url: baseurl + '/accountManagement.html',
                type: "post",
                success: function (info) {

                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式
                    $("#side-nav li:eq(0) a").removeClass("active");
                    $("#side-nav li:eq(1) a").removeClass("active");
                    $("#side-nav li:eq(2) a").removeClass("active");
                    $("#side-nav li:eq(3) a").removeClass("active");
                    $("#side-nav li:eq(4) a").removeClass("active");
                    $("#side-nav li:eq(5) a").removeClass("active");
                    $("#side-nav li:eq(6) a").removeClass("active");
                    $("#subPages").removeClass("in");
                    $("#subPages2").removeClass("in");
                    //$("#side-nav li:eq(0) a").addClass("active");
                    $("#side-nav li:eq(1) a:eq(0)").addClass("active");
                    //$("#side-nav li:eq(2) a").addClass("active");   
                    $("#subPages").addClass("in");
                    //$("#subPages2").addClass("in");               
                    $("#subPages .nav li:eq(0) a").addClass("active");
                    //$("#subPages .nav li:eq(1) a").addClass("active");
                    //$("#subPages2 .nav li:eq(0) a").addClass("active");
                    //$("#subPages2 .nav li:eq(1) a").addClass("active");           
                    $.cookie("ctype", "account_management");
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });
            break;
        case "account_audition":
            //加载待审核账号页面
            $("title").html("电子采购系统 - 待审核账号");
            $.ajax({
                url: baseurl + '/accountAudition.html',
                type: "post",
                success: function (info) {

                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式
                    $("#side-nav li:eq(0) a").removeClass("active");
                    $("#side-nav li:eq(1) a").removeClass("active");
                    $("#side-nav li:eq(2) a").removeClass("active");
                    $("#side-nav li:eq(3) a").removeClass("active");
                    $("#side-nav li:eq(4) a").removeClass("active");
                    $("#side-nav li:eq(5) a").removeClass("active");
                    $("#side-nav li:eq(6) a").removeClass("active");
                    $("#subPages").removeClass("in");
                    $("#subPages2").removeClass("in");
                    //$("#side-nav li:eq(0) a").addClass("active");
                    $("#side-nav li:eq(1) a:eq(0)").addClass("active");
                    //$("#side-nav li:eq(2) a").addClass("active");   
                    $("#subPages").addClass("in");
                    //$("#subPages2").addClass("in");               
                    //$("#subPages .nav li:eq(0) a").addClass("active");
                    $("#subPages .nav li:eq(1) a").addClass("active");
                    //$("#subPages2 .nav li:eq(0) a").addClass("active");
                    //$("#subPages2 .nav li:eq(1) a").addClass("active");                    
                    $.cookie("ctype", "account_audition");
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });
            break;
        case "info_management":
            //加载查看信息页面

            $("title").html("电子采购系统 - 查看信息");

            $.ajax({
                url: baseurl + '/infoManagement.html',
                type: "post",
                success: function (info) {
                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式                
                    $("#side-nav li:eq(0) a").removeClass("active");
                    $("#side-nav li:eq(1) a").removeClass("active");
                    $("#side-nav li:eq(2) a").removeClass("active");
                    $("#side-nav li:eq(3) a").removeClass("active");
                    $("#side-nav li:eq(4) a").removeClass("active");
                    $("#side-nav li:eq(5) a").removeClass("active");
                    $("#side-nav li:eq(6) a").removeClass("active");
                    $("#subPages").removeClass("in");
                    $("#subPages2").removeClass("in");
                    //$("#side-nav li:eq(0) a").addClass("active");
                    //$("#side-nav li:eq(1) a").addClass("active");
                    $("#side-nav li:eq(4) a:eq(0)").addClass("active");
                    //$("#subPages").addClass("in");
                    $("#subPages2").addClass("in");
                    //$("#subPages .nav li:eq(0) a").addClass("active");
                    //$("#subPages .nav li:eq(1) a").addClass("active");
                    $("#subPages2 .nav li:eq(0) a").addClass("active");
                    //$("#subPages2 .nav li:eq(1) a").addClass("active");              
                    $.cookie("ctype", "info_management");
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });
            break;
        case "info_update":
            //加载信息发布页面
            $("title").html("电子采购系统 - 信息发布");
            $.ajax({
                url: baseurl + '/infoUpdate.html',
                type: "post",
                success: function (info) {
                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式
                    $("#side-nav li:eq(0) a").removeClass("active");
                    $("#side-nav li:eq(1) a").removeClass("active");
                    $("#side-nav li:eq(2) a").removeClass("active");
                    $("#side-nav li:eq(3) a").removeClass("active");
                    $("#side-nav li:eq(4) a").removeClass("active");
                    $("#side-nav li:eq(5) a").removeClass("active");
                    $("#side-nav li:eq(6) a").removeClass("active");
                    $("#subPages").removeClass("in");
                    $("#subPages2").removeClass("in");
                    //$("#side-nav li:eq(0) a").addClass("active");
                    //$("#side-nav li:eq(1) a").addClass("active");
                    $("#side-nav li:eq(4) a:eq(0)").addClass("active");
                    //$("#subPages").addClass("in");
                    $("#subPages2").addClass("in");
                    //$("#subPages .nav li:eq(0) a").addClass("active");
                    //$("#subPages .nav li:eq(1) a").addClass("active");
                    //$("#subPages2 .nav li:eq(0) a").addClass("active");
                    $("#subPages2 .nav li:eq(1) a").addClass("active");
                    $.cookie("ctype", "info_update");
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });
            break;
        case "project_management":
            //加载查看项目页面
            $("title").html("电子采购系统 - 查看项目");
            $.ajax({
                url: baseurl + '/projectManagement.html',
                type: "post",
                success: function (info) {
                    //alert("project_management ajax success");
                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式
                    $("#side-nav li a").removeClass("active");
                    //$("#side-nav li").eq(1).find("a").eq(0).addClass("active");
                    //$("#side-nav li").eq(1).find("#subPages").addClass("in");
                    $("#side-nav li").eq(1).find("a").eq(2).addClass("active");
                    $.cookie('ctype', 'project_Management');
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });

            //projectDetail(1,1);

            break;
        case "project_apply":
            //加载申请项目页面

            $("title").html("电子采购系统 - 申请项目");
            $.ajax({
                url: baseurl + '/projectApply.html',
                type: "post",
                success: function (info) {
                    $.cookie("ctype", "project_apply");
                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式
                    $("#side-nav li a").removeClass("active");
                    //$("#side-nav li").eq(1).find("a").eq(0).addClass("active");
                    //$("#side-nav li").eq(1).find("#subPages").addClass("in");
                    $("#side-nav li").eq(2)/*.find("a").eq(2)*/.addClass("active");
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });

            break;
        case "project_handling":
            //加载待解决项目页面
            $("title").html("电子采购系统 - 待解决项目");
            $.ajax({
                url: baseurl + '/projectHandling.html',
                type: "post",
                success: function (info) {
                    $.cookie("ctype", "project_handling");
                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式
                    $("#side-nav li a").removeClass("active");
                    //$("#side-nav li").eq(1).find("a").eq(0).addClass("active");
                    //$("#side-nav li").eq(1).find("#subPages").addClass("in");
                    $("#side-nav li").eq(3)/*.find("a").eq(2)*/.addClass("active");
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });
            break;
        case "project_init":
            //加载待受理项目页面
            $("title").html("电子采购系统 - 待受理项目");
            $.ajax({
                url: baseurl + '/project_init.html',
                type: "post",
                success: function (info) {
                    $.cookie("ctype", "project_init");
                    //加载内容区
                    $("#contain-holder").html($(info).filter("#contain-loader").html());
                    //加载js区
                    $("#js-holder").html($(info).filter("#js-loader").html());
                    //控制侧边栏active样式
                    //控制侧边栏active样式
                    $("#side-nav li a").removeClass("active");
                    //$("#side-nav li").eq(1).find("a").eq(0).addClass("active");
                    //$("#side-nav li").eq(1).find("#subPages").addClass("in");
                    $("#side-nav li").eq(2)/*.find("a").eq(2)*/.addClass("active");
                },
                error: function () {
                    layer.msg('请刷新重试');
                }
            });
            break;
        case "project_View":
            //加载待受理项目页面
            projectDetail($.cookie("ZUEL_proid"), $.cookie("ZUEL_oprtid"));
            break;
    }
}

function projectDetail(pid, oId) {
    $.cookie("ZUEL_proid", "");
    $.cookie("ZUEL_proid", pid);
    $.cookie("ZUEL_oprtid", oId);
    $.cookie('ctype', 'project_View');
    //alert(pid);
    //alert($.cookie("ZUEL_proid"));


    $("title").html("电子采购系统 - 项目详情");
    $.ajax({
        url: baseurl + '/projectView.html',
        type: "post",
        success: function (info) {
            $("#contain-holder").html($(info).filter("#contain-loader").html());
            //加载js区
            $("#js-holder").html($(info).filter("#js-loader").html());
        },
        error: function () {
            layer.msg('请刷新重试');
        }
    });
}
function exit() {

    layer.confirm("是否确认退出", {
        btn: ['确认退出', '取消'] //按钮
    },
        function () {
            $.cookie('ZUEL_oprtid', '', { expires: -1 }); // 删除 cookie
            $.cookie('ZUEL_proid', '', { expires: -1 }); // 删除 cookie
            $.cookie('ZUEL_proidsta', '', { expires: -1 }); // 删除 cookie
            $.cookie('ZUEL_prostep', '', { expires: -1 }); // 删除 cookie
            $.cookie('ZUEL_uid', '', { expires: -1 }); // 删除 cookie
            $.cookie('ZUEL_uname', '', { expires: -1 }); // 删除 cookie
            $.cookie('ctype', '', { expires: -1 }); // 删除 cookie
            $.cookie('utype', '', { expires: -1 }); // 删除 cookie
            window.location.href = "/profession/user/logout";
            // $.ajax({
            //     type: "POST",
            //     url: "/profession/user/logout",
            //     datatype: 'json',
            //     async: false,
            //     data: {
            //     }, // 发送数据
            //     error: function (jsonobj) {
            //         layer.msg("请求失败！", {
            //             time: 1000
            //         });
            //     },
            //     success: function (jsonobj) {
            //         //alert(JSON.stringify(jsonobj));
            //         if (jsonobj.resultCode == "50000") {
            //             //alert(jsonobj.resultDesc+"！");
            //             window.location.href = "/profession/views/pages/login.html";
            //         } else {
            //             layer.msg(jsonobj.resultDesc + "！", {
            //                 time: 1000
            //             });
            //         }
            //     },
            // });
        }, function () {

        });
}

//我在这里定义了存储所有流程信息的变量
var ZUEL_proFlowInfo = "";
var ZUEL_proInfo = "";
var uploader_file;
