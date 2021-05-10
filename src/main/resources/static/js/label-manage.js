//更新用户名
$('#welcome-text').html('欢迎您，' + $.cookie('userName'));
showLabels()

function showLabels() {
    $.ajax({
        type: "POST",
        url: "/notice/getLabels",
        success: function (json) {
            let label = "";
            if (json.code === "2006") {
                for (let i = 0; i < json.data.length; i++) {
                    label += "<button type=\"button\" class=\"btn btn-default\">" + json.data[i].labelName + "</button>"
                }
                $("#labels").append(label)
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

function addLabel(){
    $("#change-phone-div").css("visibility", "visible")
    layer.open({
        type: 1,
        title: '添加标签',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['330px', '200px'],
        content: $("#change-phone-div"),
        btn: ['添加'],
        yes: function (index, layero) {
            console.log($("#label-add").val())
            console.log("yes")
            if ($("#label-add").val() === "") {
                layer.msg('请输入标签名', {
                    time: 1000
                })
            } else {
                postAddLabel($("#label-add").val())
            }
        }
    })
}

function postAddLabel(labelName){
    $.ajax({
        type: "POST",
        url: "/notice/addLabel",
        data:{
            labelName: labelName
        },
        success: function (json) {
            let label = "";
            if (json.code === "2002") {
                layer.msg( "添加成功", {
                    time: 1000
                });
                setTimeout(function () {
                    location.reload()
                }, 1000)
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