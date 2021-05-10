//更新用户名
$('#welcome-text').html('欢迎您，' + $.cookie('userName'));
showUsers()


function showUsers() {
    $.ajax({
        type: "POST",
        url: "/index/getUsersInCharge",
        data: {
            userId: $.cookie('userId')
        },
        success: function (json) {
            let users = "";
            if (json.code === "2006") {
                for (var i = 0; i < json.data.length; i++) {
                    $("#unit-holder").append("<option style='font-size: 14px;' value='" + json.data[i].userId + "'>" + json.data[i].userName + "</option>");
                    $("#notice-releasers").append("<option style='font-size: 14px;' value='" + json.data[i].userId + "'>" + json.data[i].userName + "</option>");
                    users += "<label><input type='checkbox' name='check' id='" + json.data[i].userId + "'/>" + json.data[i].userName + "</label>&nbsp;&nbsp;"
                    $('.selectpicker').selectpicker('refresh');
                    $('.selectpicker').selectpicker('show');
                }
                $("#users").append(users)
            } else {
                layer.msg(json.desc + "!", {
                    time: 1000
                });
            }
        },
        error: function (jqXHR) {
            layer.msg('请求失败！', {
                time: 1000
            });
        }
    })
}

function test(element) {
    switch (element) {
        case "name": {
            if ($("#name").val() == null || $("#name").val() === "") {
                $("#msgInfo_title").text("单位名称不能为空！")
                return false;
            } else {
                $("#msgInfo_title").text("")
                return true;
            }
        }
    }
}

function getUsers() {
    var l_id = "["
    var length = $("input:checkbox[name='check']:checked").length;
    if (length !== 0) {
        $("input:checkbox[name='check']:checked").each(function (i, index) {
            //操作
            console.log($(this).attr("id"))
            if (i < length - 1) {
                l_id += $(this).attr("id") + ","
            } else {
                l_id += $(this).attr("id") + "]"
            }
        });
    }
    return $.parseJSON(l_id)
}

function submit(){
    if (test('name')) {
        let index = layer.load(1, {
            shade: [0.5, '#fff'], //0.1透明度的白色背景
        });
        let data = {
            unitName: $("#name").val(),
            unitDescribe: $("#describe").val(),
            unitHolderId: $("#unit-holder").val(),
            noticeReleaserIds: $("#notice-releasers").val().join(','),
            userIds: getUsers().join(',')
        };
        console.log(data)
        //String unitName, String unitDescribe, String unitHolderId, String noticeReleaserIds, String userIds
        $.ajax({
            url: "/index/createUnit",
            data: data,
            async: true,
            type: "POST",
            // contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (re) {
                if (re.code === "2002") {
                    layer.close(index)
                    layer.msg('单位创建成功', {
                        time: 2000
                    });
                    setTimeout(function (){
                        location.href = "/user.html"
                    }, 1000)
                } else {
                    layer.msg(re.desc, {
                        time: 1000
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg('操作失败 ', {
                    time: 1000
                });
            }
        });
    }
}