window.onload = getOrderTable();

// 领域选择
$("#fieldChoose").change(function () {
    var fieldId = $('#fieldChoose option:selected').val();
    $.ajax({
        url: 'GetConsultNameServlet',
        type: 'post',
        data: {
            'fieldId': fieldId
        },
        async: true,
        dataType: 'JSON',
        success: function (rep) {
            arr = rep.datas.consultInfo;
            console.log(arr)
            var consultChoose = document.getElementById('consultChoose');
            consultChoose.innerHTML = '<option consultId=null> 请选择咨询师</option>';
            for (var i = 0; i < arr.length; i++) {
                consultChoose.innerHTML += `<option value=${i} consultId='${arr[i].consultId}' >${arr[i].adminAccount}</option>`;
            }
        },
        error: function (data) {
            layer.msg("联系管理员")
        }
    })
    console.log()
})

//咨询师选择
$("#consultChoose").change(function () {
    var consultChooseId = $('#consultChoose option:selected').val();
    console.log("选择的值:" + consultChooseId)
    var consultName = document.getElementById('consultName');
    consultName.innerText = `${arr[parseInt(consultChooseId)].adminAccount}`;
    var Graduate = document.getElementById('Graduate');
    Graduate.innerText = `${arr[parseInt(consultChooseId)].consultSchool}`
    var title = document.getElementById('title');
    title.innerText = `${arr[parseInt(consultChooseId)].titleName}`

    var fields = document.getElementById('fields');
    FieldArr = arr[parseInt(consultChooseId)].fields;
    fields.innerText = ``;
    for (var i = 0; i < FieldArr.length; i++) {
        fields.innerHTML += `<div class="divLeft">${FieldArr[i]}</div>`
        console.log(FieldArr[i])
    }
    var money = document.getElementById('money');
    money.innerText = `${arr[parseInt(consultChooseId)].consultFee}`
    var backGround = document.getElementById('backGround');
    backGround.innerText = `${arr[parseInt(consultChooseId)].consultBackground}`
})

//查询
$("#QueryBtn").click(function () {
    var consultId = 0;
    var fieldId = null;
    var consultTime = $("#consultTime").val();
    consultId = $('#consultChoose option:selected').attr("consultId");
    fieldId = $('#fieldChoose option:selected').val();
    console.log(fieldId + " " + consultId + " " + consultTime)
    if (fieldId == 0) {
        layer.msg("请选择领域")
    } else if (consultId == "null") {
        layer.msg("请选择咨询师")
    } else if (consultTime == '') {
        layer.msg("请输入预约时间")
    } else {
        consultTime
        $.ajax({
            url: 'GetConsultTimeServlet',
            type: 'post',
            data: {
                'consultTime': consultTime,
                'consultId': consultId
            },
            async: true,
            dataType: 'JSON',
            success: function (rep) {
                arr = rep.datas.consultOrderTime;
                myTbody.innerHTML = ``;
                console.log(arr)
                var ss = '';
                for (var i = arr.length - 1; i >= 0; i--) {
                    var s = arr[i].calendarTime.substring(11, 16);
                    // if ((i + 1) % 4 == 0) {
                    //     ss += '<tr>';
                    // }
                    if (arr[i].calendarState == 0) {
                        ss += `<td orderTime='${arr[i].calendarTime}' calendarState=${arr[i].calendarState}>${s}</td>`
                    } else {
                        ss += `<td orderTime='${arr[i].calendarTime}' calendarState=${arr[i].calendarState} style="background-color: red">${s}</td>`
                    }
                    // if (i % 4 == 0) {
                    //     ss += '</tr>';
                    // }
                    console.log("可预约时间" + arr[i].calendarTime)
                }
                myTbody.innerHTML = ss;
                console.log(ss)
            },
            error: function (data) {
                layer.msg("联系管理员")
            }
        })
    }
})

//时段点击
$("#myTbody").click(function (event) {
    var input = event.target;
    var tds = input.parentElement.children
    object = event;

    if ($.trim(input.getAttribute("style")) != $.trim("background-color: red")) {
        for (let i = 0; i < tds.length; i++) {
            var bgColor = tds[i].getAttribute("style")
            if ($.trim(bgColor) === $.trim("background-color: #0a67fb")) {
                tds[i].removeAttribute("style")
            }
        }
        input.setAttribute("style", "background-color: #0a67fb")
    }

})

//提交按钮
$("#ComBtn").click(function () {
    try {
        var fieldId = $('#fieldChoose option:selected').val();//领域Id
        var consultId = $('#consultChoose option:selected').attr("consultId");//预约的咨询师ID
        var radioVal = object.target.getAttribute("ordertime") //选中的预约时间
        var ProblemTxt = $("#ProblemTxt").val();
        console.log(fieldId + " " + consultId + " " + radioVal + " " + ProblemTxt)
        $.ajax({
            url: 'OrderConsultServlet',
            type: 'post',
            data: {
                'fieldId': fieldId,
                'consultId': consultId,
                'radioVal': radioVal,
                'ProblemTxt': ProblemTxt,
            },
            async: true,
            dataType: 'JSON',
            success: function (rep) {
                layer.msg(rep.message)
                myTbody.innerHTML = ``;
            },
            error: function (data) {
                layer.msg("联系管理员")
            }
        })
    } catch (err) {
        layer.msg("请选择预约的时间")
    }
})

//日期点击
$("#consultTime").click(function () {
    myTbody.innerHTML = ``;
})

// 仅选择日期
$(".form-date").datetimepicker(
    {
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: "yyyy-mm-dd"
    });

