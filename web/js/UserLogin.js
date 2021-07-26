//登录按钮
$("#loginBtn").click(function () {
    console.log("登录");
    var UnameText = $("#UnameText").val()
    var UpwdText = $("#UpwdText").val()
    var captchaCode = $("#captchaCode").val()
    if (captchaCode != '') {
        if (UnameText != '' || UpwdText != '') {
            $.ajax({
                url: 'UserLoginServlet',
                type: 'post',
                data: {
                    'UnameText': UnameText,
                    'UpwdText': UpwdText,
                    'captchaCode': captchaCode
                },
                async: true,
                dataType: 'JSON',
                success: function (resp) {
                    console.log(resp);
                    if (resp.id == 0) {
                        alert("登录成功")
                        window.location.href = resp.location;
                    } else if (resp.id == 1) {
                        var myMessager = new $.zui.Messager();
                        myMessager.show(resp.message);
                    }
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })
        }
    } else {
        var myMessager = new $.zui.Messager({type: 'error'});
        myMessager.show('请输入验证码');
    }
})

//注册
$("#RlogonBtn").click(function () {

    var RUAccountText = $("#RUAccountText").val()
    var RUpwdText = $("#RUpwdText").val()
    var RUpwdCheckText = $("#RUpwdCheckText").val()
    var sexVal = $("input[name='sexRadio']:checked").val();
    var RUageText = $("#RUageText").val()
    var RUphoneText = $("#RUphoneText").val()
    var RUAddressText = $("#RUAddressText").val()

    console.log(RUAccountText + "/" + RUpwdText + "/" + RUpwdCheckText + "/" + RUageText + "/" + RUphoneText + "/" + RUAddressText + "/" + sexVal)

    $.ajax({
        url: 'UserRegisterServlet',
        type: 'post',
        data: {
            'RUAccountText': RUAccountText,
            'RUpwdText': RUpwdText,
            'RUpwdCheckText': RUpwdCheckText,
            'sexVal': sexVal,
            'RUageText': RUageText,
            'RUphoneText': RUphoneText,
            'RUAddressText': RUAddressText
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            layer.msg(resp.message)
        },
        error: function (data) {
            console.log("联系管理员")
        }
    })

})


