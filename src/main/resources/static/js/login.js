function checkInput(account, password){
    // 账号校验，学生学号：10位或12位数字   教师工号：Z开头 + 7位数字
    let accountRegExp = new RegExp("(^\\d{10}$)|(^\\d{12}$)|(^(Z|z)\\d{7}$)");
    // 密码校验：不低于六位的字符
    let passwordRegExp = new RegExp("^.{6,}$");
    if (!accountRegExp.test(account)) {
        layer.msg("学工号格式错误，请重新输入");
        return false
    }
    if (!passwordRegExp.test(password)) {
        layer.msg("密码不正确，请重新输入");
        return false
    }
    return true
}

//回车键登录
document.addEventListener("keypress", function (event) {
    if (event.keyCode === 13)
        login()
})

function login(){
    let login_account = $("#login-account").val()
    let login_password = $("#login-password").val()
    if (checkInput(login_account, login_password)){
        $.ajax({
            type: "POST",
            url:"/index/login",
            data:{
                account: login_account,
                password: login_password
            },
            success: function (res) {
                console.log(res)
                // 登陆成功，有通知发布权限
                if (res.code === "4000") {
                    $.cookie("userId", res.data.userId)
                    $.cookie("userName", res.data.userName)
                    location.href = "/notice-release.html"
                } else if (res.data.code === "4007"){ // 登陆成功，无通知发布权限
                    layer.msg("当前用户无通知发布权限")
                } else {
                    layer.msg("登陆失败，请检查账号密码是否正确")
                }
            },
            fail:function(){
                layer.msg(res.desc + "！", {
                    time: 1000
                });
            }
        })
    }
}