//获取表格信息
function getOrderTable() {
    $.ajax({
        url: 'GetOrderTableServlet',
        type: 'post',
        async: true,
        dataType: 'JSON',
        success: function (rep) {
            arr = rep.datas.orderConsultTable;
            console.log(arr)
            for (let i = 0; i < arr.length; i++) {
                if (arr[i].stateName == "已诊断") {
                    style = ""
                } else {
                    style = "display: none"
                }
                console.log("遍历表格数组")
                mybody.innerHTML += `<tr style="text-align: center">
                     <td>${arr[i].orderTime}</td>
                        <td>${arr[i].adminAccount}</td>
        <td>${arr[i].fieldName}</td>
        <td>${arr[i].stateName}</td>
        <td>
            <button type="button" id="" class="btn" value="评价" adminId="${arr[i].adminId}" style="${style}">评价</button>
            <button type="button" id="" class="btn btn-primary"  orderTime="${arr[i].orderTime}" value="查看详情">查看详情</button>
            <button type="button"  id="" class="btn" adminId="${arr[i].adminId}" value="查看咨询师"}>查看咨询师
            </button>
        </td>
    </tr>`
            }


        },
        error: function (resp) {
            layer.msg("联系管理员")
        }
    })
}

$("#mybody").click(function (event) {
    var input = event.target;
    adminId = input.getAttribute("adminId");
    orderTime = input.getAttribute("orderTime");

    if (input.value == "查看咨询师") {
        console.log("查看咨询师")
        $.ajax({
            url: 'ViewConsultantServlet',
            type: 'post',
            data: {
                'adminId': adminId,
                'orderTime': orderTime
            },
            async: true,
            dataType: 'JSON',
            success: function (resp) {
                arr = resp.datas.consultInfoQos;
                console.log(arr[0])
                $("#consultNameV").attr("placeholder", arr[0].adminAccount)
                $("#fieldV").attr("placeholder", arr[0].fieldName)
                $("#schoolV").attr("placeholder", arr[0].consultSchool);
                $("#titleV").attr("placeholder", arr[0].titleName);
                $("#consultIntroV").attr("placeholder", arr[0].consultIntro);
                var BgV = document.getElementById("BgV");
                BgV.innerText = arr[0].consultBackground;

                for (let i = 0; i < arr.length; i++) {
                    userEvaluate.innerHTML += ` <div> ${arr[i].userAccount}:</div>
                    <div><textarea id="" class="form-control" disabled> ${arr[i].commentContent} </textarea></div>
                    <div>&nbsp;</div>`;
                }


            },
            error: function (date) {
                layer.msg("请联系管理员")
            }
        })

        $('#viewConsultant').modal({
            keyboard: false,
            show: true
        })
    } else if (input.value == "查看详情") {
        console.log("查看详情")
        $.ajax({
            url: 'AppointmentDetailServlet',
            type: 'post',
            data: {
                'orderTime': orderTime,
            },
            async: true,
            dataType: 'JSON',
            success: function (resp) {
                arr = resp.datas.orderInfos;
                console.log(arr[0])
                $("#advisoryD").attr("placeholder", arr[0].userAccount)
                $("#fieldD").attr("placeholder", arr[0].fieldName)
                $("#oTimeD").attr("placeholder", arr[0].orderTime)
                $("#ReservationStatusD").attr("placeholder", arr[0].stateName)
                $("#feeD").attr("placeholder", arr[0].stateName)
                $("#problemD").attr("placeholder", arr[0].orderProblem)
                $("#replyD").attr("placeholder", arr[0].orderReply)
                $("#replyTime").attr("placeholder", arr[0].replyTime)
                $("#consultNameD").attr("placeholder", arr[0].adminAccount)
                $("#contents").attr("placeholder", arr[0].commentContent)


            },
            error: function (date) {
                layer.msg("请联系管理员")
            }

        })
        $('#DetailModal').modal({
            keyboard: false,
            show: true
        })
    } else if (input.value == "评价") {
        console.log("评价")
        $.ajax({
            url: 'GetEvaluateConsultInfoServlet',
            type: 'post',
            data: {
                'adminId': adminId
            },
            async: true,
            dataType: 'JSON',
            success: function (resp) {
                consultInfoQos = resp.datas.consultInfoQos;
                console.log(consultInfoQos)
                $("#advisoryE").attr("placeholder", consultInfoQos[0].adminAccount);
                $("#fieldE").attr("placeholder", consultInfoQos[0].fields);
                $("#schoolE").attr("placeholder", consultInfoQos[0].consultSchool);
                $("#titleE").attr("placeholder", consultInfoQos[0].titleName);


                var BgE = document.getElementById("BgE");
                BgE.innerText = consultInfoQos[0].consultIntro;

                $("#evaluateBtn").attr("consultId", consultInfoQos[0].consultId)
            },
            error: function (data) {
                layer.msg("联系管理员")
            }
        })

        $('#evaluateModal').modal({
            keyboard: false,
            show: true
        })
    }
})

$("#evaluateBtn").click(function (event) {
    var consultId = $(this).attr("consultId");
    var evaluateTxt = $("#evaluate").val();
    console.log("提交" + consultId + evaluateTxt)
    $.ajax({
        url: 'EvaluateConsultServlet',
        type: 'post',
        data: {
            'consultId': consultId,
            'evaluateTxt': evaluateTxt
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            layer.msg(resp.message)
        },
        error: function (resp) {
            layer.msg("联系管理员")
        }
    })


})