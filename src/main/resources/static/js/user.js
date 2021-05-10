$('#welcome-text').html('欢迎您，' + $.cookie('userName'));
showUser();

function showUser() {
    $.ajax({
        type: "POST",
        url: "/index/getUser",
        data: {
            userId: "1"
            // userId: $.cookie('userId')
        },
        success: function (json) {
            if (json.code === "2006") {
                $("#name").val(json.data.userName)
                $("#gender").val(json.data.userGender)
                $("#account").val(json.data.userAccount)
                $("#password").val("************")
                $("#phone").val(json.data.userPhone)
                $("#mail").val(json.data.userEmail)
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

function updateUserInfo(field, value, userId) {
    $.ajax({
        type: "POST",
        url: "/index/updateUserInfo",
        data: {
            field: field,
            value: value,
            userId: userId
        },
        success: function (json) {
            if (json.code === "2002") {
                layer.msg('更新成功', {
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

function changePassword() {
    $("#change-password-div").css("visibility", "visible")
    layer.open({
        type: 1,
        title: '修改密码',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['330px', "200px"],
        content: $("#change-password-div"),
        btn: ['修改'],
        yes: function (index, layero) {
            console.log($("#password-change").val())
            console.log($("#password-change-confirm").val())
            console.log("yes")
            let passwordRegExp = new RegExp("^.{6,}$")
            if ($("#password-change").val() !== $("#password-change-confirm").val()) {
                layer.msg('两次输入密码不一致，请重新输入', {
                    time: 1000
                })
            } else {
                if (!passwordRegExp.test($("#password-change").val())) {
                    layer.msg('请输入六位以上密码', {
                        time: 1000
                    })
                } else {
                    updateUserInfo("user_password", $("#password-change").val(), $.cookie('userId'))
                }
            }
        }
    })
}

function changePhone() {
    $("#change-phone-div").css("visibility", "visible")
    layer.open({
        type: 1,
        title: '修改联系电话',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['330px', '200px'],
        content: $("#change-phone-div"),
        btn: ['修改'],
        yes: function (index, layero) {
            console.log($("#phone-change").val())
            console.log("yes")
            let phoneRegExp = new RegExp("^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")
            if (!phoneRegExp.test($("#phone-change").val())) {
                layer.msg('请输入正确的手机号码', {
                    time: 1000
                })
            } else {
                updateUserInfo("user_phone", $("#phone-change").val(), $.cookie('userId'))
            }
        }
    })
}

function changeMail() {
    $("#change-email-div").css("visibility", "visible")
    layer.open({
        type: 1,
        title: '修改邮箱',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['330px', '200px'],
        content: $("#change-email-div"),
        btn: ['修改'],
        yes: function (index, layero) {
            console.log($("#email-change").val())
            console.log("yes")
            let mailRegExp = new RegExp("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
            if (!mailRegExp.test($("#email-change").val())) {
                layer.msg('请输入正确的邮箱', {
                    time: 1000
                })
            } else {
                updateUserInfo("user_email", $("#email-change").val(), $.cookie('userId'))
            }
        }
    })
}